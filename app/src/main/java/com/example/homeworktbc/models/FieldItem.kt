package com.example.homeworktbc.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FieldItem(
    @SerialName("field_id")
    val fieldId: Int,
    val hint: String,
    @SerialName("field_type")
    val fieldType: String,
    val keyboard: String? = null,
    val required: Boolean ,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("icon")
    val iconUrl: String,
)
