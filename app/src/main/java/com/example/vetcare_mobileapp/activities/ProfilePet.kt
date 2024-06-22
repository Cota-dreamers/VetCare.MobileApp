package com.example.vetcare_mobileapp.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.communication.PetResponse
import com.example.vetcare_mobileapp.models.Pet
import com.example.vetcare_mobileapp.network.PetService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfilePet : AppCompatActivity() {

    private lateinit var petImageView: ImageView
    private lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile_pet)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btVet)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btAddPet = findViewById<Button>(R.id.btAddPet)
        btAddPet.setOnClickListener {
            val name = findViewById<EditText>(R.id.etNombreMascota).text.toString()
            val breed = findViewById<EditText>(R.id.etRazaMascota).text.toString()
            val weight = findViewById<EditText>(R.id.etPesoMascota).text.toString().toFloat()
            val type = findViewById<EditText>(R.id.etTipoMascota).text.toString()
            val photoUrl = findViewById<EditText>(R.id.etImagenMascota).text.toString()

            val newPet = Pet(
                name = name,
                breed = breed,
                weight = weight,
                type = type,
                photoUrl = photoUrl
            )
            addPet(newPet)

            petImageView.setOnClickListener {
                selectImageFromGallery()
            }

        }
    }

    private fun selectImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        resultLauncher.launch(intent)
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            imageUri = result.data!!.data!!
            petImageView.setImageURI(imageUri)
        }
    }

    private fun addPet(pet: Pet) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vetcare2.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productService = retrofit.create(PetService::class.java)
        val call = productService.addPet(pet)

        call.enqueue(object : Callback<PetResponse> {
            override fun onResponse(call: Call<PetResponse>, response: Response<PetResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ProfilePet, "Mascota añadida exitosamente", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@ProfilePet, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PetResponse>, t: Throwable) {
                Toast.makeText(this@ProfilePet, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
