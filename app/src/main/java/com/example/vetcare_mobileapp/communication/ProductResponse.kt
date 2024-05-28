package com.example.vetcare_mobileapp.communication

import com.example.vetcare_mobileapp.models.Product

class ProductResponse(
    private var name: String,
    private var description: String,
    private var price: Float,
    private var image: String,
    private var stock: Int,
) {
    fun toProduct(): Product {
        return Product(
            name = name,
            description = description,
            price = price,
            image = image,
            stock = stock
        )
    }
}