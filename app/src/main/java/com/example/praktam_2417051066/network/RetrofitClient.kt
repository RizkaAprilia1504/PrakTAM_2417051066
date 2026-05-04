package com.example.praktam_2417051066.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://gist.githubusercontent.com/RizkaAprilia1504/0a8bb400f290cd29eb1064922d6a87cd/raw/66468f7629b517958ae751ad992fcd1b3b686de7/" 

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
