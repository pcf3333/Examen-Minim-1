package edu.upc.dsa.services;


import edu.upc.dsa.*;
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
public class ProductServices {

    private GameManager gm;

    public ProductServices() {
        this.gm = GameManagerImpl.getInstance();
        GameManagerImpl Impl= GameManagerImpl.getInstance();
        User pere = new User("Pere","Coll");
        User juanjo = new User("Juanjo","Medina");
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

//    @POST
//    @ApiOperation(value = "create a new pedido", notes = "holi")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Successful", response=Pedido.class),
//            @ApiResponse(code = 500, message = "Validation Error")
//
//    })
//
//    @Path("/comandas")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response newComanda(Pedido p) {
//        if (p.getNombre()==null || p.getLiniaPedido()==null)  return Response.status(500).entity(p).build();
//        this.pm.AnotarComanda(p);
//        for (int i=0;i<p.getLiniaPedido().size();i++) {
//            if (GameManagerImpl.getInstance().getListProductes().get(p.getLiniaPedido().get(i).getProducte()) == null)
//                return Response.status(500).entity(p).build();
//            else
//                pm.getListProductes().get(p.getLiniaPedido().get(i).getProducte()).incrCantidad(p.getLiniaPedido().get(i).getCantidad());
//
//        }
//        return Response.status(201).entity(p).build();
//    }
//
//
//    @GET
//    @ApiOperation(value = "Ver Comanadas", notes = "Vas a ver las comandasssssss")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Successful", response = Pedido.class, responseContainer="List"),
//    })
//    @Path("/comandas")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response manager() {
//        GenericEntity<Queue<Pedido>> entity = new GenericEntity<>(pm.getComandas()){};
//        return Response.status(201).entity(entity).build()  ;
//
//    }
//
//    @DELETE
//    @ApiOperation(value = "Servir Comanda", notes = "Sirve la ultima comanda")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Successful"),
//            @ApiResponse(code = 404, message = "No comandas left")
//    })
//    @Path("/servir")
//    public Response SC() {
//        this.pm.servirComanda();
//        return Response.status(201).build();
//    }
/*
    @GET
    @ApiOperation(value = "get a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Track.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack(@PathParam("id") String id) {
        Track t = this.pm.getTrack(id);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

   @PUT
    @ApiOperation(value = "update a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateTrack(Track track) {

        Track t = this.pm.updateTrack(track);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }

*/



}