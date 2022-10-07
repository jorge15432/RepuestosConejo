package com.example.repuestosconejo.repository

import androidx.lifecycle.LiveData
import com.example.repuestosconejo.data.RepuestosConejoDao
import com.example.repuestosconejo.model.RepuestosConejo

class RepuestosConejoRepository(private val repuestosconejoDao: RepuestosConejoDao) {
    suspend fun saveRepuestosConejo(repuestosconejo: RepuestosConejo) {
    if (repuestosconejo.id==0){
        repuestosconejoDao.addRepuestosConejo(repuestosconejo)

    }else {
        repuestosconejoDao.updateRepuestosConejo(repuestosconejo)

    }
    }
    suspend fun deleteRepuestosconejo(repuestosconejo: RepuestosConejo) {
        repuestosconejoDao.deleteRepuestosConejo(repuestosconejo)
    }
    val getRepuestosConejo : LiveData<List<RepuestosConejo>> = repuestosconejoDao.getRepuestosConejo()
}

