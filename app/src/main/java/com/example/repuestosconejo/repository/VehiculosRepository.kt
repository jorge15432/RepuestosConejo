package com.example.repuestosconejo.repository

import androidx.lifecycle.LiveData
import com.example.repuestosconejo.data.VehiculosDao
import com.example.repuestosconejo.model.Vehiculos

class VehiculosRepository(private val vehiculosDao: VehiculosDao) {
    suspend fun saveVehiculos(vehiculos: Vehiculos) {
    if (vehiculos.id==0){
        vehiculosDao.addVehiculos(vehiculos)

    }else {
        vehiculosDao.updateVehiculos(vehiculos)

    }
    }
    suspend fun deleteVehiculos(vehiculos: Vehiculos) {
        vehiculosDao.deleteVehiculos(vehiculos)
    }
    val getVehiculos : LiveData<List<Vehiculos>> = vehiculosDao.getVehiculos()
}

