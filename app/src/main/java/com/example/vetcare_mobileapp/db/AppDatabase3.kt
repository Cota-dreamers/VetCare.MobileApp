package com.example.vetcare_mobileapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vetcare_mobileapp.models.Pet


@Database(entities = [Pet::class], version = 1, exportSchema = false)
abstract class AppDatabase3 : RoomDatabase(){
    abstract fun petDao(): PetDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase3? = null

        fun getDatabase(context: Context): AppDatabase3{
            val temp = INSTANCE
            if(temp != null){
                return temp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase3::class.java,
                    "pet.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

