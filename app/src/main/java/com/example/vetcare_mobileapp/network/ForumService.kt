package com.example.vetcare_mobileapp.network

import com.example.vetcare_mobileapp.models.ForumPost
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface ForumService {
    @GET("forum/highlighted")
    fun getHighlightedPosts(): Call<List<ForumPost>>

    @GET("forum/recent")
    fun getRecentPosts(): Call<List<ForumPost>>

    @POST("forum/new")
    fun createPost(@Body post: ForumPost): Call<ForumPost>
}
