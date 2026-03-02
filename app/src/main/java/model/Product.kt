package com.example.praktam_2417051066.model

import androidx.annotation.DrawableRes

data class Product(
    val nama: String,
    val deskripsi: String,
    val harga: Int,
    @DrawableRes val imageRes: Int
)