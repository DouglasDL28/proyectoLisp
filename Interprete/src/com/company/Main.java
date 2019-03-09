package com.company;

import com.company.Pila;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

     public static String menu = "MENÚ: \n" +
             "\t 1. Usar interprete de LISP. \n" +
            "\t 2. Salir del programa.";

    public static void main (String []args){

        boolean wantsToContinue = true;

        Scanner input = new Scanner(System.in);
        Pila<String> tokens = new Pila<>();
        Lexer myLexer = new Lexer();
        Parser myParser = new Parser();

        do {
            System.out.println(menu);
            int option = input.nextInt();
            switch (option){
                case 1: {
                    System.out.print("Ingrese el archivo de texto que desea procesar: ");
                    String userFile = input.nextLine();
                    userFile = input.nextLine();

                   for(String i: myLexer.putInArray(userFile)) {
                       tokens.push(i);
                   }

                    System.out.println(tokens.toString());

                    for (String token: tokens.data) {
                        switch(token){
                            case "+": {
                                tokens.pop();
                                Double a = Double.parseDouble(tokens.pop());
                                Double b = Double.parseDouble(tokens.pop());
                                Double resultado = myParser.sumar(a, b);
                                tokens.push(resultado.toString());
                                break;
                            }

                        }
                    }

                    System.out.println(tokens.peek());
                    break;
                }

                case 2: {
                    wantsToContinue = false;
                    break;
                }
            }

        } while (wantsToContinue);
    }
}
