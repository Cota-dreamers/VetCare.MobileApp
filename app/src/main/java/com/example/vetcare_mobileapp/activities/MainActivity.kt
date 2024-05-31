package com.example.vetcare_mobileapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
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

        val ivEmergency = findViewById<ImageView>(com.example.vetcare_mobileapp.R.id.ivEmergency)
        val ivAdvice = findViewById<ImageView>(com.example.vetcare_mobileapp.R.id.ivAdvice)
        val ivShop = findViewById<ImageView>(com.example.vetcare_mobileapp.R.id.ivShop)
        val ivUbication = findViewById<ImageView>(com.example.vetcare_mobileapp.R.id.ivUbication)

        ivAdvice.setOnClickListener { // Crear un Intent para iniciar ListCareAdviceActivity
            val intent = Intent(
                this@MainActivity,
                ListCareAdviceActivity::class.java
            )
            // Iniciar la actividad ListCareAdviceActivity
            startActivity(intent)
        }


        ivUbication.setOnClickListener { // Crear un Intent para iniciar ListCareAdviceActivity
            val intent = Intent(
                this@MainActivity,
                MapsUbication::class.java
            )
            // Iniciar la actividad ListCareAdviceActivity
            startActivity(intent)
        }


        ivEmergency.setOnClickListener { // Crear un Intent para iniciar EmergencyActivity
            val intent = Intent(
                this@MainActivity,
                EmergencyActivity::class.java
            )
            // Iniciar la actividad EmergencyActivity
            startActivity(intent)
        }

        ivShop.setOnClickListener{
            val intent = Intent(
                this@MainActivity,
                ListProductActivity::class.java
            )
            startActivity(intent)
        }



    }

}