package com.example.repuestosconejo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pedidos(

    var id: String,
    val nombre: String?,
    val apellido1: String?,
    val apellido2: String?,
    val fecha: String?,
    val direccion: String?,
    val precio: String?

    ) : Parcelable{
        constructor():
        this("",
            "",
            "",
            "",
            "",
            "",
            "")

    }

