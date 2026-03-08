package com.example.ortegatec.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "inventario_dispositivos_ortegatec",
    indices = [Index(value = ["idReferenciaSede"])]
)
@Parcelize
data class DeviceEntity(
    @PrimaryKey
    val codigoActivo: String, // Identificador único del activo
    val denominacionTecnica: String, // Nombre del equipo
    val areaAsignada: String, // Ubicación física
    val timestampRegistro: String, // Fecha de captura
    var estadoFisico: EstadoDispositivo = EstadoDispositivo.PENDIENTE,
    var observaciones: String = "",
    var rutaImagen: String? = null,

    val idReferenciaSede: Int // Relación con la sede (Tipo Int)
) : Parcelable