package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class User {
    private List<ObjectClass> objects;
    private String name;
    private String surname;

    public User(){
    }

    public User(String n, String s, List<ObjectClass> o){
        this.setObjects(o);
        this.setName(n);
        this.setSurname(s);
    }
    public User(String n,String s){
        this.setName(n);
        this.setSurname(s);
        this.objects=new ArrayList<>(){};
    }

    protected Comparator<User> CMP_ALPHA = new Comparator<User>() {
        public int compare(User u1, User u2) {
            return u1.getName().compareTo(u2.getName());
        }
    };

    // Collections.sort(productos, CMP_PRIZE)


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ObjectClass> getObjects() { return objects; }

    public void setObjects(List<ObjectClass> objects) { this.objects = objects; }

    public void addObject(ObjectClass o){
        this.objects.add(o);
    }

    public void changeUser(String name, String surname,List<ObjectClass> listObjects){
        this.name=name;
        this.objects=listObjects;
        this.surname=surname;
    }
}
