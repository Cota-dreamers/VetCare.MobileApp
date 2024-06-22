package com.example.vetcare_mobileapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vetcare_mobileapp.AssistanceActivity
import com.example.vetcare_mobileapp.R

class ConsultMedicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_consult_medic)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btVet)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btReserva = findViewById<Button>(com.example.vetcare_mobileapp.R.id.btReserva)
        val imReserva = findViewById<ImageView>(com.example.vetcare_mobileapp.R.id.imReserva)
        val btAsistencia = findViewById<Button>(com.example.vetcare_mobileapp.R.id.btAsistencia)
        val imAsistencia = findViewById<ImageView>(com.example.vetcare_mobileapp.R.id.imAsistencia)

        btReserva.setOnClickListener { // Crear un Intent para iniciar ListCareAdviceActivity
            val intent = Intent(
                this@ConsultMedicActivity,
                BookAppointmentActivity::class.java
            )
            // Iniciar la actividad ListCareAdviceActivity
            startActivity(intent)
        }

        imReserva.setOnClickListener { // Crear un Intent para iniciar ConsultMedicActivity
            val intent = Intent(
                this@ConsultMedicActivity,
                BookAppointmentActivity::class.java
            )
            // Iniciar la actividad ConsultMedicActivity
            startActivity(intent)
        }

        btAsistencia.setOnClickListener { // Crear un Intent para iniciar ListCareAdviceActivity
            val intent = Intent(
                this@ConsultMedicActivity,
                AssistanceActivity::class.java
            )
            // Iniciar la actividad ListCareAdviceActivity
            startActivity(intent)
        }

        imAsistencia.setOnClickListener { // Crear un Intent para iniciar ConsultMedicActivity
            val intent = Intent(
                this@ConsultMedicActivity,
                AssistanceActivity::class.java
            )
            // Iniciar la actividad ConsultMedicActivity
            startActivity(intent)
        }
    }
}