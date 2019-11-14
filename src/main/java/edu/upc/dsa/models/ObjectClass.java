package edu.upc.dsa.models;

import java.util.ArrayList;

public class ObjectClass {
    private String name;
    private int value;

    public ObjectClass(){}

    public ObjectClass(String name, int value){
        this.name=name;
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
