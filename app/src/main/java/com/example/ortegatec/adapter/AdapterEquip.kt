package com.example.ortegatec.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ortegatec.databinding.ItemEquipoBinding
import com.example.ortegatec.model.Equipo
import com.example.ortegatec.model.EstadoEquipo

class AdapterEquip(
    private var listadoEquipos: List<Equipo> = emptyList(),
    private val alHacerClick: (Equipo) -> Unit
) : RecyclerView.Adapter<AdapterEquip.EquipoViewHolder>() {

    inner class EquipoViewHolder(val binding: ItemEquipoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipoViewHolder {
        val binding = ItemEquipoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EquipoViewHolder(binding)
    }

    override fun getItemCount(): Int = listadoEquipos.size

    override fun onBindViewHolder(holder: EquipoViewHolder, position: Int) {
        val equipo = listadoEquipos[position]
        holder.binding.txtNombreEquipo.text = equipo.nombre
        holder.binding.txtEstadoEquipo.text = equipo.estado.name

        val color = when (equipo.estado) {
            EstadoEquipo.OPERATIVO -> Color.parseColor("#2E7D32")
            EstadoEquipo.DAÑADO -> Color.parseColor("#C62828")
            EstadoEquipo.PENDIENTE -> Color.parseColor("#FBC02D")
            EstadoEquipo.EXTRAVIADO -> Color.parseColor("#212121")
        }
        holder.binding.indicadorEstado.setBackgroundColor(color)
        holder.binding.txtEstadoEquipo.setTextColor(color)

        holder.binding.root.setOnClickListener { alHacerClick(equipo) }
    }

    fun updateList(newList: List<Equipo>) {
        this.listadoEquipos = newList
        notifyDataSetChanged()
    }

    fun getEquipoAt(position: Int): Equipo = listadoEquipos[position]
}
