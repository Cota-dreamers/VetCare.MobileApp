package com.example.vetcare_mobileapp.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.adapters.CareAdviceAdapter
import com.example.vetcare_mobileapp.communication.CareAdviceResponse
import com.example.vetcare_mobileapp.db.AppDatabase
import com.example.vetcare_mobileapp.db.CareAdviceDao
import com.example.vetcare_mobileapp.network.CareAdviceService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListCareAdviceActivity : AppCompatActivity() {
    private lateinit var careAdviceAdapter: CareAdviceAdapter
    private lateinit var careAdviceRecyclerView: RecyclerView
    private lateinit var careAdviceDao: CareAdviceDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_care_advice)

        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "careadvice.db"
        ).build()

        careAdviceDao = database.careAdviceDao()

        careAdviceRecyclerView = findViewById(R.id.rvCareAdviceList)
        careAdviceRecyclerView.layoutManager = LinearLayoutManager(this)

        careAdviceAdapter = CareAdviceAdapter(listOf(),this,careAdviceDao)
        careAdviceRecyclerView.adapter = careAdviceAdapter

        getCareAdvices(careAdviceDao)

    }

    private fun getCareAdvices(careAdviceDao: CareAdviceDao) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vetcare2.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val careAdviceService = retrofit.create(CareAdviceService::class.java)
        val call = careAdviceService.getAllCareAdvices()

        call.enqueue(object: Callback<List<CareAdviceResponse>> {
            override fun onResponse(call: Call<List<CareAdviceResponse>>, response: Response<List<CareAdviceResponse>>) {
                if (response.isSuccessful) {
                    val careAdviceResponses = response.body()
                    careAdviceResponses?.let {
                        // Update your RecyclerView
                        careAdviceAdapter.careadvices = it.map { careAdviceResponse ->
                            careAdviceResponse.toCareAdvice()
                        }
                        careAdviceAdapter.notifyDataSetChanged()

                        // Save data in local database
                        CoroutineScope(Dispatchers.IO).launch {
                            careAdviceAdapter.careadvices = it.map { careAdviceResponse ->
                                careAdviceResponse.toCareAdvice()
                            }.toList()
                        }
                    }
                } else {
                    Log.e("ListCareAdviceActivity", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<CareAdviceResponse>>, t: Throwable) {
                Log.e("ListCareAdviceActivity", "Error: ${t.message}")
            }
        })
    }}