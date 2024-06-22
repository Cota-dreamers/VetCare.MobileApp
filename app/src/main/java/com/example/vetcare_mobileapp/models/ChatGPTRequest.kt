package com.example.vetcare_mobileapp.models

data class ChatGPTRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>
) {
    data class Message(
        val role: String,
        val content: String
    )
}
