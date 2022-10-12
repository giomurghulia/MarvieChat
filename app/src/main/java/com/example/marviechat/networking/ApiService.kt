package com.example.marviechat.networking

import com.example.marviechat.domain.model.ChatModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("80d25aee-d9a6-4e9c-b1d1-80d2a7c979bf")
    suspend fun getChat(): Response<List<ChatModel>>
}