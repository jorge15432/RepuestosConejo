package com.example.repuestosconejo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repuestos(

    var id: String,
    val nombre: String?,
    val descripcion: String?,
    val cantidad: String?,
    val precio: String?


    ) : Parcelable{
        constructor():
        this("",
            "",
            "",
            "",
            "")

    }
