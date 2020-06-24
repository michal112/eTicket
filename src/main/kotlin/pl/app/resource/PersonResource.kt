package pl.app.resource

import pl.app.model.payload.PersonPayload
import pl.app.service.PersonService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/person")
class PersonResource {

    @Inject
    lateinit var personService :PersonService

    @GET
    @Path("/{publicId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByPublicId(@PathParam("publicId") publicId :String) :PersonPayload {
        return personService.getByPublicId(publicId)
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll() :List<PersonPayload> {
        return personService.getAll()
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun create(personPayload :PersonPayload) :PersonPayload {
        return personService.save(personPayload)
    }
}