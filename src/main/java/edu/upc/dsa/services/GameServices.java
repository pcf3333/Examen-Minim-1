package edu.upc.dsa.services;


import edu.upc.dsa.*;
import edu.upc.dsa.models.ObjectClass;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Api(value = "/", description = "Endpoint to Track Service")
@Path("/")
public class GameServices {

    private GameManager gm;

    public GameServices() {
        this.gm = GameManagerImpl.getInstance();
        User pere = new User("Pere","Coll");
        User juanjo = new User("Juanjo","Medina");
        ObjectClass espada=new ObjectClass("Espada",300);
        pere.addObject(espada);

        gm.addUser(pere);
        gm.addUser(juanjo);
    }

    @GET
    @ApiOperation(value = "Get all Users (sorted)", notes = "Users Sorted Alphabetically")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> users = new ArrayList<>(GameManagerImpl.getInstance().getListUsers().values());

        GenericEntity<List<User>> entity = new GenericEntity<>(users){};
        return Response.status(201).entity(entity).build()  ;
    }

    @POST
    @ApiOperation(value = "Add a new User", notes = "Adds a new User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User u) {
        if (u.getName()==null || u.getSurname()==null)  return Response.status(500).entity(u).build();
        else {
            gm.addUser(u);
            return Response.status(201).entity(u).build();
        }
    }

    @POST
    @ApiOperation(value = "Modify User", notes = "Modifies a User (id must be the same)")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/modifyUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyUser(User u) {
        if (u.getName() == null || u.getSurname() == null) return Response.status(500).entity(u).build();
        if (gm.modifyUser(u)) {
            return Response.status(201).entity(u).build();
        }
        return Response.status(404).build();

    }


    @GET
    @ApiOperation(value = "Get a User", notes = "Gets a user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("name") String name) {
        User u = this.gm.getListUsers().get(name);
        if (u == null) return Response.status(404).build();
        else  return Response.status(201).entity(u).build();
    }


    @GET
    @ApiOperation(value = "Get user's Objects", notes = "Gets a user Objects")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = ObjectClass.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{name}/objects")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserObjects(@PathParam("name") String name) {
        if (gm.getListUsers().containsKey(name)) {
            User u = gm.getListUsers().get(name);
            List<ObjectClass> o = u.getObjects();

            GenericEntity<List<ObjectClass>> entity = new GenericEntity<>(o) {};
            return Response.status(201).entity(entity).build();
        }
        return Response.status(404).build();

    }


    @POST
    @ApiOperation(value = "Add Object to User", notes = "Adds an object to the specified user (id)")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=ObjectClass.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/{name}/add/object")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addObject(@PathParam("name") String name, ObjectClass o) {
        if (gm.getListUsers().containsKey(name)) {
            if (o == null) return Response.status(500).entity(o).build();
            User u = gm.getListUsers().get(name);
            u.getObjects().add(o);

            GenericEntity<List<ObjectClass>> entity = new GenericEntity<>(u.getObjects()) {};
            return Response.status(201).entity(entity).build();
        }
        return Response.status(404).build();
    }

}