package edu.upc.dsa;

import edu.upc.dsa.models.Producte;

import java.util.*;

public class  ProductManagerImpl implements ProductManager {
    private static ProductManagerImpl Instance;
    private Queue<Pedido> Comandas;
    private Map<String, Producte> listProductes;
    private Map<String, Usuario> Usuarios;
    private ProductManagerImpl(){
        this.Comandas=new LinkedList<>();
        this.listProductes=new HashMap();
        this.Usuarios=new HashMap();
    }
    public static ProductManagerImpl getInstance(){
        if (Instance==null)
            Instance=new ProductManagerImpl();
        return Instance;

    }
    public List<Producte> productesOrdPreu() {
        List<Producte> lp=new ArrayList<>(listProductes.values());
        Collections.sort(lp, CMP_PRIZE);
        return lp;
    }

    public List<Producte> productesOrdVentes() {
        List<Producte> lp=new ArrayList<>(listProductes.values());
        Collections.sort(lp, new CompararVentas());
        return lp;
    }

    @Override
    public void clear() {
        this.Comandas.clear();
        this.listProductes.clear();
        this.Usuarios.clear();
    }

    public void AnotarComanda(Pedido p) {
        if (!Usuarios.containsKey(p.getNombre())) {
            Usuario user = new Usuario(p.getNombre());
            Usuarios.put(p.getNombre(), user);
        }
        this.Comandas.add(p);
    }

    public void servirComanda() {
        if (this.Comandas.size()!=0) {
            Pedido p = this.Comandas.remove();
            this.Usuarios.get(p.getNombre()).addPedido(p);
        }
    }

    public Queue<Pedido> getComandas() {
        return Comandas;
    }

    public List<Pedido> comandesPerUsuari(String isUser) {
        return Usuarios.get(isUser).getListaPedidos();
    }

    public static class CompararPrecio implements Comparator<Producte> {
        public int compare(Producte pr1, Producte pr2) {
            return Double.compare(pr1.getPrecio(), pr2.getPrecio());
        }
    }

    public static class CompararVentas implements Comparator<Producte> {
        public int compare(Producte pr1, Producte pr2) {
            return -Double.compare(pr1.getVentas(), pr2.getVentas());
        }
    }

    private Comparator<Producte> CMP_PRIZE = new Comparator<Producte>() {
        public int compare(Producte p1, Producte p2) {
            return (int) (p1.getPrecio() - p2.getPrecio());
        }
    };

    public void setListProductes(Map<String,Producte> listProductes) {
        this.listProductes = listProductes;
    }

    public Map<String, Producte> getListProductes() {
        return listProductes;
    }
}

