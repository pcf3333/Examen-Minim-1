package edu.upc.dsa;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.ObjectClass;

import java.util.List;

public interface GameManager {
    public List<User> usuariosOrdAlpha();
    public void addUser(User u);
    public void modifyUser(User u, String newName, String newSurname,List<ObjectClass> newObjects);
    public int getHowManyUsers();
    public User infoUser(String s);
    public void addObject(User u, ObjectClass o);
    public List<ObjectClass> getUserObjects(User u);
    public int getHowManyObjects(User u);
    public void clear();
}
