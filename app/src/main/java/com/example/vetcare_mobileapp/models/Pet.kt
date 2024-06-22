package com.example.vetcare_mobileapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Pet (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val breed: String,
    val weight: Float,
    val type: String,
    val photoUrl: String,
)