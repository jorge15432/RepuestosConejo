package com.example.repuestosconejo.data


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.repuestosconejo.model.Pedidos
import com.example.repuestosconejo.model.Vehiculos

@Dao
interface PedidosDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPedidos(pedidos: Pedidos)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updatePedidos(pedidos: Pedidos)

    @Delete
    suspend fun deletePedidos(pedidos: Pedidos)

    @Query ("SELECT * FROM pedidos")
    fun getPedidos() : LiveData<List<Pedidos>>
}

