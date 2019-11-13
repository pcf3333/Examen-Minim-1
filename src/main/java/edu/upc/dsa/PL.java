package edu.upc.dsa;

public class PL{
    private String producte;
    private int cantidad;

    public String getProducte() {
        return producte;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setProducte(String producte) {
        this.producte = producte;
    }

    public PL() {}
    public PL(String producte, int cantidad) {
        this.producte=producte;
        this.cantidad=cantidad;
    }
}
