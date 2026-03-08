package com.example.ortegatec

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun verificarIdentidadPaqueteOrtegatec() {
        // Obtenemos el contexto de la aplicación bajo prueba (ORTEGATEC)
        val contextoApp = InstrumentationRegistry.getInstrumentation().targetContext

        // Verificamos que el paquete coincida exactamente con el ID de tu proyecto universitario
        assertEquals("com.example.ortegatec", contextoApp.packageName)
    }
}