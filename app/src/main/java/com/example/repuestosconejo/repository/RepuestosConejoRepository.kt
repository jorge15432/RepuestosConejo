package com.example.repuestosconejo.repository

import androidx.lifecycle.LiveData
import com.example.repuestosconejo.data.RepuestosConejoDao
import com.example.repuestosconejo.model.RepuestosConejo

class RepuestosConejoRepository(private val repuestosconejoDao: RepuestosConejoDao) {
    suspend fun addrepuestosconejo(repuestosconejo: RepuestosConejo) {
        repuestosconejoDao.addRepuestosConejo(repuestosconejo)
    }
    suspend fun updaterepuestosconejo(repuestosconejo: RepuestosConejo) {
        repuestosconejoDao.updateRepuestosConejo(repuestosconejo)
    }
    suspend fun deleterepuestosconejo(repuestosconejo: RepuestosConejo) {
        repuestosconejoDao.deleteRepuestosConejo(repuestosconejo)
    }
    val getRepuestosConejo : LiveData<List<RepuestosConejo>> = repuestosconejoDao.getRepuestosConejo()
}

