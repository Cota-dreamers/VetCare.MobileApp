package com.example.vetcare_mobileapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CareAdvice (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val strAdviceThumb: String,
    val strAdvice: String,
    val strAdviceCategory: String,
    val strAdviceDescription: String,

    var isFavourite: Boolean = false
)