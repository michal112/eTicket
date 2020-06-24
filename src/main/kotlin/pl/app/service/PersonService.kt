package pl.app.service

import pl.app.model.PersonRepository
import pl.app.model.payload.PersonPayload
import pl.app.model.toEntity
import pl.app.model.toPayload
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional

@ApplicationScoped
class PersonService {

    @Inject
    lateinit var personRepository: PersonRepository

    @Transactional
    fun getByPublicId(publicId :String) :PersonPayload {
        val personEntity = personRepository.getByPublicId(publicId)
        return personEntity.toPayload()
    }

    @Transactional
    fun getAll() :List<PersonPayload> {
        val list = personRepository.getAll()
        return list.map { it.toPayload() }
    }

    @Transactional
    fun save(personPayload :PersonPayload) :PersonPayload {
        return personRepository.save(personPayload.toEntity()).toPayload()
    }
}