package com.example.vetcare_mobileapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vetcare_mobileapp.R


class ProfileUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_user)



        // Leer los datos del usuario desde SharedPreferences
        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val firstName = sharedPref.getString("firstName", "Nombre no disponible")
        val lastName = sharedPref.getString("lastName", "Apellido no disponible")
        val email = sharedPref.getString("email", "Correo no disponible")

        // Logs para depuración
        Log.d("ProfileUser", "First Name: $firstName")
        Log.d("ProfileUser", "Last Name: $lastName")
        Log.d("ProfileUser", "Email: $email")

        // Actualizar los TextViews con los datos del usuario
        findViewById<TextView>(R.id.tvProfileName).text = firstName
        findViewById<TextView>(R.id.etProfileLastName).text = lastName
        findViewById<TextView>(R.id.tvProfileCorreo).text = email
        val btMascota = findViewById<Button>(R.id.btMascota)
        btMascota.setOnClickListener {
            val intent = Intent(this@ProfileUser, ListPetActivity::class.java)
            startActivity(intent)
        }
    }
}