package com.company;

import javafx.beans.binding.ObjectExpression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {

    HashMap<String, List<Object>> functions = new HashMap<>();
    HashMap<String, List<Object>> lists = new HashMap<>();

    /**
     * La función parse está diseñada para llevar a cabo la tokenización y parseo en una sola función.
     * @param Programa Recibe la dirección del archivo estilo Common Lisp a operar.
     * @return Devuelve el parseo de los tokens.
     */
    public Object parse(String Programa) {
        Lexer mLexer = new Lexer();
        return readTokens(mLexer.tokenize(Programa));
    }

    /**
     * La función principal del parser. Esta obtiene una pila de tokens (del lexer) y crea una listas dependiendo
     * de los paréntesis o agrega un atom a la lista.
     * @param tokens Recibe una pila con todos los tokens del programa descompuestos por el lexer.
     * @return Devuelve una lista con listas o atoms dentro de ella. Esta define la estructura del programa.
     */
    public Object readTokens(Pila<String> tokens) {
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

    /**
     * Esta función transforma los tokens en sus respectivos tipos para juego ser evaluados.
     * @param token Recibe un token, el cuál es un string del lexer.
     * @return Devuelve un atom (Número o String).
     */
    public Object createAtom(String token) {
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

    /**
     * Esta función está diseñada para recibir la lista que da como resultado la función parse (la cual tiene el
     * "árbol" del programa). Recorre esta lista y dependiendo de las funciones va recorriendo cada lista con recursión
     * y devolviendo el resultado.
     * @param list: La lista que da como resultado la función "parse".
     * @return Usa recursión para devolver resultados dependiendo de en qué fase de la operación se encuentra y continuar
     * recorriendo hasta que no haya más elementos.
     */
    public Object evaluate(List list) {
        //Mientras aun haya elementos en la lista evalue.
        while(list.size() > 0) {
            //Primer elemento de la lista (funciona como un Stack).
            Object atom = list.remove(0);

            //Si el primer elemento es un String verifique la función que se desea operar.
            if (atom instanceof String) {
                System.out.println(atom); //Prueba.

                switch (atom.toString()) {
                    //Si es una suma, realice la operación.
                    case "+": {
                        Double cont = 0.0;

                        Object j = list.remove(0);

                        if (j instanceof List){
                            j = evaluate((List) j);
                            cont = (Double) j;
                        } else if (j instanceof Number){
                            cont = (Double) j;
                        }

                        while (list.size() > 0) {
                            Object x = list.remove(0);

                            if (x instanceof Number) {
                                cont += (Double) x;

                                //Si adentro de la suma hay una lista, evalue la lista.
                            } else if (x instanceof List) {
                                cont += (Double) evaluate((List) x);
                            }
                        }
                        System.out.println("Resultado +: " + cont);
                        return cont;
                    }

                    //Si es una resta, realice la operación.
                    case "-": {
                        Double cont = 0.0;

                        Object x = list.remove(0);

                        if (x instanceof List){
                            x = evaluate((List) x);
                            cont = (Double) x;
                        } else if (x instanceof Number){
                            cont = (Double) x;
                        }

                        while (list.size() > 0) {
                            Object j = list.remove(0);

                            if (j instanceof Number) {
                                cont -= (Double) j;

                                //Si hay una lista adentro, evalue la lista.
                            } else if (j instanceof List) {
                                cont -= (Double) evaluate((List) j);
                            }
                        }
                        System.out.println("Resultado -: " + cont);
                        return cont;
                    }

                    //Si es una multiplicación, realice la operación.
                    case "*": {
                        Double cont = 1.0;

                        //Primer elemento.
                        Object j = list.remove(0);

                        if (j instanceof List){
                            j = evaluate((List) j);
                            cont = (Double) j;
                        } else if (j instanceof Number){
                            cont = (Double) j;
                        }

                        //Elementos siguientes.
                        while (list.size() > 0) {
                            Object x = list.remove(0);

                            if (x instanceof Number) {
                                cont *= (Double) x;

                                //Si adentro de la operación hay una lista, evalua.
                            } else if (x instanceof List) {
                                cont *= (Double) evaluate((List) x);
                            }
                        }
                        System.out.println("Resultado *: " + cont);
                        return cont;
                    }

                    //Si es una división, realice la operación.
                    case "/": {
                        Double cont = 1.0;
                        Object x = list.remove(0);

                        if (x instanceof List){
                            x = evaluate((List) x);
                            cont = (Double) x;
                        } else if (x instanceof Number){
                            cont = (Double) x;
                        }

                        while (list.size() > 0) {
                            Object j = list.remove(0);

                            if (j instanceof Number) {
                                cont /= (Double) j;

                                //Si adentro hay una lista, evalua.
                            } else if (j instanceof List) {
                                cont /= (Double) evaluate((List) x);
                            }
                        }

                        System.out.println("Resultado /: " + cont);
                        return cont;
                    }

                    //Menor que.
                    case "<": {
                        Boolean res = false;
                        Double cont = 0.0;

                        //Primer elemento.
                        Object j = list.remove(0);

                        if (j instanceof List){
                            j = evaluate((List) j);
                            cont = (Double) j;
                        } else if (j instanceof Number){
                            cont = (Double) j;
                        }

                        //Segundo elemento
                        Object x = list.remove(0);

                        if (x instanceof Number) {
                            res = cont < (Double) x;
                            //Si adentro hay una lista, evalue.
                        } else if (x instanceof List) {
                            res = cont < (Double) evaluate((List) x);
                        }

                        System.out.println("Resultado <: " + res);
                        return res;
                    }

                    //Mayor que.
                    case ">": {
                        Boolean res = false;
                        Double cont = 0.0;

                        //Primer elemento.
                        Object j = list.remove(0);

                        if (j instanceof List){
                            j = evaluate((List) j);
                            cont = (Double) j;
                        } else if (j instanceof Number){
                            cont = (Double) j;
                        }

                        //Segundo elemento
                        Object x = list.remove(0);

                        if (x instanceof Number) {
                            res = cont > (Double) x;
                            //Si adentro hay una lista, evalue.
                        } else if (x instanceof List) {
                            res = cont > (Double) evaluate((List) x);
                        }

                        System.out.println("Resultado >: " + res);
                        return res;
                    }

                    //Comparación de igualdad.
                    case "EQUAL": {
                        Boolean res = false;

                        //Primer elemento.
                        Object j = list.remove(0);

                        if (j instanceof List){
                            j = evaluate((List) j);
                        }

                        //Segundo elemento
                        Object x = list.remove(0);

                        if (x instanceof Number) {
                            res = j.equals(x);
                            //Si adentro hay una lista, evalue.
                        } else if (x instanceof List) {
                            res = j.equals(evaluate((List) x));
                        }

                        System.out.println("Resultado EQUAL: " + res);
                        return res;
                    }

                    //Operación COND.
                    case "COND":{
                        while (list.size() > 0) {
                            Object operation = list.remove(0);
                            System.out.println(operation);

                            if (operation instanceof List){
                                if (((List) operation).size() == 2){
                                    //El primer elemento de una operación siempre es el test y el segundo es una acción.
                                    Object test = ((List) operation).get(0);
                                    Object action = ((List) operation).get(1);

                                    if (test instanceof List){
                                        test = evaluate((List) ((List) operation).get(0));
                                    } else if (test.equals("TRUE") || test.equals("T")){
                                        test = true;
                                    } else if (test.equals("FALSE") || test.equals("NIL")){
                                        test = false;
                                    }

                                    //Si el resultado de evaluar test es Booleano y es TRUE, ejecuta la acción.
                                    if(test instanceof Boolean){
                                        if ((Boolean) test){
                                            Object result = evaluate((List) action);
                                            System.out.println("Resultado COND: " + result);
                                            return result;
                                        }
                                    }
                                }
                            }
                        }

                        break;
                    }

                    //Evalua si es una lista o no.
                    case "ATOM": {
                        while  (list.size() > 0){
                            Object x = list.remove(0);
                            if (lists.containsKey(x)){
                                System.out.println("TRUE");
                                return true;
                            } else {
                                System.out.println("FALSE");
                                return false;
                            }
                        }
                        break;
                    }

                    //Crea una lista y la guarda en el HashMap lists.
                    case "LIST": {

                        String name =(String) list.remove(0);

                        Object x = list.remove(0);

                        if (x instanceof List){
                            lists.put(name, (List<Object>) x);

                        } else {
                            System.out.println("El elemento no es una lista.");
                        }

                        System.out.println(lists.get(name));
                        break;
                    }

                    //Definición de funciones.
                    case "DEFUN":  {
                        String name = (String) list.remove(0);
                        List<Object> function = new ArrayList<>();

                        while (list.size() > 0) {
                            Object x = list.remove(0);
                            int i = 0;
                            if (x instanceof List) {
                                function.add(i, x);
                                i++;
                            }
                        }
                        //Lo agrega al HashMap functions.
                        functions.put(name, function);
                        break;
                    }

                    //Si no es ninguna de las anteriores, busca alguna operación igual en el HashMap de funciones.
                    default: {
                        //Si el nombre está entre las funciones.
                        if (functions.containsKey(atom)) {
                            System.out.println("La función está definida!");

                            String name = (String) atom;
                            System.out.println("Función: " + name); //Prueba.

                            List op = (List) functions.get(atom).get(0);
                            List operation = cloneList(op);

                            System.out.println("Operación: " + op); //Prueba.

                            List param = (List) functions.get(atom).get(1);
                            System.out.println("Parámetros: " + param); //Prueba.

                            while (list.size() > 0) {
                                Object x = list.remove(0);

                                if (x instanceof List) {
                                    //Cuando recibe una operación en vez de parámetros como tal.
                                    if (((List) x).get(0) instanceof String){
                                        List mList = new ArrayList();
                                        x = evaluate((List) x);
                                        System.out.println(x);
                                        mList.add(x);
                                        if (mList.size() == param.size()) {
                                            op = substitute(param, operation,(List) mList);
                                            System.out.println(op);
                                            Object result = evaluate(op);
                                            System.out.println("Resultado " + name + ": " + result);
                                            return result;
                                        }
                                    } else {
                                        if (((List) x).size() == param.size()) {
                                            op = substitute(param, operation,(List) x);
                                            System.out.println(op);
                                            Object result = evaluate(op);
                                            System.out.println("Resultado " + name + ": " + result);
                                            return result;
                                        }
                                    }
                                }
                            }
                        } else {
                            System.out.println("La función no es válida en el programa.");
                        }
                        break;
                    }
                }
                //Si es un número, devuelve el número.
            } else if (atom instanceof Number) {
                System.out.println(atom.toString());
                return atom;

                //Si el primer elemento es una lista, evalua (recursión).
            } else if (atom instanceof List) {
                evaluate((List) atom);
            }
        }
    return null;
    }

    /**
     * Esta función está diseñada para sustituir todos los parámetros de una operación por los valores deseados.
     * @param param La lista de parametros de la operación.
     * @param operation La operación a llevar a cabo.
     * @param x Los valores que el usuario le quiere dar al parámetro en la función.
     * @return
     */
    public List substitute (List param, List operation, List x) {
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

    /**
     * El próposito de esta lista es poder hacer un deep clone a listas definidas en el programa. Esto hace posible que la lista clonada
     * sea modificada sin alterar la lista original.
     * @param list La lista que se desea clonar.
     * @return Devuelve la lista clonada.
     */
    public List cloneList (List list){
        List newList = new ArrayList();
        for (Object i: list){
            if (i instanceof List){
                newList.add(cloneList((List) i));
            }
            else {
                Object x = createAtom(String.valueOf(i));
                newList.add(x);
            }
        }
        return newList;
    }
}


