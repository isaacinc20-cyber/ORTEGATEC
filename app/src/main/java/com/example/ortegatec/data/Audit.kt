package com.example.ortegatec.data

import androidx.room.*
import com.example.ortegatec.model.Laboratorio
import com.example.ortegatec.model.Equipo
import kotlinx.coroutines.flow.Flow

@Dao
interface Audit {

    // Laboratorios
    @Query("SELECT * FROM laboratorios")
    fun obtenerTodosLosLaboratorios(): Flow<List<Laboratorio>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarLaboratorio(laboratorio: Laboratorio)

    // Equipos
    @Query("SELECT * FROM equipos WHERE laboratorioId = :labId")
    fun obtenerEquiposPorLaboratorio(labId: Int): Flow<List<Equipo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEquipo(equipo: Equipo)

    @Update
    suspend fun actualizarEquipo(equipo: Equipo)

    @Delete
    suspend fun eliminarEquipo(equipo: Equipo)

    @Query("SELECT * FROM equipos")
    suspend fun obtenerTodosLosEquipos(): List<Equipo>
}
