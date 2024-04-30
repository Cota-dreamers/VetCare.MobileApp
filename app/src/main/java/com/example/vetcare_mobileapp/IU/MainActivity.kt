package com.example.vetcare_mobileapp.IU

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(com.example.vetcare_mobileapp.R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(com.example.vetcare_mobileapp.R.id.btVet)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btLeft = findViewById<ImageView>(com.example.vetcare_mobileapp.R.id.btLeft)
        val btconfig = findViewById<ImageView>(com.example.vetcare_mobileapp.R.id.btconfig)
        val ivEmergency = findViewById<ImageView>(com.example.vetcare_mobileapp.R.id.ivEmergency)

        // Crear un Intent para iniciar EmergencyActivity
        ivEmergency.setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                EmergencyActivity::class.java
            )
            // Iniciar la actividad EmergencyActivity
            startActivity(intent)
        }

        btconfig.setOnClickListener{
            val intent = Intent(
                this@MainActivity,
                Conf_Users::class.java
            )
        }

        btLeft.setOnClickListener{
            Toast.makeText(this@MainActivity, "Estas en el Inicio!!", Toast.LENGTH_SHORT).show()
        }

    }

}