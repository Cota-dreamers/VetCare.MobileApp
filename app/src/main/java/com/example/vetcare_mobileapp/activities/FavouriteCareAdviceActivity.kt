package com.example.vetcare_mobileapp.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.adapters.CareAdviceFavouriteAdapter
import com.example.vetcare_mobileapp.db.AppDatabase
import com.example.vetcare_mobileapp.db.CareAdviceDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteCareAdviceActivity : AppCompatActivity() {
    private lateinit var favouriteAdapter: CareAdviceFavouriteAdapter
    private lateinit var careAdviceRecyclerView: RecyclerView
    private lateinit var careAdviceDao: CareAdviceDao

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_care_advice)

        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "careadvice.db"
        ).build()

        careAdviceDao = database.careAdviceDao()

        careAdviceRecyclerView = findViewById<RecyclerView>(R.id.rvFavouriteCareAdvice)
        careAdviceRecyclerView.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            val favouriteCareAdvices = careAdviceDao.getAllFavouriteCareAdvices()
            withContext(Dispatchers.Main) {
                favouriteAdapter = CareAdviceFavouriteAdapter(favouriteCareAdvices.toMutableList(), this@FavouriteCareAdviceActivity, careAdviceDao)
                careAdviceRecyclerView.adapter = favouriteAdapter
            }
        }
    }
}