package com.example.vetcare_mobileapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppointmentsDao {
    @Query("SELECT * FROM appointments WHERE user_id = :userId")
    fun getUserAppointments(userId: Int): List<Appointments>

    @Insert
    fun insertAppointment(appointments: Appointments)

    @Delete
    fun deleteAppointment(appointments: Appointments)
}
