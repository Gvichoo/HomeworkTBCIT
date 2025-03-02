package com.example.homeworktbc.data.mapper

import com.example.homeworktbc.data.room.entity.EventEntity
import com.example.homeworktbc.domain.modele.Event


fun Event.toEntity(): EventEntity {
    return EventEntity(
        id = this.id,
        name = this.name,
        image = this.image,
        organizer = this.organizer,
        date = this.date,
        info = this.info,
        price = this.price
    )
}
