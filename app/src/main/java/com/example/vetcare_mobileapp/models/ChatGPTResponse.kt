package com.example.vetcare_mobileapp.models

data class ChatGPTResponse(
    val choices: List<Choice>
) {
    data class Choice(
        val message: Message
    ) {
        data class Message(
            val role: String,
            val content: String
        )
    }
}
