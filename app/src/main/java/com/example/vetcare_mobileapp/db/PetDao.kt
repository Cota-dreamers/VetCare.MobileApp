package com.example.vetcare_mobileapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vetcare_mobileapp.models.Pet

@Dao
interface PetDao {
    @Query("SELECT * FROM PET")
    suspend fun getAllPets():List<Pet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(product: Pet)

    @Delete
    suspend fun deletePet(product: Pet)

}