package com.example.vetcare_mobileapp.network

import com.example.vetcare_mobileapp.models.FriendResponse
import com.example.vetcare_mobileapp.models.User
import retrofit2.Call
import retrofit2.http.GET

interface FriendService {
    @GET("?results=10")
    fun getFriend(): Call<FriendResponse>
}