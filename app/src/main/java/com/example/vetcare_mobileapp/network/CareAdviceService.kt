package com.example.vetcare_mobileapp.network

import com.example.vetcare_mobileapp.communication.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface CareAdviceService {
    @GET("careadvice")
    fun getAllCareAdvices(): Call<ApiResponse>
}