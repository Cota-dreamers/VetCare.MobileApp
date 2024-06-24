package com.example.vetcare_mobileapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vetcare_mobileapp.R


class ProfileUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile_user)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btVet)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btMascota = findViewById<Button>(com.example.vetcare_mobileapp.R.id.btMascota)

        btMascota.setOnClickListener { // Crear un Intent para iniciar ProfilePet
            val intent = Intent(
                this@ProfileUser,
                ListPetActivity::class.java
            )
            // Iniciar la actividad ProfilePet
            startActivity(intent)
        }
    }
}