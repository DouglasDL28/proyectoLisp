package com.company;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class PilaTest extends Pila {

    @org.junit.jupiter.api.Test
    public boolean testEmpty() {
        ArrayList<String> arrpty = new ArrayList<>();
        System.out.println("empty");
        Object item = "prueba";
        Stack instance = new Stack();
        instance.push(item);
        instance.empty();
        if (arrpty.size() == 0){
            System.out.println("El test fue un exito");
            return true;
        }
        else{
            System.out.println("El test ha fallado");
            return false;
        }
    }

    @org.junit.jupiter.api.Test
    public String TestPeek() {
        System.out.println("Peek");
        ArrayList<String> arrPeek = new ArrayList<>();
        Object item = "Prueba";
        Stack instance = new Stack();
        instance.push(item);
        instance.peek();
        return ("EL test fue exitoso");
    }

    @org.junit.jupiter.api.Test
    public String testPop() {
        System.out.println("Pop");
        ArrayList<String> arrpop = new ArrayList<>();
        Object item = "Prueba";
        Stack instance = new Stack();
        instance.push(item);
        instance.pop();
        Object result = (Array.get(item, 0));
        Object expResult = null;
        if (result != expResult) {
            return ("El test ha fallado");
        }
        else {
            return ("El test ha sido Exitoso");
        }
    }

    @org.junit.jupiter.api.Test
    void push() {
        System.out.println("Push");
        ArrayList<String> arrpush = new ArrayList<>();
        Object item = "Prueba";
        Stack instance = new Stack();
        instance.push(item);
        Object result = "Prueba";
        Object expResult = "Prueba";
        if (result != expResult){
            System.out.println("El test ha fallado");
        }
        else {
            System.out.println("El test ha sido exitoso");
        }
    }
}