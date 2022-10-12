package com.example.marviechat.domain.usecase

import com.example.marviechat.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatUseCase @Inject constructor(private val chatRepository: ChatRepository) {

    fun invoke() = chatRepository.getChat()
}