package edu.upc.dsa;
import edu.upc.dsa.models.Producte;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface ProductManager {
    public List<Producte> productesOrdPreu();
    public void AnotarComanda(Pedido c);
    public void servirComanda();
    public List<Pedido> comandesPerUsuari(String isUser);
    public List<Producte> productesOrdVentes();
    public Queue<Pedido> getComandas();
    public Map<String, Producte> getListProductes();
    public void clear();
}
