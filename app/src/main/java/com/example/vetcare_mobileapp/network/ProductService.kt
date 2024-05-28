package com.example.vetcare_mobileapp.network


import com.example.vetcare_mobileapp.communication.ProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {
    @GET("api/v1/products")
    fun getAllProducts(): Call<List<ProductResponse>>

}