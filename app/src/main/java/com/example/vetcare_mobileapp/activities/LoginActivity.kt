package com.example.vetcare_mobileapp.activities

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.api.ApiService
import com.example.vetcare_mobileapp.api.LoginRequest
import com.example.vetcare_mobileapp.api.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class LoginActivity : AppCompatActivity() {

    private val channelId = "veterinary_appointment_channel"
    private lateinit var etLoginUser: EditText
    private lateinit var etLoginPassword: EditText
    private lateinit var btIngresar: Button



    override fun onCreate(savedInstanceState: Bundle?) {

        createNotificationChannel()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etLoginUser = findViewById(R.id.etLoginUser)
        etLoginPassword = findViewById(R.id.etLoginPassword)
        btIngresar = findViewById(R.id.btIngresar)

        btIngresar.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = etLoginUser.text.toString().trim()
        val password = etLoginPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa todas las credenciales", Toast.LENGTH_SHORT).show()
            return
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://vetcare2.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val loginRequest = LoginRequest(email, password)

        apiService.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    showNotification()
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        // Guardar los datos del usuario en SharedPreferences
                        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                        with(sharedPref.edit()) {
                            putString("firstName", loginResponse.firstName)
                            putString("lastName", loginResponse.lastName)
                            putString("email", loginResponse.email)
                            putString("token", loginResponse.token)
                            apply()
                        }

                        // Pasar a la siguiente actividad
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error de red", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "VetCare Notifications"
            val descriptionText = "Channel for VetCare reminders"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, name,importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

    private fun showNotification() {
        val intent = Intent(this,MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this,channelId)
            .setSmallIcon(R.drawable.icon_veterinary)
            .setContentTitle("Cita Veterinaria")
            .setContentText("Tienes una cita programada en 2 días")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            if(ActivityCompat.checkSelfPermission(
                this@LoginActivity,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
                ) {
                notify(1,builder.build())
            }
        }

    }
}