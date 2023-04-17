package com.example.manaforge.model

import java.io.Serializable

data class RelatedUris(
    val edhrec: String,
    val gatherer: String,
    val tcgplayer_infinite_articles: String,
    val tcgplayer_infinite_decks: String
): Serializable