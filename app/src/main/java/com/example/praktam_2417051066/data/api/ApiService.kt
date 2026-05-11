package com.example.praktam_2417051066.data.api

import com.example.praktam_2417051066.data.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("gistfile1.txt")
    suspend fun getProducts(): List<Product>
}
