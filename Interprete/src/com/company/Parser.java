package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {

    HashMap<String, List<Object>> functions = new HashMap<>();

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

                        while (list.size() > 0) {
                            Object x = list.remove(0);

                            if (x instanceof Number) {
                                cont += (Double) x;
                                System.out.println(cont);

                                //Si adentro de la suma hay una lista, evalue la lista.
                            } else if (x instanceof List) {
                                cont += (Double) evaluate((List) x);
                            }
                        }
                        System.out.println(cont);
                        return cont;
                    }

                    //Si es una resta, realice la operación.
                    case "-": {
                        Double cont = 0.0;

                        while (list.size() > 0) {
                            Object x = list.remove(0);

                            if (x instanceof Number) {
                                cont -= (Double) x;
                                System.out.println(cont);

                                //Si hay una lista adentro, evalue la lista.
                            } else if (x instanceof List) {
                                cont -= (Double) evaluate((List) x);
                            }
                        }
                        System.out.println(cont);
                        return cont;
                    }

                    //Si es una multiplicación, realice la operación.
                    case "*": {
                        Double cont = (Double) list.remove(0);

                        while (list.size() > 0) {
                            Object x = list.remove(0);

                            if (x instanceof Number) {
                                cont *= (Double) x;
                                System.out.println(cont);

                                //Si adentro de la operación hay una lista, evalua.
                            } else if (x instanceof List) {
                                cont *= (Double) evaluate((List) x);
                            }
                        }
                        System.out.println(cont);
                        return cont;
                    }

                    //Si es una división, realice la operación.
                    case "/": {
                        Double cont = (Double) list.remove(0);

                        while (list.size() > 0) {
                            Object x = list.remove(0);

                            if (x instanceof Number) {
                                cont /= (Double) x;
                                System.out.println(cont);

                                //Si adentro hay una lista, evalua.
                            } else if (x instanceof List) {
                                cont /= (Double) evaluate((List) x);
                            }
                        }


                        System.out.println(cont);
                        return cont;
                    }

                    //Menor que.
                    case "<": {
                        Boolean res = false;
                        Double cont = (Double) list.remove(0);

                        while (list.size() > 0) {
                            Object x = list.remove(0);

                            if (x instanceof Number) {
                                res = cont < (Double) x;
                                System.out.println(cont);

                                //Si adentro hay una lista, evalue.
                            } else if (x instanceof List) {
                                res = cont < (Double) evaluate((List) x);
                            }
                        }

                        System.out.println(res);
                        return res;
                    }

                    //Mayor que.
                    case ">": {
                        Boolean res = false;
                        Double cont = (Double) list.remove(0);

                        while (list.size() > 0) {
                            Object x = list.remove(0);
                            if (x instanceof Number) {
                                res = cont > (Double) x;
                                System.out.println(cont);

                                //Si adentro hay una lista, evalue.
                            } else if (x instanceof List) {
                                res = cont > (Double) evaluate((List) x);
                            }
                        }

                        System.out.println(res);
                        return res;
                    }

                    //Comparación de igualdad.
                    case "EQUAL": {
                        Boolean res = false;
                        Object cont = list.remove(0);

                        while (list.size() > 0) {
                            Object x = list.remove(0);
                            if (x instanceof Number || x instanceof String) {
                                res = cont.equals(x);
                                System.out.println(cont);

                                //Si adentro hay una lista, evalue.
                            } else if (x instanceof List) {
                                res = cont.equals((Double) evaluate((List) x));
                            }
                        }
                        System.out.println(res);
                        return res;
                    }

                    case "COND":{
                        //Lista con las operaciones.
                        while (list.size() > 0){
                            //Lista con las operaciones.
                            Object op = list.remove(0);
                            if (op instanceof List){
                                if (((List) op).size() == 2) {
                                    System.out.println(((List) op).get(0));
                                    Object test = evaluate((List) ((List) op).get(0)); //Test (debe devolver booleano).

                                    if (test instanceof Boolean || (Boolean) test){
                                        evaluate((List) ((List) op).get(1)); //Si el test devuelve True, realiza la acción.
                                    } else {
                                        System.out.println("No se pudo.");
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
                            if (x instanceof List){
                                System.out.println("TRUE");
                                return true;
                            } else {
                                System.out.println("FALSE");
                                return false;
                            }
                        }
                        break;
                    }

                    //Definición de funciones.
                    case "DEFUN": {
                        System.out.println("Encuentra defun.");
                        String name = (String) list.remove(0);
                        List<Object> function = new ArrayList<>();

                        while (list.size() > 0) {
                            Object x = list.remove(0);
                            System.out.println("Encuentra: " + x);
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
                        System.out.println("Llegué al default");
                        if (functions.containsKey(atom)) {
                            String name = (String) atom;
                            System.out.println(name); //Prueba.

                            List operation = (List) functions.get(atom).get(0);
                            System.out.println(operation); //Prueba.

                            List param = (List) functions.get(atom).get(1);
                            System.out.println(param); //Prueba.

                            while (list.size() > 0) {
                                Object x = list.remove(0);
                                System.out.println(x); //Prueba.

                                if (x instanceof List) {
                                    if (((List) x).size() == param.size()) {
                                        System.out.println("Tamaño igual.");
                                        for (Object i: operation) {
                                            for (Object j: param){
                                                if(i.equals(j)){
                                                    operation.set(operation.indexOf(i), ((List) x).get(param.indexOf(j)));
                                                }
                                            }
                                        }
                                        evaluate((List) operation);
                                    }
                                }
                            }
                        } else {
                            System.out.println("La función no es válida en el programa.");
                        }
                        break;
                    }
                }
            } else if (atom instanceof Number) {
                System.out.println(atom.toString());

            } else if (atom instanceof List) {
                System.out.println("Lista");
                evaluate((List) atom);
            }
        }
    return null;
    }
}
