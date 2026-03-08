package com.example.ortegatec

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ortegatec.adapter.AdapterEquip
import com.example.ortegatec.databinding.ActivityInventarioSedeBinding
import com.example.ortegatec.viewmodel.EquipViewModel
import com.example.ortegatec.viewmodel.EquipViewModelFactory

class EquipActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInventarioSedeBinding
    private val viewModel: EquipViewModel by viewModels {
        EquipViewModelFactory((application as OrtegatecApp).repository)
    }
    private lateinit var adapter: AdapterEquip
    private var labId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInventarioSedeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        labId = intent.getIntExtra("LAB_ID", -1)
        Log.d("ORTEGATEC", "Cargando equipos para el laboratorio con ID: $labId")
        val labName = intent.getStringExtra("LAB_NAME") ?: "Equipos"
        
        supportActionBar?.title = labName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter = AdapterEquip { equipo ->
            val intent = Intent(this, ActivityEdit::class.java).apply {
                putExtra("MODO", "EQUIPO")
                putExtra("EQUIPO", equipo)
                putExtra("LAB_ID", labId)
            }
            startActivity(intent)
        }

        binding.rvEquipos.apply {
            layoutManager = LinearLayoutManager(this@EquipActivity)
            this.adapter = this@EquipActivity.adapter
        }

        viewModel.getEquiposByLab(labId).observe(this) { equipos ->
            adapter.updateList(equipos)
        }

        binding.fabAddEquipo.setOnClickListener {
            val intent = Intent(this, ActivityEdit::class.java).apply {
                putExtra("MODO", "EQUIPO")
                putExtra("LAB_ID", labId)
            }
            startActivity(intent)
        }

        setupSwipeToDelete()
    }

    private fun setupSwipeToDelete() {
        val swipeHandler = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val equipo = adapter.getEquipoAt(position)
                viewModel.delete(equipo)
                Toast.makeText(this@EquipActivity, "Equipo eliminado", Toast.LENGTH_SHORT).show()
            }
        }
        ItemTouchHelper(swipeHandler).attachToRecyclerView(binding.rvEquipos)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
