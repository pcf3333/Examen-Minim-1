package edu.upc.dsa.services;


import edu.upc.dsa.*;
import edu.upc.dsa.models.Producte;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Array;
import java.util.*;

@Api(value = "/", description = "Endpoint to Track Service")
@Path("/")
public class ProductServices {

    private ProductManager pm;

    public ProductServices() {
        this.pm = ProductManagerImpl.getInstance();
        ProductManagerImpl Impl= ProductManagerImpl.getInstance();
        Producte CocaCola=new Producte("CocaCola",2,0);
        Producte Bocata=new Producte("Bocata",4,0);
        Producte Aquarius=new Producte("Aquarius",3,0);
        Producte Croissant=new Producte("Croissant",1,0);
        Map<String,Producte> listProductes= Map.of("CocaCola",CocaCola ,"Bocata",Bocata,"Aquarius",Aquarius,"Croissant",Croissant);
        Impl.setListProductes(listProductes);
    }

    @GET
    @ApiOperation(value = "Get all Products", notes = "Te da todos los productos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producte.class, responseContainer="List"),
    })
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {

        List<Producte> products = new ArrayList<>(ProductManagerImpl.getInstance().getListProductes().values());

        GenericEntity<List<Producte>> entity = new GenericEntity<>(products){};
        return Response.status(201).entity(entity).build()  ;
    }

    @POST
    @ApiOperation(value = "create a new pedido", notes = "holi")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Pedido.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/comandas")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newComanda(Pedido p) {
        if (p.getNombre()==null || p.getLiniaPedido()==null)  return Response.status(500).entity(p).build();
        this.pm.AnotarComanda(p);
        for (int i=0;i<p.getLiniaPedido().size();i++) {
            if (ProductManagerImpl.getInstance().getListProductes().get(p.getLiniaPedido().get(i).getProducte()) == null)
                return Response.status(500).entity(p).build();
            else
                pm.getListProductes().get(p.getLiniaPedido().get(i).getProducte()).incrCantidad(p.getLiniaPedido().get(i).getCantidad());

        }
        return Response.status(201).entity(p).build();
    }


    @GET
    @ApiOperation(value = "Ver Comanadas", notes = "Vas a ver las comandasssssss")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Pedido.class, responseContainer="List"),
    })
    @Path("/comandas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response manager() {
        GenericEntity<Queue<Pedido>> entity = new GenericEntity<>(pm.getComandas()){};
        return Response.status(201).entity(entity).build()  ;

    }

    @DELETE
    @ApiOperation(value = "Servir Comanda", notes = "Sirve la ultima comanda")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "No comandas left")
    })
    @Path("/servir")
    public Response SC() {
        this.pm.servirComanda();
        return Response.status(201).build();
    }
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