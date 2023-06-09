package com.example.manaforge.model.firebase

import java.io.Serializable

data class DatosCartaColeccionFirebase(
    val id: String = "",
    val idioma: String = "",
    val nombre:String = "",
    val imagen:String = "",
    val cantidad: Int = 0
): Serializable {
}