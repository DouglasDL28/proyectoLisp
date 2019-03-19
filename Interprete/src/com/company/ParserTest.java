package com.company;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ParserTest extends Parser {

    HashMap<String, List<Object>> functions = new HashMap<>();
    HashMap<String, List<Object>> lists = new HashMap<>();
    @Test
    public Object readTokens() {
        String token = tokens.pop();
            if (token.equals("(")) {
                List L = new ArrayList();
                while (!tokens.peek().equals(")")) {
                    L.add(readTokens(tokens));
                }
                tokens.pop(); //Pop al ")".
                return L;
            } else {
                return createAtom(token);
            }
    }

    @Test
    void createAtom() {
        try {
            return Double.parseDouble(token);
        } catch (Exception e) {
            try {
                return Integer.parseInt(token);
            } catch (Exception e2) {
                return token;
            }
        }
    }

    @Test
    void substitute() {
        List op = operation;
        for (Object i: op){
            for (Object j: param){
                if (i instanceof List){
                    substitute(param,(List) i, x);
                } else if (i.equals(j)){
                    op.set(operation.indexOf(i), ((List) x).get(param.indexOf(j)));
                }
            }
        }
        return op;
    }

    @Test
    public List testcloneList( List list) {
        List<String> list = Arrays.asList("1", "2", "3", "4", "5");
        List newList = new ArrayList();
        for (Object i: list){
            if (i instanceof List){
                newList.add(cloneList((List) i));
            }
        }
        return newList;
    }
}
