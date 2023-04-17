package com.example.testretrofit.model.card

import com.example.manaforge.model.Card
import java.io.Serializable

data class CardList (
    val `object`: String,
    val total_cards: Int,
    val has_more: Boolean,
    val data: List<Card>
): Serializable