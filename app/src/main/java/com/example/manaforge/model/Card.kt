package com.example.manaforge.model

import java.io.Serializable

data class Card(
    val id: String,
    val oracle_id: String,
    val multiverse_ids: List<Int>,
    val name: String,
    val printed_name: String,
    val lang: String,
    val released_at: String,
    val uri: String,
    val scryfall_uri: String,
    val layout: String,
    val highres_image: Boolean,
    val image_status: String,
    val  image_uris: ImageUris,
    val mana_cost: String,
    val cmc: Double,
    val type_line: String,
    val printed_type_line: String,
    val oracle_text: String,
    val printed_text: String,
    val power: String,
    val toughness: String,
    val colors: List<String>,
    val color_identity: List<String>,
    val keywords: List<String>,
    val legalities: Legalities,
    val games: List<String>,
    val reserved: Boolean,
    val foil: Boolean,
    val nonfoil: Boolean,
    val finishes: List<String>,
    val oversized: Boolean,
    val promo: Boolean,
    val reprint: Boolean,
    val variation: Boolean,
    val set_id: String,
    val set: String,
    val set_name: String,
    val set_type: String,
    val set_uri: String,
    val set_search_uri: String,
    val scryfall_set_uri: String,
    val rulings_uri: String,
    val prints_search_uri: String,
    val collector_number: String,
    val digital: Boolean,
    val rarity: String,
    val card_back_id: String,
    val artist: String,
    val artist_ids: List<String>,
    val illustration_id: String,
    val related_uris:RelatedUris,
    val purchase_uris:PurchaseUris,
    var prices:Prices
): Serializable