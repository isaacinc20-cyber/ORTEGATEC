package com.example.ortegatec.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ortegatec.databinding.ItemSedeBinding
import com.example.ortegatec.model.Laboratorio

class AdapterAudit(
    private var listaSedes: List<Laboratorio>,
    private val alHacerClick: (Laboratorio) -> Unit
) : RecyclerView.Adapter<AdapterAudit.SedeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SedeViewHolder {
        val binding = ItemSedeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SedeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SedeViewHolder, position: Int) {
        val sede = listaSedes[position]
        holder.bind(sede, alHacerClick)
    }

    override fun getItemCount(): Int = listaSedes.size

    fun actualizarDatos(nuevaLista: List<Laboratorio>) {
        this.listaSedes = nuevaLista
        notifyDataSetChanged()
    }

    class SedeViewHolder(private val binding: ItemSedeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sede: Laboratorio, alHacerClick: (Laboratorio) -> Unit) {
            binding.txtNombreSede.text = sede.nombre
            binding.txtLocalizacion.text = sede.edificio

            itemView.setOnClickListener { alHacerClick(sede) }
        }
    }
}