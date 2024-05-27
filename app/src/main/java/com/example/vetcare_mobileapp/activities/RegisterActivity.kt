package com.example.vetcare_mobileapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vetcare_mobileapp.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btIngresar = findViewById<Button>(com.example.vetcare_mobileapp.R.id.btRegister)

        btIngresar.setOnClickListener { // Crear un Intent para iniciar
            val intent = Intent(
                this@RegisterActivity,
                MainActivity::class.java
            )
            // Iniciar la actividad
            startActivity(intent)
        }
    }
}