package com.example.repuestosconejo.repository

import androidx.lifecycle.LiveData
import com.example.repuestosconejo.data.RepuestosDao
import com.example.repuestosconejo.data.VehiculosDao
import com.example.repuestosconejo.model.Repuestos
import com.example.repuestosconejo.model.Vehiculos

class RepuestosRepository(private val repuestosDao: RepuestosDao) {

     fun saveRepuestos(repuestos: Repuestos) {
        repuestosDao.saveRepuestos(repuestos)

    }
     fun deleteRepuestos(repuestos: Repuestos) {
        repuestosDao.deleteRepuestos(repuestos)
    }
    val getRepuestos : LiveData<List<Repuestos>> = repuestosDao.getRepuestos()
}

