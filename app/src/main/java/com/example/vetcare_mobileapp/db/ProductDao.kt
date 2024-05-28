package com.example.vetcare_mobileapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vetcare_mobileapp.models.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM PRODUCT")
    suspend fun getAllProducts():List<Product>

    @Query("SELECT * FROM PRODUCT WHERE isFavourite = 1")
    suspend fun getAllFavouriteProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

}