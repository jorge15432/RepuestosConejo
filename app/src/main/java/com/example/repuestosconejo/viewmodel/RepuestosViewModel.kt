package com.example.repuestosconejo.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.example.repuestosconejo.data.RepuestosDao
import com.example.repuestosconejo.model.Repuestos
import com.example.repuestosconejo.repository.RepuestosRepository
import kotlinx.coroutines.launch

class RepuestosViewModel(application: Application) : AndroidViewModel(application) {


    private val repository: RepuestosRepository = RepuestosRepository(RepuestosDao())
    val getRepuestos = repository.getRepuestos


    fun saveRepuestos(repuestos: Repuestos){
        viewModelScope.launch { repository.saveRepuestos(repuestos) }
    }

    fun deleteRepuestos(repuestos: Repuestos) {
        viewModelScope.launch { repository.deleteRepuestos(repuestos) }
    }

}
