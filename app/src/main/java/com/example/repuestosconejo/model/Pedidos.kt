package com.example.repuestosconejo.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="pedidos")
data class Pedidos(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name="nombre")
    val nombre: String?,
    @ColumnInfo(name="apellido1")
    val apellido1: String?,
    @ColumnInfo(name="apellido2")
    val apellido2: String?,
    @ColumnInfo(name="fecha")
    val fecha: String?,
    @ColumnInfo(name="direccion")
    val direccion: String?,
    @ColumnInfo(name="precio")
val precio: String?
    ) : Parcelable

//falta agregar iamgen