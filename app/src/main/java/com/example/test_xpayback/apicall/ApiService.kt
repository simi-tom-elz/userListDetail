package com.example.test_xpayback.apicall

import com.example.test_xpayback.model.User
import com.example.test_xpayback.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(@Query("limit") limit: Int, @Query("skip") skip: Int): UserResponse

    @GET("users/{id}")
    suspend fun getUserDetail(@Path("id") userId: Int): User
}
