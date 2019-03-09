package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Lexer {
    ArrayList<String> tokens = new ArrayList<>();
    String archivo;
    int parentCerrado = 0;
    int parentAbirto = 0;


    public ArrayList<String> tokenize (String archivo) {
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
                    tokens.add(i);
                }
            });
        } catch (
                IOException exception) {
            System.out.println("Error!");
        }
        return tokens;
    }

}
