package pl.app.service

import pl.app.model.entity.PersonEntity
import pl.app.model.payload.PersonPayload
import pl.app.model.toEntity
import pl.app.model.toPayload
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@ApplicationScoped
class PersonService(

    @Inject
    val entityManager :EntityManager
) {

    @Transactional
    fun getByPublicId(publicId :String) :PersonPayload {
        val personEntity = entityManager.createQuery("select p from PersonEntity p where p.publicId = :publicId")
                .setParameter("publicId", publicId)
                .singleResult as PersonEntity
        return personEntity.toPayload()
    }

    @Transactional
    fun getAll() :List<PersonPayload> {
        val list = entityManager.createQuery("select p from PersonEntity p")
                .resultList
        return list.map { (it as PersonEntity).toPayload() }
    }

    @Transactional
    fun save(personPayload :PersonPayload) :PersonPayload {
        return entityManager.merge(personPayload.toEntity()).toPayload()
    }
}