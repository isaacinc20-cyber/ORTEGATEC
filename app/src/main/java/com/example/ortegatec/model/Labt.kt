package com.example.ortegatec.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "sedes_ortegatec")
@Parcelize
data class Labt(
    @PrimaryKey(autoGenerate = true)
    val idSede: Int = 0, // ID autoincremental para control de bases de datos
    val nombreEstablecimiento: String, // Ejemplo: "Laboratorio de Computación 1"
    val ubicacionBloque: String // Ejemplo: "Bloque B - Planta Alta"
) : Parcelable
