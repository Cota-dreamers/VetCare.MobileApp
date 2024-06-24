package com.example.vetcare_mobileapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.vetcare_mobileapp.models.Appointment
import android.content.ContentValues


class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "VetCareDB"
        private const val TABLE_APPOINTMENTS = "Appointments"

        private const val KEY_ID = "id"
        private const val KEY_DATE = "date"
        private const val KEY_TIME = "time"
        private const val KEY_PET_NAME = "petName"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createAppointmentsTable = ("CREATE TABLE " + TABLE_APPOINTMENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATE + " TEXT,"
                + KEY_TIME + " TEXT," + KEY_PET_NAME + " TEXT" + ")")
        db.execSQL(createAppointmentsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_APPOINTMENTS")
        onCreate(db)
    }

    fun addAppointment(appointment: Appointment) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_DATE, appointment.date)
        contentValues.put(KEY_TIME, appointment.time)
        contentValues.put(KEY_PET_NAME, appointment.petName)

        db.insert(TABLE_APPOINTMENTS, null, contentValues)
        db.close()
    }

    fun getAllAppointments(): List<Appointment> {
        val appointmentList: ArrayList<Appointment> = ArrayList()
        val selectQuery = "SELECT  * FROM $TABLE_APPOINTMENTS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val appointment = Appointment(
                    cursor.getString(cursor.getColumnIndexOrThrow(KEY_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(KEY_TIME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(KEY_PET_NAME))
                )
                appointmentList.add(appointment)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return appointmentList
    }
}
