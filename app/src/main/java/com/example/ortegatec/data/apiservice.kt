package com.example.ortegatec.data

import com.example.ortegatec.model.Equipo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface apiservice {

    @POST("sincronizar_inventario")
    suspend fun enviarInventarioALaNube(
        @Body listadoDispositivos: List<Equipo>
    ): Response<Unit>
}
