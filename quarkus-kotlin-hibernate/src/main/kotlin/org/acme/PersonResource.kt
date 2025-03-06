package org.acme

import jakarta.transaction.Transactional
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PersonResource {

    @GET
    fun getAllPersons(): List<Person> = Person.listAll()

    @POST
    @Transactional
    fun addPerson(person: Person): Response {
        person.persist()
        return Response.status(Response.Status.CREATED).entity(person).build()
    }

    @GET
    @Path("/{id}")
    fun getPersonById(@PathParam("id") id: Long): Person? = Person.findById(id)

    @DELETE
    @Path("/{id}")
    @Transactional
    fun deletePersonById(@PathParam("id") id: Long): Response {
        val person = Person.findById(id)
        return if (person != null) {
            Person.deleteById(id)
            Response.status(Response.Status.NO_CONTENT).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }
}
