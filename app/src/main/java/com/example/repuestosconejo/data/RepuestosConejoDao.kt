package com.example.repuestosconejo.data


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.repuestosconejo.model.RepuestosConejo

@Dao
interface RepuestosConejoDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRepuestosConejo(repuestosconejo: RepuestosConejo)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateRepuestosConejo(repuestosconejo: RepuestosConejo)

    @Delete
    suspend fun deleteRepuestosConejo(repuestosconejo: RepuestosConejo)

    @Query ("SELECT * FROM repuestosconejo")
    fun getRepuestosConejo() : LiveData<List<RepuestosConejo>>
}

