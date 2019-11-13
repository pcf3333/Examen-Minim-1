package edu.upc.dsa;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private List<Pedido> ListaPedidos = new ArrayList<>();

    public Usuario(String nombre ){
        this.nombre=nombre;
    }

    public void addPedido(Pedido p){
        this.ListaPedidos.add(p);
    }
    public List<Pedido> getListaPedidos() {
        return ListaPedidos;
    }


}
