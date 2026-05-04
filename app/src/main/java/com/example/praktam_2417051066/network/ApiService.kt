package com.example.praktam_2417051066.network

import com.example.praktam_2417051066.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("gistfile1.txt")
    suspend fun getProducts(): List<Product>
}
