package com.example.homeworktbc.dataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Card(
    val id : String = UUID.randomUUID().toString(),
    val cardNumber : Long,
    val cardHolderName : String,
    val valid : String,
    val card : CardTypes
): Parcelable
