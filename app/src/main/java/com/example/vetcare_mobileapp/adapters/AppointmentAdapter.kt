package com.example.vetcare_mobileapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.models.Appointment

class AppointmentsAdapter(private val appointmentsList: List<Appointment>) :
    RecyclerView.Adapter<AppointmentsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAppointmentDetails: TextView = itemView.findViewById(R.id.tvAppointmentDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_appointment, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = appointmentsList[position]
        holder.tvAppointmentDetails.text = "Tiene una cita pendiente para el día ${appointment.date} a la ${appointment.time} para su mascota ${appointment.petName}"
    }

    override fun getItemCount(): Int {
        return appointmentsList.size
    }
}
