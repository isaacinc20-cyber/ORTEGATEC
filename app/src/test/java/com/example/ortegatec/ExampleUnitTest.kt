package com.example.ortegatec

import org.junit.Test
import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun verificacionLogicaBase() {
        // Asegura que el motor de pruebas de ORTEGATEC esté activo
        val resultadoEsperado = 4
        assertEquals("Error en la validación del motor de pruebas", resultadoEsperado, 2 + 2)
    }

    @Test
    fun validarLogicaDeSeriales() {
        // Como profesional de sistemas, validamos que los identificadores no sean nulos
        val prefijoMarca = "ORTEGATEC"
        val serialSimulado = "$prefijoMarca-2026-X1"

        assertTrue("El serial debe comenzar con el nombre de la marca", serialSimulado.startsWith("ORTEGATEC"))
        assertNotNull("El objeto de prueba no debería ser nulo", serialSimulado)
    }
}