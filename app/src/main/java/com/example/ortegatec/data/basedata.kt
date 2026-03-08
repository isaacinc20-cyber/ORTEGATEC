package com.example.ortegatec.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ortegatec.model.Laboratorio
import com.example.ortegatec.model.Equipo

@Database(entities = [Laboratorio::class, Equipo::class], version = 2, exportSchema = false)
@TypeConverters(zconverters::class)
abstract class basedata : RoomDatabase() {

    abstract fun obtenerDao(): Audit

    companion object {
        @Volatile
        private var INSTANCIA_UNICA: basedata? = null

        fun obtenerBaseDatos(contexto: Context): basedata {
            return INSTANCIA_UNICA ?: synchronized(this) {
                val instancia = Room.databaseBuilder(
                    contexto.applicationContext,
                    basedata::class.java,
                    "db_techaudit_ortegatec"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCIA_UNICA = instancia
                instancia
            }
        }
    }
}
