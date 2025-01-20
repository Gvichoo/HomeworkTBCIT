package com.example.homeworktbc.fragmentMain

import androidx.lifecycle.ViewModel
import com.example.homeworktbc.MessageTypeAdapter
import com.example.homeworktbc.models.ChatItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainViewModel : ViewModel() {

    private val moshi = Moshi.Builder()
        .add(MessageTypeAdapter()) // If MessageType is a custom enum
        .add(KotlinJsonAdapterFactory())
        .build()

    // Updated to use Moshi's adapter for a list of ChatItem objects
    private val jsonAdapter: JsonAdapter<List<ChatItem>> = moshi.adapter(
        Types.newParameterizedType(List::class.java, ChatItem::class.java)
    )

    // JSON data
    val json = """
        [
            {
                "id": 1,
                "image": "https://www.alia.ge/wp-content/uploads/2022/09/grisha.jpg",
                "owner": "grisha oniani",
                "last_message": "თავის ტერიტორიას ბომბავდა",
                "last_active": "4:20 PM",
                "unread_messages": 3,
                "is_typing": false,
                "laste_message_type": "text"
            },
            {
                "id": 2,
                "image": null,
                "owner": "jemal kakauridze",
                "last_message": "შემოგევლე",
                "last_active": "3:00 AM",
                "unread_messages": 0,
                "is_typing": true,
                "laste_message_type": "voice"
            },
            {
                "id": 3,
                "image": "https://i.ytimg.com/vi/KYY0TBqTfQg/hqdefault.jpg",
                "owner": "guram jinoria",
                "last_message": "ცოცხალი ვარ მა რა ვარ შევ.. როდის იყო კვტარი ტელეფონზე ლაპარაკობდა",
                "last_active": "1:00 ",
                "unread_messages": 0,
                "is_typing": false,
                "laste_message_type": "file"
            },
            {
                "id": 4,
                "image": "",
                "owner": "kako wenguashvili",
                "last_message": "ადამიანი რო მოსაკლავად გაგიმეტებს თანაც ქალი ის დასანდობი არ არი",
                "last_active": "1:00 PM",
                "unread_messages": 0,
                "is_typing": false,
                "laste_message_type": "text"
            }
        ]
    """.trimIndent()

    // Fix parseJson method
    fun parseJson(): List<ChatItem>? {
        return try {
            // Correct parsing to List<ChatItem>
            jsonAdapter.fromJson(json)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
    }
}
