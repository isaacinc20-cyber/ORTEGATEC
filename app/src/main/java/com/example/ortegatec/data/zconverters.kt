package com.example.ortegatec.data

import androidx.room.TypeConverter
import com.example.ortegatec.model.EstadoEquipo

class zconverters {

    @TypeConverter
    fun deEstadoAString(estado: EstadoEquipo?): String {
        return estado?.name ?: EstadoEquipo.PENDIENTE.name
    }

    @TypeConverter
    fun deStringAEstado(valor: String?): EstadoEquipo {
        return try {
            if (valor == null) EstadoEquipo.PENDIENTE
            else EstadoEquipo.valueOf(valor)
        } catch (e: Exception) {
            EstadoEquipo.PENDIENTE
        }
    }
}
