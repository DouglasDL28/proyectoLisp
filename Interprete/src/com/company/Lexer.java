package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Lexer {
    Pila<String> tokens = new Pila<>();
    String archivo;
    int parentCerrado = 0;
    int parentAbirto = 0;


    /**
     * Separa el texto en el programa y crea una pila de tokens, los cuales son todos los elementos del programa (incluyendo paréntesis).
     * @param archivo El archivo de texto con el programa en Common Lisp.
     * @return Devuelve una pila con todos los tokens.
     */
    public Pila<String> tokenize (String archivo) {
        try {
            Stream<String> lines = Files.lines(
                    Paths.get(archivo),
                    StandardCharsets.UTF_8
            );
            lines.forEach(line -> {
                String[] parts = line.replace("(", "( ").replace(")", " )").split(" ");
                for (String i : parts) {
                    if (i.contains("(")){
                        parentAbirto ++;

                    }
                    if (i.contains(")")){
                        parentCerrado ++;
                    }

                    i = i.toUpperCase();
                    tokens.push(i);
                }
            });
        } catch (
                IOException exception) {
            System.out.println("Error!");
        }
        return tokens;
    }

}
