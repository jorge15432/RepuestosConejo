package com.example.repuestosconejo.repository

import androidx.lifecycle.LiveData
import com.example.repuestosconejo.data.PedidosDao
import com.example.repuestosconejo.data.VehiculosDao
import com.example.repuestosconejo.model.Pedidos
import com.example.repuestosconejo.model.Vehiculos

class PedidosRepository(private val pedidosDao: PedidosDao) {

     fun savePedidos(pedidos: Pedidos) {
        pedidosDao.savePedidos(pedidos)

    }
     fun deletePedidos(pedidos: Pedidos) {
        pedidosDao.deletePedidos(pedidos)
    }
    val getPedidos : LiveData<List<Pedidos>> = pedidosDao.getPedidos()
}

