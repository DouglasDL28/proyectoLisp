/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

/**
 *
 * @author moyha
 */

import java.util.ArrayList;

public class Stack<String> {

    ArrayList<String> arr = new ArrayList<String>(); //Se crea un vector con elementos E
    
    public void push(String item){
        this.arr.add(item); //Se instacea y se agrega al vector
    }
    
    public String pop(){ // Se crea variable int para guardar el tamaÃ±o del vector creado
        return this.arr.remove(arr.size()- 1); // Se istacea y se le quita 1 unidad para que las posiciones empiecen desde 1 y no en 0
        //Tambien se elimina un elemento del vector
    } 
    
    public String peek(){
        int algo = arr.size();// Se crea variable int para guardar el tamaÃ±o del vector creado
        return this.arr.get(algo-1);// Se istacea y se le quita 1 unidad para que las posiciones empiecen desde 1 y no en 0
        //Toma el ultimo valor del Vector
    }
    
    public boolean empty(){
        if(this.arr.isEmpty()){ //Se instancea
            return true;
            //Si el vector esta vacio se devolverÃ¡ true
        }
        else{
            return false;
            //Si el vector no esta vacio se devolverÃ¡ false
        }
    }
    
    public int size(){
        return this.arr.size(); //Instaciar
    }
}