package com.example.vetcare_mobileapp.adapters

import android.app.Activity
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

class CartProductAdapter (
    var products: MutableList<Product>,
    private val context: Context,
    private val productDao: ProductDao
): RecyclerView.Adapter<CartProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.tvName)
        val productImage: ImageView = itemView.findViewById(R.id.ivPhoto)
        val bt_Delete: Button = itemView.findViewById(R.id.btDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductAdapter.ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_cart_product, parent, false)
        return CartProductAdapter.ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        if (products.isEmpty()) {
            Toast.makeText(context, "No hay productos en el carrito", Toast.LENGTH_SHORT).show()
            holder.productName.visibility = View.GONE
            holder.bt_Delete.visibility = View.GONE
        } else {
            val product = products[position]
            holder.productName.text = product.name

            Picasso.get().load(product.image).into(holder.productImage)

            holder.bt_Delete.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    productDao.deleteProduct(product)
                    (context as Activity).runOnUiThread {
                        products.removeAt(position)
                        notifyItemRemoved(position)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

}