package com.example.repuestosconejo.repository

import androidx.lifecycle.LiveData
import com.example.repuestosconejo.data.VehiculosDao
import com.example.repuestosconejo.model.Vehiculos

class VehiculosRepository(private val vehiculosDao: VehiculosDao) {

     fun saveVehiculos(vehiculos: Vehiculos) {
        vehiculosDao.saveVehiculos(vehiculos)

    }
     fun deleteVehiculos(vehiculos: Vehiculos) {
        vehiculosDao.deleteVehiculos(vehiculos)
    }
    val getVehiculos : LiveData<List<Vehiculos>> = vehiculosDao.getVehiculos()
}

