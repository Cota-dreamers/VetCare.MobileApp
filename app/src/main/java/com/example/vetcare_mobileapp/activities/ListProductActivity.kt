package com.example.vetcare_mobileapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.adapters.ProductAdapter
import com.example.vetcare_mobileapp.communication.ProductResponse
import com.example.vetcare_mobileapp.db.AppDatabase
import com.example.vetcare_mobileapp.db.AppDatabase2
import com.example.vetcare_mobileapp.db.ProductDao
import com.example.vetcare_mobileapp.network.ProductService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListProductActivity : AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productDao: ProductDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_product)

        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase2::class.java, "product.db"
        ).build()

        productDao = database.productDao()

        productRecyclerView = findViewById(R.id.rvProductList)
        productRecyclerView.layoutManager = LinearLayoutManager(this)

        productAdapter = ProductAdapter(listOf(),this,productDao)
        productRecyclerView.adapter = productAdapter

        getProducts(productDao)

        val favouriteProductButton = findViewById<Button>(R.id.bn_FavouriteProduct)
        favouriteProductButton.setOnClickListener {
          val intent = Intent(this, CartProductActivity::class.java)
            startActivity(intent)
        }


    }

    private fun getProducts(productDao: ProductDao){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vetcare2.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productService = retrofit.create(ProductService::class.java)
        val call = productService.getAllProducts()

        call.enqueue(object: Callback<List<ProductResponse>> {
            override fun onResponse(call: Call<List<ProductResponse>>, response: Response<List<ProductResponse>>){
                if (response.isSuccessful){
                    val productResponses = response.body()
                    productResponses?.let {

                        productAdapter.products = it.map { productResponse ->
                            productResponse.toProduct()
                        }
                        productAdapter.notifyDataSetChanged()

                        CoroutineScope(Dispatchers.IO).launch {
                            productAdapter.products = it.map { productResponse ->
                                productResponse.toProduct()
                            }.toList()
                    }
                }
            } else {
                Log.e("ListProductActivity", "Error: ${response.code()}")
                }
        }



        override fun onFailure(call: Call<List<ProductResponse>>, t: Throwable) {
            Log.e("ListProductActivity", "Error: ${t.message}")
        }
    })
}}