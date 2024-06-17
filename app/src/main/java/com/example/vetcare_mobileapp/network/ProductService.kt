package com.example.vetcare_mobileapp.network


import com.example.vetcare_mobileapp.communication.ProductResponse
import com.example.vetcare_mobileapp.models.Product
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductService {
    @GET("api/v1/products")
    fun getAllProducts(): Call<List<ProductResponse>>

    @POST("api/v1/products")
    fun addProduct(@Body product: Product): Call<ProductResponse>

}