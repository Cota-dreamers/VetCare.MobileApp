package com.example.vetcare_mobileapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
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
        val petName: EditText = itemView.findViewById<EditText>(R.id.etNombreMascota)
        val breed: EditText = itemView.findViewById<EditText>(R.id.etRazaMascota)
        val weight: EditText = itemView.findViewById<EditText>(R.id.etPesoMascota)
        val type: EditText = itemView.findViewById<EditText>(R.id.etTipoMascota)
        val photoUrl: EditText = itemView.findViewById<EditText>(R.id.etImagenMascota)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_product, parent, false)
        return PetViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}