package com.example.manaforge.model

import java.io.Serializable

data class ImageUris(
    val art_crop: String,
    val border_crop: String,
    val large: String,
    val normal: String,
    val png: String,
    val small: String
): Serializable