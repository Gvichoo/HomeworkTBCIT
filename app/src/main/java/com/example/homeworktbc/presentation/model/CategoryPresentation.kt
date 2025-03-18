package com.example.homeworktbc.presentation.model


data class CategoryPresentation(
    val id: String,
    val name: String,
    val nameDe: String?,
    val createdAt: String,
    val bglNumber: String?,
    val bglVariant: String?,
    val orderId: Int?,
    val main: Boolean?,
    val children: List<CategoryPresentation> = emptyList()
)