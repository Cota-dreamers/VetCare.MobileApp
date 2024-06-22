package com.example.vetcare_mobileapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.vetcare_mobileapp.databinding.ActivityAssistanceBinding
import com.example.vetcare_mobileapp.models.ChatGPTRequest
import com.example.vetcare_mobileapp.models.ChatGPTResponse
import com.example.vetcare_mobileapp.network.RetrofitClient2
import com.example.vetcare_mobileapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AssistanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAssistanceBinding
    private lateinit var apiService: ApiService
    private val apiKey: String by lazy { getString(R.string.openai_api_key) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssistanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Retrofit y ApiService
        val retrofit = RetrofitClient2.instance
        apiService = retrofit.create(ApiService::class.java)

        binding.btnSend.setOnClickListener {
            val query = binding.etQuery.text.toString().trim()
            if (query.isNotEmpty()) {
                sendQueryToChatGPT(query)
            }
        }
    }

    private fun sendQueryToChatGPT(query: String) {
        binding.progressBar.visibility = View.VISIBLE
        val messages = listOf(
            ChatGPTRequest.Message(role = "user", content = query)
        )
        val request = ChatGPTRequest(messages = messages)
        apiService.getChatGPTResponse("Bearer $apiKey", request).enqueue(object : Callback<ChatGPTResponse> {
            override fun onResponse(call: Call<ChatGPTResponse>, response: Response<ChatGPTResponse>) {
                binding.progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val answer = response.body()?.choices?.get(0)?.message?.content ?: "No response"
                    binding.tvResponse.text = answer
                } else {
                    binding.tvResponse.text = "Error: ${response.errorBody()?.string()}"
                }
            }

            override fun onFailure(call: Call<ChatGPTResponse>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                binding.tvResponse.text = "Failure: ${t.message}"
            }
        })
    }
}