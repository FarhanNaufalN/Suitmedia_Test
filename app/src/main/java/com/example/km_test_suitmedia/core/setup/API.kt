package com.example.km_test_suitmedia.core.setup

import com.example.km_test_suitmedia.core.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface API {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int?
    ): UserResponse
}
