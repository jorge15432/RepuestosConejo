package com.example.repuestosconejo.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="vehiculos")
data class Vehiculos(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name="marca")
    val marca: String?,
    @ColumnInfo(name="año")
    val año: String?,
    @ColumnInfo(name="modelo")
    val modelo: String?,
    @ColumnInfo(name="motor")
    val motor: String?


) : Parcelable

