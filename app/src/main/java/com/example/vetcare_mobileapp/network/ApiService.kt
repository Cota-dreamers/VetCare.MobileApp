package com.example.vetcare_mobileapp.network

import com.example.vetcare_mobileapp.models.ChatGPTRequest
import com.example.vetcare_mobileapp.models.ChatGPTResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    fun getChatGPTResponse(
        @Header("Authorization") authHeader: String,
        @Body request: ChatGPTRequest
    ): Call<ChatGPTResponse>

    companion object {
        const val BASE_URL = "https://api.openai.com/"
    }
}
