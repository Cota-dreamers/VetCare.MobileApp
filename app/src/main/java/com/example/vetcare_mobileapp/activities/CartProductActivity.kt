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
import com.example.vetcare_mobileapp.adapters.CartProductAdapter
import com.example.vetcare_mobileapp.db.AppDatabase
import com.example.vetcare_mobileapp.db.AppDatabase2
import com.example.vetcare_mobileapp.db.ProductDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartProductActivity : AppCompatActivity() {
    private lateinit var favouriteAdapter: CartProductAdapter
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productDao: ProductDao
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_product)

        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase2::class.java, "product.db"
        ).build()

        productDao = database.productDao()

        productRecyclerView = findViewById<RecyclerView>(R.id.rvCartProductList)
        productRecyclerView.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            val cartProducts = productDao.getAllFavouriteProducts()
            withContext(Dispatchers.Main) {
                favouriteAdapter = CartProductAdapter(cartProducts.toMutableList(), this@CartProductActivity, productDao)
                productRecyclerView.adapter = favouriteAdapter
            }
        }
    }
}