package com.example.vetcare_mobileapp.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.api.ApiService
import com.example.vetcare_mobileapp.api.RegisterRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var cbAcceptTerms: CheckBox
    private lateinit var btRegister: Button
    private lateinit var tvTerms: TextView
    private lateinit var webViewTerms: WebView
    private lateinit var etNombre: EditText
    private lateinit var etApellido: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        cbAcceptTerms = findViewById(R.id.cbAcceptTerms)
        btRegister = findViewById(R.id.btRegister)
        tvTerms = findViewById(R.id.tvTerms)
        webViewTerms = findViewById(R.id.webViewTerms)
        etNombre = findViewById(R.id.etNombre)
        etApellido = findViewById(R.id.etApellido)
        etCorreo = findViewById(R.id.etCorreo)
        etPassword = findViewById(R.id.etPassword)

        btRegister.setOnClickListener {
            if (cbAcceptTerms.isChecked) {
                registerUser()
            } else {
                Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
            }
        }

        tvTerms.setOnClickListener {
            showTermsAndConditions()
        }

        webViewTerms.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                    return true
                }
                return false
            }
        }
    }


    private fun registerUser()  {
        val firstName = etNombre.text.toString().trim()
        val lastName = etApellido.text.toString().trim()
        val email = etCorreo.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://vetcare2.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        val registerRequest = RegisterRequest(firstName, lastName, email, password)
        apiService.registerUser(registerRequest).enqueue(object : retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@RegisterActivity, "Registro fallido", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Error de red", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun showTermsAndConditions() {
        val htmlContent = """
            <html>
            <head>
                <title>Términos y Condiciones - VetCare</title>
            </head>
            <body>
                <h1>Términos y Condiciones</h1>
                
                <p>Bienvenido a VetCare. Al utilizar nuestros servicios, aceptas los siguientes términos y condiciones. Por favor, léelos detenidamente.</p>
                
                <h2>1. General</h2>
                <p>Estos términos y condiciones rigen el uso de la aplicación VetCare y todos los servicios relacionados proporcionados por VetCare. Al acceder o usar la aplicación, aceptas cumplir con estos términos.</p>
                
                <h2>2. Servicios</h2>
                <p>VetCare ofrece una plataforma para encontrar y contactar servicios veterinarios. Nos esforzamos por proporcionar información precisa y actualizada, pero no garantizamos la exactitud o integridad de dicha información.</p>
                
                <h2>3. Registro</h2>
                <p>Para usar ciertas funciones de la aplicación, debes registrarte y crear una cuenta. Te comprometes a proporcionar información veraz y completa durante el proceso de registro y a mantenerla actualizada.</p>
                
                <h2>4. Privacidad</h2>
                <p>Nos tomamos tu privacidad muy en serio. Para más información sobre cómo recopilamos y usamos tus datos personales, por favor revisa nuestra <a href="https://cota-dreamers.github.io/VetCare-LandingPage/" target="_blank">Política de Privacidad</a>.</p>
                
                <h2>5. Conducta del Usuario</h2>
                <p>Te comprometes a usar VetCare de manera responsable y a no realizar actividades que puedan dañar a VetCare o a otros usuarios. Esto incluye, pero no se limita a, la distribución de contenido ilegal o dañino, el uso de la aplicación para actividades fraudulentas y la violación de derechos de propiedad intelectual.</p>
                
                <h2>6. Propiedad Intelectual</h2>
                <p>Todos los contenidos presentes en la aplicación, incluidos textos, gráficos, logos, y software, son propiedad de VetCare o de sus licenciantes y están protegidos por las leyes de propiedad intelectual.</p>
                
                <h2>7. Modificaciones</h2>
                <p>VetCare se reserva el derecho de modificar estos términos y condiciones en cualquier momento. Te notificaremos sobre cualquier cambio a través de la aplicación o por correo electrónico. El uso continuado de nuestros servicios después de la notificación implica la aceptación de los nuevos términos.</p>
                
                <h2>8. Contacto</h2>
                <p>Si tienes alguna pregunta sobre estos términos y condiciones, por favor contáctanos a través de <a href="mailto:vetcare24@gmail.com">vetcare24@gmail.com</a>.</p>
                
                <h2>9. Enlaces Externos</h2>
                <p>Para más información sobre nuestros servicios, visita nuestra <a href="https://cota-dreamers.github.io/VetCare-LandingPage/" target="_blank">Landing Page</a>.</p>
                
                <h2>10. Limitación de Responsabilidad</h2>
                <p>VetCare no se hace responsable de ningún daño directo, indirecto, incidental, especial o consecuente que resulte del uso o la imposibilidad de usar nuestra aplicación.</p>

                <h2>11. Ley Aplicable</h2>
                <p>Estos términos y condiciones se rigen por las leyes del país en el que operamos. Cualquier disputa relacionada con estos términos será resuelta por los tribunales competentes en dicha jurisdicción.</p>
            </body>
            </html>
        """

        webViewTerms.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
        webViewTerms.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        if (webViewTerms.visibility == View.VISIBLE) {
            webViewTerms.visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }
}