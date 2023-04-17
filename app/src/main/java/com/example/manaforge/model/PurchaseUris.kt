package com.example.manaforge.model

import java.io.Serializable

data class PurchaseUris(
    val cardhoarder: String,
    val cardmarket: String,
    val tcgplayer: String
): Serializable