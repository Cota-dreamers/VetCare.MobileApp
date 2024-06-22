package com.example.vetcare_mobileapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.adapters.ForumPostAdapter
import com.example.vetcare_mobileapp.adapters.FriendAdapter
import com.example.vetcare_mobileapp.models.ForumPost
import com.example.vetcare_mobileapp.models.FriendResponse
import com.example.vetcare_mobileapp.network.ForumService
import com.example.vetcare_mobileapp.network.FriendService
import com.example.vetcare_mobileapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForumActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var forumService: ForumService
    private lateinit var friendAdapter: FriendAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forum)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(com.example.vetcare_mobileapp.R.id.btVet)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.rvPosts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        forumService = RetrofitClient.instance.create(ForumService::class.java)

        val btDestacado = findViewById<Button>(R.id.btDestacado)
        val btReciente = findViewById<Button>(R.id.btReciente)
        val btNuevo = findViewById<Button>(R.id.btNuevo)

        btDestacado.setOnClickListener {
            loadHighlightedPosts()
            loadFriends()
        }

        btReciente.setOnClickListener {
            loadRecentPosts()
        }

        btNuevo.setOnClickListener {
            val intent = Intent(this@ForumActivity, NewPostActivity::class.java)
            startActivity(intent)
        }

    }

    private fun loadHighlightedPosts() {
        val forumService = RetrofitClient.instance.create(ForumService::class.java)
        forumService.getHighlightedPosts().enqueue(object : Callback<List<ForumPost>> {
            override fun onResponse(call: Call<List<ForumPost>>, response: Response<List<ForumPost>>) {
                if (response.isSuccessful) {
                    val posts = response.body() ?: emptyList()
                    recyclerView.adapter = ForumPostAdapter(posts)
                }
            }

            override fun onFailure(call: Call<List<ForumPost>>, t: Throwable) {
                // Handle error
            }
        })
    }
    private fun loadRecentPosts() {
        forumService.getRecentPosts().enqueue(object : Callback<List<ForumPost>> {
            override fun onResponse(call: Call<List<ForumPost>>, response: Response<List<ForumPost>>) {
                if (response.isSuccessful) {
                    val posts = response.body() ?: emptyList()
                    recyclerView.adapter = ForumPostAdapter(posts)
                }
            }

            override fun onFailure(call: Call<List<ForumPost>>, t: Throwable) {
                // Handle error
            }
        })
    }

    private fun loadFriends() {
        // Inicializamos el adaptador con una lista vacía
        friendAdapter = FriendAdapter(emptyList())
        val rvFriend = findViewById<RecyclerView>(R.id.rvPosts)
        rvFriend.adapter = friendAdapter
        rvFriend.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val friendService = retrofit.create(FriendService::class.java)

        val request = friendService.getFriend()

        request.enqueue(object : Callback<FriendResponse>{
            override fun onResponse(call: Call<FriendResponse>, response: Response<FriendResponse>) {
                if (response.isSuccessful) {
                    val friendResponse = response.body()
                    friendResponse?.let {
                        // Actualizamos el adaptador con la lista de amigos obtenida de la respuesta
                        friendAdapter.updateFriends(it.results)
                    }
                }
            }

            override fun onFailure(call: Call<FriendResponse>, t: Throwable) {
                // Manejar error
            }
        })
    }

}
