package com.example.vetcare_mobileapp.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_pet)

        val btnAddPet = findViewById<Button>(R.id.btnAddPet)
        btnAddPet.setOnClickListener {
            val name = findViewById<EditText>(R.id.etName).text.toString()
            val breed = findViewById<EditText>(R.id.etBreed).text.toString()
            val weight = findViewById<EditText>(R.id.etWeight).text.toString().toFloatOrNull()
            val photoUrl = findViewById<EditText>(R.id.etImageUrl).text.toString()
            val type = findViewById<EditText>(R.id.etType).text.toString()
            val color = findViewById<EditText>(R.id.etColor).text.toString()
            val date = findViewById<EditText>(R.id.etDate).text.toString()

            if (weight == null) {
                Toast.makeText(this@ProfilePet, "Peso no válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newPet = Pet(
                name = name,
                breed = breed,
                weight = weight,
                photoUrl = photoUrl,
                type = type,
                color = color,
                date = date
            )
            addPet(newPet)
        }
    }

    private fun addPet(pet: Pet) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vetcare2.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val petService = retrofit.create(PetService::class.java)
        val call = petService.addPet(pet)

        call.enqueue(object : Callback<PetResponse> {
            override fun onResponse(call: Call<PetResponse>, response: Response<PetResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ProfilePet, "Mascota añadida exitosamente", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    val errorMessage = response.errorBody()?.string()
                    Toast.makeText(this@ProfilePet, "Error: ${response.code()} - $errorMessage", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PetResponse>, t: Throwable) {
                Toast.makeText(this@ProfilePet, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
