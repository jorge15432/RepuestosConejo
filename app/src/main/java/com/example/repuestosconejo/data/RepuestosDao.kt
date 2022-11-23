package com.example.repuestosconejo.data


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.repuestosconejo.model.Repuestos

@Dao
interface RepuestosDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRepuestos(repuestos: Repuestos)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateRepuestos(repuestos: Repuestos)

    @Delete
    suspend fun deleteRepuestos(repuestos: Repuestos)

    @Query ("SELECT * FROM repuestos")
    fun getRepuestos() : LiveData<List<Repuestos>>
}

