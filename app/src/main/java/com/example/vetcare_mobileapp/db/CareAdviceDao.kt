package com.example.vetcare_mobileapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vetcare_mobileapp.models.CareAdvice

@Dao
interface CareAdviceDao {

    @Query("SELECT * FROM CAREADVICE")
    suspend fun getAllCareAdvices():List<CareAdvice>

    @Query("SELECT * FROM CAREADVICE WHERE isFavourite = 1")
    suspend fun getAllFavouriteCareAdvices(): List<CareAdvice>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCareAdvice(careAdvice: CareAdvice)

    @Delete
    suspend fun deleteCareAdvice(careAdvice: CareAdvice)
}