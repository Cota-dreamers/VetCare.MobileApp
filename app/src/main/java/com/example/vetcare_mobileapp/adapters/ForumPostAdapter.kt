package com.example.vetcare_mobileapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.models.ForumPost

class ForumPostAdapter(private val posts: List<ForumPost>) : RecyclerView.Adapter<ForumPostAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.post_title)
        val content: TextView = view.findViewById(R.id.post_content)
        val author: TextView = view.findViewById(R.id.post_author)
        val date: TextView = view.findViewById(R.id.post_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_forum_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        holder.title.text = post.title
        holder.content.text = post.content
        holder.author.text = post.author
        holder.date.text = post.date
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}
