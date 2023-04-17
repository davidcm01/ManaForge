package com.example.manaforge.model

import java.io.Serializable

data class Preview(
    val previewed_at: String,
    val source: String,
    val source_uri: String
): Serializable