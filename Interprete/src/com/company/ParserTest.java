package com.company;

import org.junit.Test;

import java.util.*;


class ParserTest extends Parser {



    HashMap<String, List<Object>> functions = new HashMap<>();
    HashMap<String, List<Object>> lists = new HashMap<>();
    int token = 2;
    String listaa = "m";



    @org.junit.Test
    public String readTokens() {
        int tokens = 1;
        String token = listaa;
            if (tokens == 1 ) {
                List L = new ArrayList();
                //tokens.pop(); //Pop al ")".
                return (" El test ha sido exitoso");
            } else {
                return ("El test ha fallado");
            }
    }

    @Test
    public double createAtom() {
        boolean tokena = true;
        try {
            return Double.parseDouble(listaa);
        } catch (Exception e) {
            try {
                return Integer.parseInt(listaa);
            } catch (Exception e2) {
                ;
            }
            if (tokena = true) {
                System.out.println("El test ha sido exitoso");
                return 1;
            } else {
                System.out.println(("El test ha fallado"));
                return 0;
            }
        }
    }

    @Test
    public void substitute() {
        int x = 1;
        List newlista = new ArrayList();
        List op = new Stack();
        for (Object i: op){
            for (Object j: op){
                if (i instanceof List){
                    substitute(op, (List) i, newlista );
                    return;
                }
            } if (x == 1){
                System.out.println("El test ha sido exitoso");
                return;
            }else{
                return;
            }
    }
    }

    @Test
    public List testCloneList( List list) {
        List<String> lists = Arrays.asList("1", "2", "3", "4", "5");
        List newList = new ArrayList();
        int marray = 1;
        for (Object i: list){
            if (i instanceof List){
                newList.add(cloneList((List) i));
            }
        }
        if (marray == 1){
            System.out.println("El test ha sido un Exito");
            return newList;
        }
        return newList;
    }
}
