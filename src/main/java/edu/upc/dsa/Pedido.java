package edu.upc.dsa;

import edu.upc.dsa.models.Producte;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pedido {
    private String nombre;
    private List<PL> liniaPedido = new ArrayList<>();

    public Pedido(){}

    public Pedido(String nombre){
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<PL> getLiniaPedido() {
        return liniaPedido;
    }

    public void setLiniaPedido(List<PL> liniaPedido) {
        this.liniaPedido = liniaPedido;
    }



    public void CreatePL(String producte, int cantidad, Map<String,Producte> m) {
        getLiniaPedido().add(new PL(producte,cantidad));
        m.get(producte).incrCantidad(cantidad);

    }

}
