package com.example.vetcare_mobileapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.vetcare_mobileapp.db.AppDatabase3
import com.example.vetcare_mobileapp.db.Appointments

class ScheduleAppointmentActivity : AppCompatActivity() {
    private lateinit var editTextDate: EditText
    private lateinit var editTextTime: EditText
    private lateinit var spinnerDoctor: Spinner
    private lateinit var buttonScheduleAppointment: Button
    private lateinit var db: AppDatabase3
    private val userId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_appointment)

        editTextDate = findViewById(R.id.editTextDate)
        editTextTime = findViewById(R.id.editTextTime)
        spinnerDoctor = findViewById(R.id.spinnerDoctor)
        buttonScheduleAppointment = findViewById(R.id.buttonScheduleAppointment)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase3::class.java, "vetcare_database"
        ).allowMainThreadQueries().build()


        val doctors = listOf("Dr. Smith", "Dr. Johnson", "Dr. Williams")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, doctors)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDoctor.adapter = adapter

        buttonScheduleAppointment.setOnClickListener { scheduleAppointment() }
    }

    private fun scheduleAppointment() {
        val date = editTextDate.text.toString()
        val time = editTextTime.text.toString()
        val doctorName = spinnerDoctor.selectedItem.toString()

        if (date.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "La fecha y la hora son obligatorias", Toast.LENGTH_SHORT).show()
            return
        }

        val appointments = Appointments(
            userId = userId,
            date = date,
            time = time,
            doctorName = doctorName
        )

        db.appointmentDao().insertAppointment(appointments)
        Toast.makeText(this, "Consulta programada exitosamente", Toast.LENGTH_SHORT).show()
        finish()
    }
}
