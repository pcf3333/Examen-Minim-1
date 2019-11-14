package edu.upc.dsa;

import edu.upc.dsa.models.ObjectClass;
import edu.upc.dsa.models.User;

import java.util.*;

public class GameManagerImpl implements GameManager {
    private static GameManagerImpl Instance;
    private Map<String, User> listUsers;
    private Map<String, ObjectClass> listObjects;
    private GameManagerImpl(){
        this.listUsers=new HashMap<>();
        this.listObjects=new HashMap<>();
    }
    public static GameManagerImpl getInstance(){
        if (Instance==null)
            Instance=new GameManagerImpl();
        return Instance;
    }

    public Map<String, ObjectClass> getListObjects() {
        return listObjects;
    }

    public void setListObjects(Map<String, ObjectClass> listObjects) {
        this.listObjects = listObjects;
    }

    public Map<String, User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(Map<String, User> listUsers) {
        this.listUsers = listUsers;
    }

    public List<User> usuariosOrdAlpha() {
        List<User> lu=new ArrayList<>(listUsers.values());
        Collections.sort(lu, CMP_ALPHA);
        return lu;
    }

    public List<ObjectClass> getUserObjects(User u) {
        if (listUsers.containsKey(u.getName())) {
            List<ObjectClass> lo = new ArrayList<>(u.getObjects());
            return lo;
        }
        return null;
    }

    @Override
    public void clear() {
        this.listUsers.clear();
        this.listObjects.clear();
    }

    public void addUser(User u) {
        if (!listUsers.containsKey(u.getName())) {
            User user = new User(u.getName(), u.getSurname(), u.getObjects());
            listUsers.put(u.getName(), user);
        }
    }

    public void modifyUser(User u, String newName, String newSurname, List<ObjectClass> newObjects) {
        if (listUsers.containsKey(u.getName())) {
            u.changeUser(newName, newSurname,newObjects);
        }
    }

    public int getHowManyUsers() {
        return listUsers.size();
    }

    public int getHowManyObjects(User u) {
        if (listUsers.containsKey(u.getName())) {
            return u.getObjects().size();
        }
        return -1;
    }

    public User infoUser(String username) {
        if (listUsers.containsKey(username)) {
            return listUsers.get(username);
        }
        return null;
    }

    private Comparator<User> CMP_ALPHA = new Comparator<User>() {
        public int compare(User u1, User u2) {
            return u1.getName().compareTo(u2.getName());
        }
    };

    public void addObject(User u, ObjectClass o) {
        if (listUsers.containsKey(u.getName()) && o!=null) {
            if (!listObjects.containsKey(o.getName())) {
                ObjectClass object = new ObjectClass(o.getName(), o.getValue());
            }
            u.addObject(o);
        }
    }

}

/*public static class CompararPrecio implements Comparator<User> {
    public int compare(User pr1, User pr2) {
        return Double.compare(pr1.getPrecio(), pr2.getPrecio());
    }
}*/

