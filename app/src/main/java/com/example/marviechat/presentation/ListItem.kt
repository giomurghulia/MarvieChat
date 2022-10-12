package com.example.marviechat.presentation

import com.example.marviechat.R

data class ListItem(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    val messageType: MessageType,
    val lastMessage: String,
    val unreadMessage: Int,
    val isTyping: Boolean,
    val updatedDate: String
)
enum class MessageType {
    TEXT {
        override val icon: Int
            get() = TODO("Not yet implemented")
    },
    ATTACHMENT {
        override val icon: Int
            get() = R.drawable.ic_attachment

        override fun toString() = "Sent an attachment"
    },
    VOICE {
        override val icon: Int
            get() = R.drawable.ic_voice

        override fun toString() = "Sent a voice message"
    };

    abstract val icon: Int

    companion object {
        fun stringToType(type: String) = when (type) {
            "text" -> TEXT
            "attachment" -> ATTACHMENT
            "voice" -> VOICE
            else -> {}
        }
    }
}
