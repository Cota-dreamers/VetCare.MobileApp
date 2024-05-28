package com.example.vetcare_mobileapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Product (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val description: String,
    val price: Float,
    val image: String,
    val stock: Int,

    var isFavourite: Boolean = false
)