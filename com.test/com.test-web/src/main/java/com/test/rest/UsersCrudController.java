package com.test.rest;
import com.test.dto.PersonDto;
import com.test.entity.Person;
import com.test.service.PersonService;
import com.test.validator.PersonValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;


@Path("/persons")
@Stateless(name="usersCrudController")
public class UsersCrudController {
    @Inject
    private PersonValidator personValidator;
    @Inject
    private PersonService personService;
   @POST
   @Path("/save")
   @Consumes("application/json")
   public Response savePerson(PersonDto personDto) {
       if (!personValidator.validate(personDto)) {
           return Response.status(Response.Status.BAD_REQUEST).entity("Fullfill all fields").build();
       }
       personService.savePerson(personDto);
       return Response.ok().build();
    }
    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updatePerson(PersonDto personDto,@PathParam(value = "id") Long id) {
        if (!personValidator.validate(personDto)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Fullfill all fields").build();
        }
        boolean result = personService.updatePerson(id,personDto);
        if (!result) {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found").build();
        }
        return Response.ok().build();
    }
    @GET
    @Path("/get/{id}")
    @Produces("application/json")
    public Response getPerson(@PathParam(value = "id") Long id) {
        Person person = personService.getPerson(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found").build();
        }
        return Response.ok(person).build();
    }
    @GET
    @Path("/all")
    @Produces("application/json")
    public Collection<Person> getAll() {
        return personService.getAll();
    }

}
