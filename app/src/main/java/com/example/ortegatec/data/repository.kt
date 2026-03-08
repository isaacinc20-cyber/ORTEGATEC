package com.example.ortegatec.data

import com.example.ortegatec.model.Laboratorio
import com.example.ortegatec.model.Equipo
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class repository(
    private val auditDao: Audit,
    private val apiServicio: apiservice
) {

    val laboratorios: Flow<List<Laboratorio>> = auditDao.obtenerTodosLosLaboratorios()

    suspend fun insertarLaboratorio(laboratorio: Laboratorio) {
        auditDao.insertarLaboratorio(laboratorio)
    }

    fun obtenerEquiposPorLaboratorio(labId: Int): Flow<List<Equipo>> =
        auditDao.obtenerEquiposPorLaboratorio(labId)

    suspend fun insertarEquipo(equipo: Equipo) {
        auditDao.insertarEquipo(equipo)
    }

    suspend fun actualizarEquipo(equipo: Equipo) {
        auditDao.actualizarEquipo(equipo)
    }

    suspend fun eliminarEquipo(equipo: Equipo) {
        auditDao.eliminarEquipo(equipo)
    }

    suspend fun sincronizarConNube(): Response<Unit> {
        val todosLosEquipos = auditDao.obtenerTodosLosEquipos()
        return apiServicio.enviarInventarioALaNube(todosLosEquipos)
    }
}
