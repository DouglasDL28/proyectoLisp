package com.company;

import java.util.ArrayList;

public class Parser {

    public void parse(String Programa) {

    }

    public void readTokens(String programa){
        Lexer myLexer = new Lexer();
        ArrayList<String> mList = new ArrayList<>();

        Pila<String> tokens = myLexer.tokenize(programa);
         String token = tokens.pop();
        if (token.equals("(")){
            switch (token){
                case "+": {
                    tokens.pop();
                    Double a = Double.parseDouble(tokens.pop());
                    Double b = Double.parseDouble(tokens.pop());
                    Double resultado = sumar(a, b);
                    tokens.push(resultado.toString());
                    break;
                }
            }

        }

    }

    public Object createAtom (String token) {
        try {
            return Integer.parseInt(token);
        } catch (Exception e) {
            try {
                return Float.parseFloat(token);
            } catch (Exception e2) {
               return token;
            }
        }



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
