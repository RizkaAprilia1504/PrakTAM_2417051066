package com.example.praktam_2417051066.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("nama")
    val nama: String,
    @SerializedName("deskripsi")
    val deskripsi: String,
    @SerializedName("harga")
    val harga: Int,
    @SerializedName("image_url")
    val imageUrl: String
)