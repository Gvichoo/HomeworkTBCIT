package com.example.homeworktbc.presentation.mapper

import com.example.homeworktbc.domain.model.Category
import com.example.homeworktbc.presentation.model.CategoryPresentation

fun Category.toPresentation(): CategoryPresentation{
    return CategoryPresentation(
        id = id,
        name = name,
        nameDe = nameDe,
        createdAt = createdAt,
        bglNumber = bglNumber,
        bglVariant =bglVariant ,
        orderId = orderId,
        main = main,
        children = children.map { it.toPresentation() }
    )
}