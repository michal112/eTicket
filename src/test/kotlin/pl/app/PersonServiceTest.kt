package pl.app

import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.h2.H2DatabaseTestResource
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pl.app.data.EntityFactory
import pl.app.model.entity.PersonEntity
import pl.app.model.entity.TicketEntity
import pl.app.model.entity.TicketType
import pl.app.model.toPayload
import pl.app.service.PersonService
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@QuarkusTest
@Transactional
@QuarkusTestResource(H2DatabaseTestResource::class)
class PersonServiceTest {

    @Inject
    lateinit var personService :PersonService

    @Inject
    lateinit var entityManager: EntityManager

    val tickets :MutableSet<TicketEntity> = mutableSetOf()

    val people :MutableList<PersonEntity> = mutableListOf()

    init {
        val names = arrayOf("Mateusz", "Jakub", "Magdalena")

        repeat(6) {
            tickets.add(EntityFactory.createTicket(it, TicketType.values()[it % 3]))
        }
        repeat(3) {
            people.add(EntityFactory.createPerson(names[it], tickets.filter { ticketEntity -> TicketType.values()[it] == ticketEntity.type }.toMutableSet()))
        }
    }

    @BeforeEach
    fun before() {
        for (person in people) {
            entityManager.persist(person)
        }
    }

    @AfterEach
    fun after() {
        for (person in people) {
            entityManager.remove(entityManager.find(PersonEntity::class.java, person.id))
        }
    }

    @Test
    fun getAllPeopleWithTickets() {
        //given
        //when
        val all = personService.getAll()
        //then
        Assertions.assertIterableEquals(people.map { personEntity -> personEntity.toPayload()}.toList(), all)
    }

    @Test
    fun getPersonByPublicIdWithTickets() {
        //given
        val person = people.find { personEntity -> "Mateusz" == personEntity.name }
        //when
        val response = person?.publicId?.let { personService.getByPublicId(it) }
        //then
        Assertions.assertEquals(person?.toPayload(), response)
    }
}