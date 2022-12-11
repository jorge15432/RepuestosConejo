package com.example.repuestosconejo.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.example.repuestosconejo.data.PedidosDao
import com.example.repuestosconejo.model.Pedidos
import com.example.repuestosconejo.repository.PedidosRepository
import kotlinx.coroutines.launch

class PedidosViewModel(application: Application) : AndroidViewModel(application) {


    private val repository: PedidosRepository = PedidosRepository(PedidosDao())
    val getPedidos = repository.getPedidos



    fun savePedidos(pedidos: Pedidos){
        viewModelScope.launch { repository.savePedidos(pedidos) }
    }

    fun deletePedidos(pedidos: Pedidos) {
        viewModelScope.launch { repository.deletePedidos(pedidos) }
    }

}
