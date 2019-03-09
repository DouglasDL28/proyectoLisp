package com.company;

import java.util.ArrayList;

public class Parser {

    public void parse(String Programa) {

    }

    public void readTokens(String programa){
        Lexer myLexer = new Lexer();

        ArrayList<String> tokens = myLexer.tokenize(programa);
        String token = tokens.remove(0);
        if (token.equals("(")){

        }

    }

    public Object createAtom (String token){

    }

    public Double sumar(Double a, Double b ){
        return a + b;
    }

    public Double resta(Double a, Double b ){
        return a - b;
    }

    public Double multiplicar(Double a, Double b ){
        return a * b;
    }

    public Double dividir(Double a, Double b ){
        return a / b;
    }
}
