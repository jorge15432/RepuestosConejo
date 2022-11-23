package com.example.repuestosconejo.data


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.repuestosconejo.model.Vehiculos

@Dao
interface VehiculosDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addVehiculos(vehiculos: Vehiculos)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateVehiculos(vehiculos: Vehiculos)

    @Delete
    suspend fun deleteVehiculos(vehiculos: Vehiculos)

    @Query ("SELECT * FROM vehiculos")
    fun getVehiculos() : LiveData<List<Vehiculos>>
}

