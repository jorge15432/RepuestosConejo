package com.example.repuestosconejo.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="repuestos")
data class Repuestos(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name="nombre")
    val nombre: String?,
    @ColumnInfo(name="descripcion")
    val descripcion: String,
    @ColumnInfo(name="cantidad")
    val cantidad: String,

    ) : Parcelable

//falta agregar iamgen