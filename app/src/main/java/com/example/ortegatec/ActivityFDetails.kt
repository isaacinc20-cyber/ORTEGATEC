package com.example.ortegatec

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ortegatec.databinding.ActivityVistaDetalladaBinding
import com.example.ortegatec.model.EEntity
import com.example.ortegatec.model.EstadoDispositivo

class ActivityFDetails : AppCompatActivity() {

    private lateinit var binding: ActivityVistaDetalladaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityVistaDetalladaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperamos el objeto serializado de ORTEGATEC
        val dispositivo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("OBJETO_EQUIPO", EEntity::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("OBJETO_EQUIPO") as? EEntity
        }

        dispositivo?.let {
            cargarInformacionTecnica(it)
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun cargarInformacionTecnica(item: EEntity) {
        binding.txtDetalleNombreTecnico.text = item.nombreTecnico
        binding.txtDetalleSerial.text = getString(R.string.detalle_serial, item.serialUnico.take(8).uppercase())
        binding.txtDetallePuntoUbicacion.text = item.puntoUbicacion
        binding.txtDetalleFechaAlta.text = getString(R.string.detalle_ingreso, item.fechaAlta)
        binding.txtDetalleComentarios.text = item.comentarios.ifEmpty { getString(R.string.sin_observaciones) }

        val colorIdentidad = when (item.estadoFisico) {
            EstadoDispositivo.OPERATIVO -> "#2E7D32".toColorInt()
            EstadoDispositivo.PENDIENTE -> "#546E7A".toColorInt()
            EstadoDispositivo.DAÑADO -> "#C62828".toColorInt()
            EstadoDispositivo.EXTRAVIADO -> "#212121".toColorInt()
        }

        binding.viewFranjaEstado.setBackgroundColor(colorIdentidad)
        supportActionBar?.title = getString(R.string.titulo_detalle, item.estadoFisico.name)
    }
}