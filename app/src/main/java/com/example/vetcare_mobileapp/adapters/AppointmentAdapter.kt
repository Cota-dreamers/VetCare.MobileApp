package com.example.vetcare_mobileapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.models.Appointment

class AppointmentsAdapter(private val appointments: List<Appointment>) :
    RecyclerView.Adapter<AppointmentsAdapter.AppointmentViewHolder>() {

    class AppointmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPetName: TextView = view.findViewById(R.id.tvPetName)
        val tvAppointmentDate: TextView = view.findViewById(R.id.tvAppointmentDate)
        val tvAppointmentTime: TextView = view.findViewById(R.id.tvAppointmentTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_appointment, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointments[position]
        holder.tvPetName.text = "Nombre de la mascota: ${appointment.petName}"
        holder.tvAppointmentDate.text = "Fecha de cita: ${appointment.date}"
        holder.tvAppointmentTime.text = "Hora de la cita: ${appointment.time}"
    }

    override fun getItemCount(): Int {
        return appointments.size
    }
}
