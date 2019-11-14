package edu.upc.dsa;

import edu.upc.dsa.models.ObjectClass;
import edu.upc.dsa.models.User;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class GameManagerImplTest {

    private GameManagerImpl gameManager = null;
    private List<User> userSortedList=new LinkedList<>();

    //Usuarios
    private User pere = new User("Pere","Coll");
    private User juanjo = new User("Juanjo","Medina");
    private ObjectClass espada=new ObjectClass("Espada",300);

    @Before
    public void setUp(){
        gameManager = GameManagerImpl.getInstance();
        ObjectClass espada=new ObjectClass("Espada",100);

        userSortedList.add(juanjo);
        userSortedList.add(pere);

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
        assertEquals(userSortedList.size(), gameManager.usersSortAlpha().size());
        assertEquals(userSortedList, gameManager.usersSortAlpha());
    }

    @Test
    public void testObjetoUsuario() {
        gameManager.addUser(pere);
        pere.addObject(espada);

        //Function to compare objects of user Pere
        assertEquals(pere.getObjects(), gameManager.getUserObjects(pere));
    }

}