package com.example.homeworktbc

import com.example.homeworktbc.models.MessageType
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class MessageTypeAdapter {
    @FromJson
    fun fromJson(value: String): MessageType {
        return when (value) {
            "text" -> MessageType.TEXT
            "file" -> MessageType.FILE
            "voice" -> MessageType.VOICE
            else -> throw IllegalArgumentException("Unknown message type: $value")
        }
    }

    @ToJson
    fun toJson(messageType: MessageType): String {
        return when (messageType) {
            MessageType.TEXT -> "text"
            MessageType.FILE -> "file"
            MessageType.VOICE -> "voice"
        }
    }
}

