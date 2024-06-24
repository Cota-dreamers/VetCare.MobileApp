package com.example.vetcare_mobileapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.adapters.AppointmentsAdapter
import com.example.vetcare_mobileapp.database.DatabaseHandler

class ViewAppointmentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_appointments)

        val dbHandler = DatabaseHandler(this)
        val appointments = dbHandler.getAllAppointments()

        val rvAppointments: RecyclerView = findViewById(R.id.rvAppointments)
        rvAppointments.layoutManager = LinearLayoutManager(this)
        rvAppointments.adapter = AppointmentsAdapter(appointments)
    }
}
