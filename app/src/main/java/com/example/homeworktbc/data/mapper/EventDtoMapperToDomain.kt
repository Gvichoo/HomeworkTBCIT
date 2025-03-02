package com.example.homeworktbc.data.mapper

import com.example.homeworktbc.data.model.EventDto
import com.example.homeworktbc.domain.modele.Event

fun EventDto.toDomain() : Event{

    return Event(
        id = id,
        name = name,
        image = image,
        organizer = organizer,
        date =date,
        info = info,
        price = price
    )

}