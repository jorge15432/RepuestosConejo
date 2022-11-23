package com.example.repuestosconejo.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.repuestosconejo.model.Pedidos
import com.example.repuestosconejo.model.Repuestos
import com.example.repuestosconejo.model.Vehiculos


@Database(entities = [Vehiculos::class, Pedidos::class, Repuestos::class],version=1, exportSchema = false)
abstract  class RepuestosConejoDatabase : RoomDatabase() {
    abstract fun vehiculosDao() : VehiculosDao
    abstract fun repuestosDao() : RepuestosDao
    abstract fun pedidosDao() : PedidosDao


    companion object {
        @Volatile
        private var INSTANCE: RepuestosConejoDatabase? = null
        fun getDatabase(context: android.content.Context) : RepuestosConejoDatabase {
            val temp = INSTANCE
            if (temp != null) {
                return temp
            }
            synchronized(this) {
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    RepuestosConejoDatabase::class.java,
                    "repuestosconejo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}


