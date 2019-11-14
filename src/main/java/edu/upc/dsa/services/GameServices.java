package edu.upc.dsa.services;


import edu.upc.dsa.*;
import edu.upc.dsa.models.ObjectClass;
import edu.upc.dsa.models.User;
import edu.upc.dsa.util.ListObject;
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
        GameManagerImpl Impl= GameManagerImpl.getInstance();
        User pere = new User("Pere","Coll");
        User juanjo = new User("Juanjo","Medina");
        ObjectClass espada=new ObjectClass("Espada",300);
        pere.addObject(espada);
        Map<String, User> listUsers=Map.of("Juanjo",juanjo,"Pere",pere );
        Impl.setListUsers(listUsers);
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

    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User u) {
        if (u.getName()==null || u.getSurname()==null)  return Response.status(500).entity(u).build();
        this.gm.addUser(u);
        return Response.status(201).entity(u).build();
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


    //Em falla
    @GET
    @ApiOperation(value = "Get user's Objects", notes = "Gets a user Objects")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = ListObject.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{name}/objects")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserObjects(@PathParam("name") String name) {
        User u = this.gm.getListUsers().get(name);
        List <ObjectClass> o = u.getObjects();
        if (u == null || o.size()==0) return Response.status(404).build();
        else  return Response.status(201).entity(o).build();
    }


//    Si la poso peta per la response
//    @POST
//    @ApiOperation(value = "Modify User", notes = "Modifies a User")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Successful", response=User.class),
//            @ApiResponse(code = 500, message = "Validation Error")
//
//    })
//
//    @Path("/modifyUser")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response modifyUser(String oldName, String newName, String newSurname, List<ObjectClass> o) {
//        if (gm.getListUsers().containsKey(oldName)) {
//            if (oldName == null || newName == null || newSurname == null) return Response.status(500).entity(gm.getListUsers().get(oldName)).build();
//            else {
//                gm.modifyUser(oldName, newName, newSurname, o);
//                return Response.status(201).entity(gm.getListUsers().get(newName)).build();
//            }
//        }
//        return Response.status(500).entity("No user found").build();
//
//    }


}