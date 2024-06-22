package com.example.vetcare_mobileapp.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.models.ForumPost
import com.example.vetcare_mobileapp.network.ForumService
import com.example.vetcare_mobileapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        val editTextTitle = findViewById<EditText>(R.id.edit_text_title)
        val editTextContent = findViewById<EditText>(R.id.edit_text_content)
        val buttonSubmit = findViewById<Button>(R.id.button_submit)

        buttonSubmit.setOnClickListener {
            val title = editTextTitle.text.toString()
            val content = editTextContent.text.toString()
            val author = "Author" // Replace with actual author

            val newPost = ForumPost(0, title, content, author, "Date")
            createNewPost(newPost)
        }
    }

    private fun createNewPost(post: ForumPost) {
        val forumService = RetrofitClient.instance.create(ForumService::class.java)
        forumService.createPost(post).enqueue(object : Callback<ForumPost> {
            override fun onResponse(call: Call<ForumPost>, response: Response<ForumPost>) {
                if (response.isSuccessful) {
                    finish() // Close the activity
                }
            }

            override fun onFailure(call: Call<ForumPost>, t: Throwable) {
                // Handle error
            }
        })
    }
}
