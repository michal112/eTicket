package pl.app.model

import pl.app.model.entity.PersonEntity
import pl.app.model.entity.TicketEntity
import pl.app.model.entity.TicketType
import pl.app.model.payload.PersonPayload
import pl.app.model.payload.TicketPayload
import pl.app.model.payload.TicketTypePayload
import java.util.*

fun PersonEntity.toPayload() :PersonPayload {
    return PersonPayload(publicId, name, tickets?.map { it.toPayload() }?.toSet())
}

fun TicketEntity.toPayload() :TicketPayload {
    return TicketPayload(publicId, price, timestamp, type.toPayload())
}

fun TicketType.toPayload() :TicketTypePayload {
    return TicketTypePayload.valueOf(name)
}

fun PersonPayload.toEntity() :PersonEntity {
    val personEntity = PersonEntity(
            publicId = UUID.randomUUID().toString(),
            name = name,
            tickets = tickets?.map { it.toEntity() }?.toMutableSet()
    )
    personEntity.tickets?.forEach { it.person = personEntity }
    return personEntity
}

fun TicketPayload.toEntity() :TicketEntity {
    return TicketEntity(
            publicId = UUID.randomUUID().toString(),
            price = price,
            timestamp = timestamp,
            type = type.toEntity()
    )
}

fun TicketTypePayload.toEntity() :TicketType {
    return TicketType.valueOf(name)
}

