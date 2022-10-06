package com.example.repuestosconejo.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.repuestosconejo.data.RepuestosConejoDao
import com.example.repuestosconejo.data.RepuestosConejoDatabase
import com.example.repuestosconejo.model.RepuestosConejo
import com.example.repuestosconejo.repository.RepuestosConejoRepository
import kotlinx.coroutines.launch

class RepuestosConejoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: RepuestosConejoRepository
    val getRepuestosConejo: LiveData<List<RepuestosConejo>>
    init {
        val repuestosconejoDao = RepuestosConejoDatabase.getDatabase(application).repuestosconejoDao()
        repository = RepuestosConejoRepository(repuestosconejoDao)
        getRepuestosConejo = repository.getRepuestosConejo
    }
    suspend fun addRepuestosConejo(repuestosconejo: RepuestosConejo) {
        viewModelScope.launch { repository.addrepuestosconejo(repuestosconejo) }
    }
    suspend fun updateRepuestosConejo(repuestosconejo: RepuestosConejo) {
        viewModelScope.launch { repository.updaterepuestosconejo(repuestosconejo) }
    }
    suspend fun deleteRepuestosConejo(repuestosconejo: RepuestosConejo) {
        viewModelScope.launch { repository.deleterepuestosconejo(repuestosconejo) }
    }
}

