package com.example.vetcare_mobileapp.IU

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vetcare_mobileapp.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btLogin = findViewById<Button>(com.example.vetcare_mobileapp.R.id.btInicioSesion)
        btLogin.setOnClickListener { // Crear un Intent para iniciar
            val intent = Intent(
                this@AuthActivity,
                LoginActivity::class.java
            )
            // Iniciar la actividad
            startActivity(intent)
        }

        val btRegistrarse = findViewById<Button>(com.example.vetcare_mobileapp.R.id.btRegistrarse)
        btRegistrarse.setOnClickListener{
            val intent = Intent(
                this@AuthActivity,
                RegisterActivity::class.java
            )
            // Iniciar la actividad
            startActivity(intent)
        }



    }


}