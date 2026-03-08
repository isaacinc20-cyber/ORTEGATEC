package com.example.ortegatec

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ortegatec.adapter.AdapterLab
import com.example.ortegatec.databinding.ActivityMainBinding
import com.example.ortegatec.viewmodel.LabViewModel
import com.example.ortegatec.viewmodel.LabViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: LabViewModel by viewModels {
        LabViewModelFactory((application as OrtegatecApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ORTEGATEC", "MainActivity iniciada correctamente")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = AdapterLab { lab ->
            val intent = Intent(this, EquipActivity::class.java).apply {
                putExtra("LAB_ID", lab.id)
                putExtra("LAB_NAME", lab.nombre)
            }
            startActivity(intent)
        }

        binding.rvLaboratorios.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
        }

        viewModel.allLaboratorios.observe(this) { labs ->
            adapter.updateList(labs)
        }

        binding.fabAddLab.setOnClickListener {
            val intent = Intent(this, ActivityEdit::class.java).apply {
                putExtra("MODO", "LABORATORIO")
            }
            startActivity(intent)
        }

        binding.btnSincronizar.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.sincronizar()
        }

        viewModel.syncStatus.observe(this) { success ->
            if (success != null) {
                binding.progressBar.visibility = View.GONE
                if (success) {
                    Toast.makeText(this, "Sincronización Exitosa", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error en la Sincronización", Toast.LENGTH_SHORT).show()
                }
                viewModel.resetSyncStatus()
            }
        }
    }
}
