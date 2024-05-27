package com.example.vetcare_mobileapp.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.adapters.CareAdviceAdapter
import com.example.vetcare_mobileapp.communication.ApiResponse
import com.example.vetcare_mobileapp.db.AppDatabase
import com.example.vetcare_mobileapp.db.CareAdviceDao
import com.example.vetcare_mobileapp.network.CareAdviceService
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
            .baseUrl("http://localhost:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val careAdviceService = retrofit.create(CareAdviceService::class.java)
        val call = careAdviceService.getAllCareAdvices()

        call.enqueue(object: Callback<ApiResponse>{
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if(response.isSuccessful){
                    val careAdvices = response.body()?.careadvices
                    careAdviceAdapter.careadvices = careAdvices ?: listOf()
                    careAdviceAdapter.notifyDataSetChanged()

                }else{
                    val errorMessage = "Error: ${response.errorBody()}"
                    Toast.makeText(this@ListCareAdviceActivity, errorMessage, Toast.LENGTH_SHORT).show()
                    Log.e("Error del API", errorMessage)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                val errorMessage = "Error: ${t.message}"
                Toast.makeText(this@ListCareAdviceActivity, errorMessage, Toast.LENGTH_SHORT).show()
                Log.e("Error del API", errorMessage)
            }
        })
    }
}