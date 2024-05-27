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
import com.example.vetcare_mobileapp.db.CareAdviceDao
import com.example.vetcare_mobileapp.models.CareAdvice
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CareAdviceAdapter(
    var careadvices: List<CareAdvice>,
    private val context: Context,
    private val CareAdviceDao: CareAdviceDao
    ): RecyclerView.Adapter<CareAdviceAdapter.CareAdviceViewHolder>() {
        inner class CareAdviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val careAdviceName: TextView = itemView.findViewById(R.id.tvName)
            val careAdviceImage: ImageView = itemView.findViewById(R.id.ivPhoto)
            val careAdviceCategory: TextView = itemView.findViewById(R.id.tvCategory)
            val careAdviceDescription: TextView = itemView.findViewById(R.id.tvDescription)
            val favouriteButton: Button = itemView.findViewById(R.id.btAdd)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareAdviceViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_care_advice, parent, false)
            return CareAdviceViewHolder(view)
        }

    override fun onBindViewHolder(holder: CareAdviceViewHolder, position: Int) {
        val careAdvice = careadvices[position]
        holder.careAdviceName.text = careAdvice.strAdvice

        Picasso.get().load(careAdvice.strAdviceThumb).into(holder.careAdviceImage)
        holder.careAdviceName.text = careAdvice.strAdvice.toString()
        holder.careAdviceCategory.text = careAdvice.strAdviceCategory.toString()
        holder.careAdviceDescription.text = careAdvice.strAdviceDescription.toString()

        holder.favouriteButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                careAdvice.isFavourite = !careAdvice.isFavourite
                if (careAdvice.isFavourite) {
                    CareAdviceDao.insertCareAdvice(careAdvice)
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            "El consejo ya está en favoritos",
                            Toast.LENGTH_SHORT
                        ).show(
                        )
                    }
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return careadvices.size
    }
}