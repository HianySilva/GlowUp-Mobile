package com.example.glowup.api

import retrofit2.Call
import retrofit2.http.GET

interface ProductApi {
    @GET("products.json")
    fun getProducts(): Call<List<ApiProduct>>

}


