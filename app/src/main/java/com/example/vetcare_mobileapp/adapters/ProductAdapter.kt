package com.example.vetcare_mobileapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.db.ProductDao
import com.example.vetcare_mobileapp.models.Product
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductAdapter (
    var products: List<Product>,
    private val context: Context,
    private val productDao: ProductDao
):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.tvName)
        val productImage: ImageView = itemView.findViewById(R.id.ivPhoto)
        val productPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val productDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val productStock: TextView = itemView.findViewById(R.id.tvStock)
        val favouriteButton: Button = itemView.findViewById(R.id.btAdd)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.productName.text = product.name

        Picasso.get().load(product.image).into(holder.productImage)
        holder.productName.text = product.name.toString()
        holder.productPrice.text = product.price.toString()
        holder.productDescription.text = product.description.toString()
        holder.productStock.text = product.stock.toString()

        holder.favouriteButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                product.isFavourite = !product.isFavourite
                if (product.isFavourite) {
                    productDao.insertProduct(product)
                }else {
                    withContext(Dispatchers.Main){
                        Toast.makeText(
                            context,
                            "El producto ya está en el carrito",
                            Toast.LENGTH_SHORT
                        ).show(
                        )
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

}