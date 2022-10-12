package com.example.marviechat.data


import com.example.marviechat.domain.model.ChatModel
import com.example.marviechat.domain.repository.ChatRepository
import com.example.marviechat.networking.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ChatRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ChatRepository {

    override fun getChat(): Flow<List<ChatModel>> = flow {
        val response = apiService.getChat()

        if (response.isSuccessful) {
            emit(response.body()!!)
        }

    }
}
