package com.example.vetcare_mobileapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vetcare_mobileapp.models.Product


@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AppDatabase2: RoomDatabase(){
    abstract fun productDao(): ProductDao

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
                    "product.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}