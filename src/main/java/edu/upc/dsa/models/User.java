package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class User {
    private List<ObjectClass> objects = new ArrayList<>(){};
    private String name;
    private String surname;
    private String id;

    public User(){
    }

    public User(String n,String s){
        this.setName(n);
        this.setSurname(s);
        this.setId(RandomUtils.getId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
