package com.example.km_test_suitmedia.core.setup

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Config {

    val BASE_URL = "https://reqres.in/api/"

    fun getRetrofitData(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}