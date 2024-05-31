package com.example.vetcare_mobileapp.network

import com.example.vetcare_mobileapp.db.NearbySearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GooglePlacesApiService {
    @GET("maps/api/place/nearbysearch/json")
    fun getNearbyPlaces(
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("type") type: String,
        @Query("key") apikey: String
    ): Call<NearbySearchResponse>
}