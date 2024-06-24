package com.example.vetcare_mobileapp.network


import com.example.vetcare_mobileapp.communication.PetResponse
import com.example.vetcare_mobileapp.models.Pet
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface PetService {
    @GET("api/v1/pets")
    fun getAllPets(): Call<List<PetResponse>>

    @POST("api/v1/pets")
    @Headers("Content-Type: application/json", "accept: */*")
    fun addPet(@Body pet: Pet): Call<PetResponse>
}