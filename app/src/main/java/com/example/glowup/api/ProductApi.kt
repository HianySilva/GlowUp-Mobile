// ProductApi.kt
package com.example.glowup.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("products.json")
    fun getProducts(
        @Query("product_type") productType: String? = null
    ): Call<List<ApiProduct>>
}