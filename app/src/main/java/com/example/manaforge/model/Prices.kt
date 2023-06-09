package com.example.manaforge.model

import java.io.Serializable

data class Prices(
    val eur: String = "0",
    val eur_foil: String = "0",
    val tix: String = "0",
    val usd: String = "0",
    val usd_etched: Any = "0",
    val usd_foil: String = "0"
): Serializable