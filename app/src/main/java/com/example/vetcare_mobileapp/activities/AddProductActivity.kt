package com.example.vetcare_mobileapp.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.communication.ProductResponse
import com.example.vetcare_mobileapp.models.Product
import com.example.vetcare_mobileapp.network.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        val btnAddProduct = findViewById<Button>(R.id.btnAddProduct)
        btnAddProduct.setOnClickListener {
            val name = findViewById<EditText>(R.id.etName).text.toString()
            val description = findViewById<EditText>(R.id.etDescription).text.toString()
            val price = findViewById<EditText>(R.id.etPrice).text.toString().toFloat()
            val imageUrl = findViewById<EditText>(R.id.etImageUrl).text.toString()
            val stock = findViewById<EditText>(R.id.etStock).text.toString().toInt()

            val newProduct = Product(
                name = name,
                description = description,
                price = price,
                image = imageUrl,
                stock = stock
            )
            addProduct(newProduct)
        }
    }

    private fun addProduct(product: Product) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vetcare2.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productService = retrofit.create(ProductService::class.java)
        val call = productService.addProduct(product)

        call.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AddProductActivity, "Producto añadido exitosamente", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@AddProductActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Toast.makeText(this@AddProductActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}