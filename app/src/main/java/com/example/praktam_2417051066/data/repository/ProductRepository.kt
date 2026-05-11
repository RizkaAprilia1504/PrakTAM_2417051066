package com.example.praktam_2417051066.data.repository

import com.example.praktam_2417051066.data.api.RetrofitClient
import com.example.praktam_2417051066.data.model.Product

class ProductRepository {
    suspend fun getProducts(): List<Product> {
        return try {
            RetrofitClient.instance.getProducts()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
