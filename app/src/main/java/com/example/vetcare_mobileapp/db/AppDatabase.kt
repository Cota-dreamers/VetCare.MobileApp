package com.example.vetcare_mobileapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vetcare_mobileapp.models.CareAdvice

@Database(entities = [CareAdvice::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){
    abstract fun careAdviceDao(): CareAdviceDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val temp = INSTANCE
            if(temp != null){
                return temp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "careadvice.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}