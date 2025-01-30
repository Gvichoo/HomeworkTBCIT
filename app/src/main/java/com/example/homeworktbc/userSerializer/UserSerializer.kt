package com.example.homeworktbc.userSerializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.homeworktbc.MessageUser
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream


object UserSerializer : Serializer<MessageUser>{
    override val defaultValue: MessageUser = MessageUser.getDefaultInstance()


    override suspend fun readFrom(input: InputStream): MessageUser {
        try {
            return MessageUser.parseFrom(input)
        }catch (e : InvalidProtocolBufferException){
            throw CorruptionException("Cannot read proto " , e)
        }
    }

    override suspend fun writeTo(t: MessageUser, output: OutputStream) {
        t.writeTo(output)
    }

}