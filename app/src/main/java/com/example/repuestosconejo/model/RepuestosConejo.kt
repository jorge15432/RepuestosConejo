package com.example.repuestosconejo.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="repuestosconejo")
data class RepuestosConejo(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name="nombre")
    val nombre: String?,
    @ColumnInfo(name="marca")
    val marca: String,
    @ColumnInfo(name="modelo")
    val modelo: String,
    @ColumnInfo(name="codigo")
    val codigo: String?
) : Parcelable
