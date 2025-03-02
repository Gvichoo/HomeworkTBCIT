package com.example.homeworktbc.data.mapper

import com.example.homeworktbc.data.model.EventDto
import com.example.homeworktbc.data.room.entity.EventEntity
import com.example.homeworktbc.domain.modele.Event

fun EventDto.toDomain() : Event{

    return Event(
        id = this.id,
        name = this.name,
        image = this.image,
        organizer = this.organizer,
        date = this.date,
        info = this.info,
        price = this.price
    )

}


fun EventEntity.toDomain(): Event {
    return Event(
        id = this.id,
        name = this.name,
        image = this.image,
        organizer = this.organizer,
        date = this.date,
        info = this.info,
        price = this.price
    )
}