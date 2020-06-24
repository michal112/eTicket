package pl.app.model

import pl.app.model.entity.PersonEntity
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager

@ApplicationScoped
class PersonRepository {

    @Inject
    lateinit var entityManager: EntityManager

    fun save(personEntity: PersonEntity) :PersonEntity {
        return entityManager.merge(personEntity)
    }

    fun getAll() :List<PersonEntity> {
        return entityManager.createQuery("select p from PersonEntity p")
                .resultList.map { it as PersonEntity }
    }

    fun getByPublicId(publicId :String): PersonEntity {
        return entityManager.createQuery("select p from PersonEntity p where p.publicId = :publicId")
                .setParameter("publicId", publicId)
                .singleResult as PersonEntity
    }
}