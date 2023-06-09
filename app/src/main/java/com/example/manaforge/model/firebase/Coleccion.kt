package com.example.manaforge.model.firebase

import java.io.Serializable

data class Coleccion(
    var idColeccion:String = "",
    val nombre: String = "",
    val idUsuario: String = "",
    val cartas: List<DatosCartaColeccionFirebase> = emptyList()
): Serializable