package com.example.glowup.api

import com.google.gson.annotations.SerializedName

data class ApiProduct(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("brand")
    val brand: String?,

    @SerializedName("category")
    val category: String?,

    @SerializedName("price")
    val price: String?,

    @SerializedName("api_featured_image")
    val imageUrl: String?,

    @SerializedName("description")
    val description: String?
)
