package com.example.repuestosconejo.repository

import androidx.lifecycle.LiveData
import com.example.repuestosconejo.data.RepuestosDao
import com.example.repuestosconejo.data.VehiculosDao
import com.example.repuestosconejo.model.Repuestos
import com.example.repuestosconejo.model.Vehiculos

class RepuestosRepository(private val repuestosDao: RepuestosDao) {
    suspend fun saveRepuestos(repuestos: Repuestos) {
    if (repuestos.id==0){
        repuestosDao.addRepuestos(repuestos)

    }else {
        repuestosDao.updateRepuestos(repuestos)

    }
    }
    suspend fun deleteRepuestos(repuestos: Repuestos) {
        repuestosDao.deleteRepuestos(repuestos)
    }
    val getRepuestos : LiveData<List<Repuestos>> = repuestosDao.getRepuestos()
}

