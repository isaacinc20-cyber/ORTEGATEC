package com.example.ortegatec

import android.app.Application
import com.example.ortegatec.data.basedata
import com.example.ortegatec.data.repository
import com.example.ortegatec.data.apiservice
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OrtegatecApp : Application() {

    private val database by lazy { basedata.obtenerBaseDatos(this) }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://69acb7319ca639a5217f77e9.mockapi.io/") // Tu URL de MockAPI
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val apiService by lazy { retrofit.create(apiservice::class.java) }

    val repository by lazy {
        repository(database.obtenerDao(), apiService)
    }
}