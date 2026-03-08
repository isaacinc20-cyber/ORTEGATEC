package com.example.ortegatec.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "inventario_dispositivos_ortegatec",
    foreignKeys = [
        ForeignKey(
            entity = Laboratorio::class,
            parentColumns = ["idSede"], // Debe coincidir con el PK de tu clase Laboratorio
            childColumns = ["idReferenciaSede"],
            onDelete = ForeignKey.CASCADE // Si borras la sede, se borran sus equipos
        )
    ],
    indices = [Index("idReferenciaSede")]
)
@Parcelize
data class EEntity(
    @PrimaryKey
    val serialUnico: String,        // Identificador físico (S/N)
    val nombreTecnico: String,     // Nombre del activo
    val puntoUbicacion: String,    // Ubicación en el laboratorio
    val fechaAlta: String,         // Fecha de ingreso al sistema
    val comentarios: String,
    val estadoFisico: EstadoDispositivo = EstadoDispositivo.PENDIENTE,
    val idReferenciaSede: Int      // Vinculación con la sede (Tipo Int)
) : Parcelable