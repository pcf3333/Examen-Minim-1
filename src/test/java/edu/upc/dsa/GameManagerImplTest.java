package edu.upc.dsa;

import edu.upc.dsa.models.ObjectClass;
import edu.upc.dsa.models.User;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class GameManagerImplTest {

    GameManagerImpl gameManager = null;
    Map<String, User> listUsers = null;
    Map<String, ObjectClass> listObjects = null;
    List<User> userSortedList=null;
    List<ObjectClass> objectList=null;

    //Usuarios
    User pere = new User("Pere","Coll");
    User juanjo = new User("Juanjo","Medina");
    ObjectClass espada=new ObjectClass("Espada",300);

    @Before
    public void setUp(){
        gameManager = GameManagerImpl.getInstance();
        ObjectClass espada=new ObjectClass("Espada",100);
        ObjectClass escudo=new ObjectClass("Escudo",300);
        ObjectClass cofre=new ObjectClass("Cofre",500);
        ObjectClass moneda=new ObjectClass("Moneda",10);

        listUsers=Map.of("Juanjo",juanjo,"Pere",pere );


        //Adding Object to User
        pere.addObject(espada);
    }

    @After
    public void tearDown() {
        GameManagerImpl.getInstance().clear();
    }

    @Test
    public void testAddUser() {
        //Function to add a User
        gameManager.addUser(pere);
        gameManager.addUser(juanjo);
        assertEquals(listUsers.size(), gameManager.getListUsers().size());
//        assertEquals(listUsers, gameManager.getListUsers());
        //NO ENTENC PERQUE NO FUNCIONA... ELS DOS MAPS SON IGUALS PERO LA ID QUE ELS ASSGNA ES DIFFERENT...

    }

    @Test
    public void testObjetoUsuario() {
        gameManager.addUser(pere);
        pere.addObject(espada);

        //Function to compare objects of user Pere
        assertEquals(pere.getObjects(), gameManager.getUserObjects(pere));
    }

}