package com.example.vetcare_mobileapp.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.db.CareAdviceDao
import com.example.vetcare_mobileapp.models.CareAdvice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CareAdviceFavouriteAdapter (
    var careadvices: MutableList<CareAdvice>,
    private val context: Context,
    private val CareAdviceDao: CareAdviceDao
): RecyclerView.Adapter<CareAdviceFavouriteAdapter.FavouriteViewHolder>() {

    class FavouriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val careAdviceName: TextView = itemView.findViewById(R.id.tvName)
        val bt_Delete: Button = itemView.findViewById(R.id.btDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareAdviceFavouriteAdapter.FavouriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_favourite_care_advice, parent, false)
        return CareAdviceFavouriteAdapter.FavouriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        if (careadvices.isEmpty()) {
            Toast.makeText(context, "No hay favoritos", Toast.LENGTH_SHORT).show()
            holder.careAdviceName.visibility = View.GONE
            holder.bt_Delete.visibility = View.GONE
        } else {
            val careAdvice = careadvices[position]
            holder.careAdviceName.text = careAdvice.strAdvice

            holder.bt_Delete.setOnClickListener {
               CoroutineScope(Dispatchers.IO).launch {
                    CareAdviceDao.deleteCareAdvice(careAdvice)
                    (context as Activity).runOnUiThread {
                        careadvices.removeAt(position)
                        notifyItemRemoved(position)
                    }
                }
            }
        }
    }
    override fun getItemCount(): Int {
        return careadvices.size
    }
}