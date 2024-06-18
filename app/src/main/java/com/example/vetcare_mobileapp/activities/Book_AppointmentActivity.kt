package com.example.vetcare_mobileapp.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vetcare_mobileapp.R
import java.util.Calendar

class BookAppointmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)

        val btnDate: Button = findViewById(R.id.btnDate)
        val tvSelectedDate: TextView = findViewById(R.id.tvSelectedDate)
        val btnTime: Button = findViewById(R.id.btnTime)
        val tvSelectedTime: TextView = findViewById(R.id.tvSelectedTime)
        val btnSave: Button = findViewById(R.id.btnSave)

        btnDate.setOnClickListener {
            showDatePickerDialog(tvSelectedDate)
        }

        btnTime.setOnClickListener {
            showTimePickerDialog(tvSelectedTime)
        }

        btnSave.setOnClickListener {
            // Aquí puedes agregar la lógica para guardar la reserva
        }

        btnSave.setOnClickListener {
            // Mostrar mensaje al hacer clic en el botón
            showToast("Reserva Realizada :)")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    private fun showDatePickerDialog(tvSelectedDate: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.text = date
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun showTimePickerDialog(tvSelectedTime: TextView) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                val time = String.format("%02d:%02d", selectedHour, selectedMinute)
                tvSelectedTime.text = time
            },
            hour, minute, true
        )
        timePickerDialog.show()
    }
}