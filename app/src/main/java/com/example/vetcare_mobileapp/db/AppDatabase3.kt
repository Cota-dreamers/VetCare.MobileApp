package com.example.vetcare_mobileapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Appointments::class], version = 1, exportSchema = false)
abstract class AppDatabase3 : RoomDatabase() {
    abstract fun appointmentDao(): AppointmentsDao
}
