package com.example.marviechat.domain.model

data class ChatModel(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String,
    val message_type: String,
    val last_message: String?,
    val unrea_message: Int,
    val is_typing: Boolean,
    val updated_date: Long
)

