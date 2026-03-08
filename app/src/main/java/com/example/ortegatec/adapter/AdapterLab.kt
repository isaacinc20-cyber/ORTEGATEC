package com.example.ortegatec.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ortegatec.databinding.ItemSedeBinding
import com.example.ortegatec.model.Laboratorio

class AdapterLab(
    private var listadoSedes: List<Laboratorio> = emptyList(),
    private val accionAlClick: (Laboratorio) -> Unit
) : RecyclerView.Adapter<AdapterLab.SedeViewHolder>() {

     class SedeViewHolder(val binding: ItemSedeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SedeViewHolder {
        val binding = ItemSedeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SedeViewHolder(binding)
    }

    override fun getItemCount(): Int = listadoSedes.size

    override fun onBindViewHolder(holder: SedeViewHolder, position: Int) {
        val sedeActual = listadoSedes[position]
        holder.binding.txtNombreSede.text = sedeActual.nombre
        holder.binding.txtLocalizacion.text = "Edificio: ${sedeActual.edificio}"

        holder.binding.root.setOnClickListener {
            accionAlClick(sedeActual)
        }
    }

    fun updateList(newList: List<Laboratorio>) {
        this.listadoSedes = newList
        notifyDataSetChanged()
    }
}
