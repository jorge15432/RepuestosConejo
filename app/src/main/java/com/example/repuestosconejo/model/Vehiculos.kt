package com.example.repuestosconejo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vehiculos(

    var id: String,
    val marca: String?,
    val año: String?,
    val modelo: String?,
    val motor: String?


) : Parcelable{
    constructor():
            this("",
                "",
                "",
                "",
                "")
}
