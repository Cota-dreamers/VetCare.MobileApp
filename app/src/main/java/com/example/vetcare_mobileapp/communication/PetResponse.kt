package com.example.vetcare_mobileapp.communication

import com.example.vetcare_mobileapp.models.Pet

class PetResponse(
    private var name: String,
    private var breed: String,
    private var weight: Float,
    private var type: String,
    private var photoUrl: String,
) {
    fun toPet(): Pet {
        return Pet(
            name = name,
            breed = breed,
            weight = weight,
            type = type,
            photoUrl = photoUrl
        )
    }
}