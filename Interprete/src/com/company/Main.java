package com.company;

import java.util.List;
import java.util.Scanner;

public class Main {

     public static String menu = "MENÚ: \n" +
             "\t 1. Usar interprete de LISP. \n" +
            "\t 2. Salir del programa.";

    public static void main (String []args){

        boolean wantsToContinue = true;
        Scanner input = new Scanner(System.in);
        Parser myParser = new Parser();

        do {
            System.out.println(menu);
            int option = input.nextInt();
            switch (option){
                case 1: {
                    System.out.print("Ingrese el archivo de texto que desea procesar: ");
                    String userFile = input.nextLine();
                    userFile = input.nextLine();

                    System.out.println("Programa: "+ myParser.parse(userFile));

                    myParser.evaluate((List) myParser.parse(userFile));

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
