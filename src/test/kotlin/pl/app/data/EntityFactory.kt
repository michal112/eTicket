package pl.app.data

import pl.app.model.entity.PersonEntity
import pl.app.model.entity.TicketEntity
import pl.app.model.entity.TicketType
import java.text.SimpleDateFormat
import java.util.*

class EntityFactory {

    companion object {

        fun createPerson(name: String, tickets: MutableSet<TicketEntity>) :PersonEntity {
            val personEntity = PersonEntity(
                    publicId = UUID.randomUUID().toString(),
                    name = name,
                    tickets = tickets
            )
            personEntity.tickets?.forEach { it.person = personEntity }

            return personEntity
        }

        fun createTicket(price: Int, type: TicketType) :TicketEntity {
            return TicketEntity(
                    publicId = UUID.randomUUID().toString(),
                    price = price,
                    timestamp = SimpleDateFormat("yyyy-MM-dd").parse("2020-06-17"),
                    type = type
            )
        }
    }
}