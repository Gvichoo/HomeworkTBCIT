package com.example.homeworktbc.presentation.mapper

import com.example.homeworktbc.data.local.room.entity.AttendedEventEntity
import com.example.homeworktbc.domain.modele.AttendedEvent

fun AttendedEvent.toEntity() : AttendedEventEntity {
    return AttendedEventEntity(
        id = this.id,
        name = this.name,
        image = this.image,
        organizer = this.organizer,
        date = this.date,
        info = this.info,
        price = this.price
    )
}



fun AttendedEventEntity.toDomain() : AttendedEvent{
    return AttendedEvent(
        id = this.id,
        name = this.name,
        image = this.image,
        organizer = this.organizer,
        date = this.date,
        info = this.info,
        price = this.price
    )
}