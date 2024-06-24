package com.example.vetcare_mobileapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.db.PetDao
import com.example.vetcare_mobileapp.models.Pet
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PetAdapter (
    var pets: List<Pet>,
    private val context: Context,
    private val petDao: PetDao
):RecyclerView.Adapter<PetAdapter.PetViewHolder>(){

    inner class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val petId: TextView = itemView.findViewById(R.id.tvId)
        val petName: TextView = itemView.findViewById(R.id.tvName)
        val petImage: ImageView = itemView.findViewById(R.id.ivPhoto)
        val petWeight: TextView = itemView.findViewById(R.id.tvWeight)
        val petBreed: TextView = itemView.findViewById(R.id.tvBreed)
        val petDate: TextView = itemView.findViewById(R.id.tvDate)
        val petColor: TextView = itemView.findViewById(R.id.tvColor)
        val petType: TextView = itemView.findViewById(R.id.tvType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_pet, parent, false)
        return PetViewHolder(view)
    }


    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val pet = pets[position]
        holder.petId.text = pet.id.toString()
        holder.petName.text = pet.name
        Picasso.get().load(pet.photoUrl).into(holder.petImage)
        holder.petWeight.text = pet.weight.toString()
        holder.petBreed.text = pet.breed
        holder.petDate.text = pet.date
        holder.petColor.text = pet.color
        holder.petType.text = pet.type


    }

    override fun getItemCount(): Int {
      return pets.size
    }

}