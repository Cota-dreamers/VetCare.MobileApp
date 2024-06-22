package com.example.vetcare_mobileapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.models.User

//import androidx.recyclerview.widget.RecyclerView.Adapter


class FriendAdapter(var friends: List<User>): RecyclerView.Adapter<FriendPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_user,parent, false)

        return FriendPrototype(view)
    }

    override fun onBindViewHolder(holder: FriendPrototype, position: Int) {
        holder.bind(friends[position])
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    fun updateFriends(newFriends: List<User>) {
        friends = newFriends
        notifyDataSetChanged()
    }

}

class FriendPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName = itemView.findViewById<TextView>(R.id.tvName)
    val tvEmail = itemView.findViewById<TextView>(R.id.tvEmail)
    val ivFriend = itemView.findViewById<ImageView>(R.id.ivFriend)

    @SuppressLint("SetTextI18n")
    fun bind(friend: User){
        tvName.text = "${friend.name.title} ${friend.name.first} ${friend.name.last}"
        tvEmail.text = friend.email

        Glide.with(itemView).load(friend.picture.large).into(ivFriend)
    }
}

