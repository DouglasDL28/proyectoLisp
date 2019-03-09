package com.company;

import java.util.ArrayList;
import java.util.Vector;

public class Pila<String> {

    public ArrayList<String> data;

    public Pila () {
        this.data = new ArrayList<>();
    }

    public boolean empty() {
        return (data.size() == 0);
    }


    public int size() {
        return data.size();
    }

    public String peek() {
        return data.get(0);
    }

    public String pop() {
            return data.remove(0);
    }

    public void push(String item) {
        data.add(item);
    }

    public java.lang.String toString() {

        java.lang.String result = "";
        for (String i: data) {
            result = result + i;
        }

        return result;
    }
}
