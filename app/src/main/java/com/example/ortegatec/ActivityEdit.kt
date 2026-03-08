package com.example.ortegatec

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ortegatec.databinding.ActivityGestionRegistroBinding
import com.example.ortegatec.model.Equipo
import com.example.ortegatec.model.EstadoEquipo
import com.example.ortegatec.model.Laboratorio
import kotlinx.coroutines.launch

class ActivityEdit : AppCompatActivity() {

    private lateinit var binding: ActivityGestionRegistroBinding
    private var modo: String? = null
    private var labId: Int = -1
    private var equipoEdicion: Equipo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGestionRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        modo = intent.getStringExtra("MODO")
        labId = intent.getIntExtra("LAB_ID", -1)
        equipoEdicion = intent.getParcelableExtra("EQUIPO")

        configurarUI()

        binding.btnGuardar.setOnClickListener {
            if (modo == "LABORATORIO") {
                guardarLaboratorio()
            } else {
                guardarEquipo()
            }
        }
    }

    private fun configurarUI() {
        if (modo == "LABORATORIO") {
            supportActionBar?.title = "Nuevo Laboratorio"
            binding.tilExtra.hint = "Edificio"
            binding.tvEstadoLabel.visibility = View.GONE
            binding.spEstado.visibility = View.GONE
        } else {
            supportActionBar?.title = if (equipoEdicion != null) "Editar Equipo" else "Nuevo Equipo"
            binding.tilExtra.hint = "Nombre del Equipo" // Reutilizamos campos
            binding.tilNombre.hint = "Nombre del Equipo"
            binding.tilExtra.visibility = View.GONE // Para equipos solo usamos nombre y estado en este diseño simplificado
            
            binding.tvEstadoLabel.visibility = View.VISIBLE
            binding.spEstado.visibility = View.VISIBLE

            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, EstadoEquipo.values())
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spEstado.adapter = adapter

            equipoEdicion?.let {
                binding.etNombre.setText(it.nombre)
                binding.spEstado.setSelection(it.estado.ordinal)
            }
        }
    }

    private fun guardarLaboratorio() {
        val nombre = binding.etNombre.text.toString().trim()
        val edificio = binding.etExtra.text.toString().trim()

        if (nombre.isEmpty() || edificio.isEmpty()) {
            Toast.makeText(this, "Campos obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        val lab = Laboratorio(nombre = nombre, edificio = edificio)
        lifecycleScope.launch {
            (application as OrtegatecApp).repository.insertarLaboratorio(lab)
            finish()
        }
    }

    private fun guardarEquipo() {
        val nombre = binding.etNombre.text.toString().trim()
        val estado = binding.spEstado.selectedItem as EstadoEquipo

        if (nombre.isEmpty()) {
            Toast.makeText(this, "El nombre es obligatorio", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            val repo = (application as OrtegatecApp).repository
            if (equipoEdicion != null) {
                repo.actualizarEquipo(equipoEdicion!!.copy(nombre = nombre, estado = estado))
            } else {
                repo.insertarEquipo(Equipo(nombre = nombre, estado = estado, laboratorioId = labId))
            }
            finish()
        }
    }
}
