package com.example.vetcare_mobileapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.ScheduleAppointmentActivity

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
        val ivReserva = findViewById<Button>(com.example.vetcare_mobileapp.R.id.btReserva)

        ivReserva.setOnClickListener { // Crear un Intent para iniciar ListCareAdviceActivity
            val intent = Intent(
                this,
                ScheduleAppointmentActivity::class.java
            )
            // Iniciar la actividad ListCareAdviceActivity
            startActivity(intent)
        }
    }

}