package com.example.homeworktbc.data.mapper

import com.example.homeworktbc.data.remote.dto.CategoryDto
import com.example.homeworktbc.domain.model.Category

fun CategoryDto.toDomain(depth: Int = 0): Category {
    return Category(
        id = id,
        name = name,
        nameDe = nameDe,
        createdAt = createdAt,
        bglNumber = bglNumber,
        bglVariant =bglVariant ,
        orderId = orderId,
        main = main,
        children = children.map { it.toDomain(depth + 1 ) },
        depth = depth
    )
}