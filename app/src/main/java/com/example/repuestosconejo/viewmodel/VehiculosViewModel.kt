package com.example.repuestosconejo.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.repuestosconejo.data.RepuestosConejoDatabase
import com.example.repuestosconejo.model.Vehiculos
import com.example.repuestosconejo.repository.VehiculosRepository
import kotlinx.coroutines.launch

class VehiculosViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: VehiculosRepository
    val getVehiculos: LiveData<List<Vehiculos>>
    init {
        val vehiculosDao = RepuestosConejoDatabase.getDatabase(application).vehiculosDao()
        repository = VehiculosRepository(vehiculosDao)
        getVehiculos = repository.getVehiculos
    }
     fun saveVehiculos(vehiculos: Vehiculos){
        viewModelScope.launch { repository.saveVehiculos(vehiculos) }
    }

     fun deleteVehiculos(vehiculos: Vehiculos) {
        viewModelScope.launch { repository.deleteVehiculos(vehiculos) }
    }

}



