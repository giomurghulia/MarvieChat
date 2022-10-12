package com.example.marviechat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marviechat.domain.model.ChatModel
import com.example.marviechat.domain.usecase.GetChatUseCase
import com.example.marviechat.toResult
import com.example.marviechat.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatUseCase: GetChatUseCase
) : ViewModel() {

    private val _state = MutableSharedFlow<Result<List<ListItem>>>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val state get() = _state.asSharedFlow()

    fun getChat() {
        viewModelScope.launch {
            getChatUseCase.invoke()
                .toResult()
                .collect {
                    when (it) {
                        is Result.Success -> {
                            _state.tryEmit(Result.Success(buildList(it.data)))
                        }
                        is Result.Loading -> {
                            _state.tryEmit(Result.Loading)
                        }
                        is Result.Error -> {
                            _state.tryEmit(Result.Error(it.throwable))
                        }
                    }
                }
        }
    }

    private fun buildList(chat: List<ChatModel>): List<ListItem> {
        val items = mutableListOf<ListItem>()

        items.addAll(chat.map {
            ListItem(
                it.id,
                it.email,
                it.first_name,
                it.last_name,
                it.avatar,
                MessageType.stringToType(it.message_type) as MessageType,
                it.last_message.toString(),
                it.unrea_message,
                it.is_typing,
                getDate(it.updated_date, "HH:mm aa")
            )
        })
        return items
    }

    private fun getDate(epochSec: Long, dateFormatStr: String?): String {
        val date = Date(epochSec * 1000)
        val format = SimpleDateFormat(
            dateFormatStr,
            Locale.getDefault()
        )

        return format.format(date)
    }

}