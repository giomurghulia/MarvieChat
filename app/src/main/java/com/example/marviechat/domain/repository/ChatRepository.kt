package com.example.marviechat.domain.repository

import com.example.marviechat.domain.model.ChatModel
import kotlinx.coroutines.flow.Flow


interface ChatRepository {

    fun getChat(): Flow<List<ChatModel>>
}