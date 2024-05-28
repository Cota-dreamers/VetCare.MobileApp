package com.example.vetcare_mobileapp.network

import com.example.vetcare_mobileapp.communication.ApiResponse
import com.example.vetcare_mobileapp.communication.CareAdviceResponse
import retrofit2.Call
import retrofit2.http.GET

interface CareAdviceService {
    @GET("api/v1/advices")
    fun getAllCareAdvices(): Call<List<CareAdviceResponse>>
}