package com.example.vetcare_mobileapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.adapters.PetAdapter
import com.example.vetcare_mobileapp.communication.PetResponse
import com.example.vetcare_mobileapp.db.AppDatabase3
import com.example.vetcare_mobileapp.db.PetDao
import com.example.vetcare_mobileapp.models.Pet
import com.example.vetcare_mobileapp.network.PetService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListPetActivity : AppCompatActivity() {

    private lateinit var petAdapter: PetAdapter
    private lateinit var petRecyclerView: RecyclerView
    private lateinit var petDao: PetDao
    private var allPets: List<Pet> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pet)

        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase3::class.java, "pet.db"
        ).build()

        petDao = database.petDao()

        petRecyclerView = findViewById(R.id.rvPetList)
        petRecyclerView.layoutManager = LinearLayoutManager(this)

        petAdapter = PetAdapter(listOf(), this, petDao)
        petRecyclerView.adapter = petAdapter

        getPets(petDao)

        val fabAddPet = findViewById<FloatingActionButton>(R.id.fab_add_pet)
        fabAddPet.setOnClickListener {
            val intent = Intent(this, ProfilePet::class.java)
            startActivity(intent)
        }
    }

    private fun getPets(petDao: PetDao) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vetcare2.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val petService = retrofit.create(PetService::class.java)
        val call = petService.getAllPets()

        call.enqueue(object : Callback<List<PetResponse>> {
            override fun onResponse(call: Call<List<PetResponse>>, response: Response<List<PetResponse>>){
                if (response.isSuccessful){
                    val petResponse = response.body()
                    petResponse?.let {
                        allPets = it.map { petResponse ->
                            petResponse.toPet()
                        }
                        petAdapter.pets = allPets
                        petAdapter.notifyDataSetChanged()

                        CoroutineScope(Dispatchers.IO).launch {
                            petAdapter.pets = allPets
                        }
                    }
                } else {
                    Log.e("ListPetActivity", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<PetResponse>>, t: Throwable) {
                Log.e("ListPetActivity", "Error: ${t.message}")
            }

        })
    }

}