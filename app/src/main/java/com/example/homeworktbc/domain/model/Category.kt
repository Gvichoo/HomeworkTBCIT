package com.example.homeworktbc.domain.model


data class Category (
    val id: String,
    val name: String,
    val nameDe: String?,
    val createdAt: String,
    val bglNumber: String?,
    val bglVariant: String?,
    val orderId: Int?,
    val main: Boolean?,
    val children: List<Category> = emptyList()
)
