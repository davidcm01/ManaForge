package com.example.manaforge.model

import java.io.Serializable

data class Prices(
    val eur: String,
    val eur_foil: String,
    val tix: String,
    val usd: String,
    val usd_etched: Any,
    val usd_foil: String
): Serializable