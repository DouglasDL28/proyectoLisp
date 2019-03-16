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
        while(list.size() > 0) {
            Object atom = list.remove(0);
            System.out.println(atom.toString());

            if (atom instanceof String) {
                System.out.println(atom); //Prueba.

                switch (atom.toString()) {
                    case "+": {
                        Double cont = 0.0;

                        while (list.size() > 0) {
                            Object x = list.remove(0);

                            if (x instanceof Number) {
                                cont += (Double) x;
                                System.out.println(cont);

                            } else if (x instanceof List) {
                                cont += (Double) evaluate((List) x);
                            }
                        }
                        System.out.println(cont);
                        return cont;
                    }

                    case "-": {
                        Double cont = 0.0;

                        while (list.size() > 0) {
                            Object x = list.remove(0);

                            if (x instanceof Number) {
                                cont -= (Double) x;
                                System.out.println(cont);

                            } else if (x instanceof List) {
                                cont -= (Double) evaluate((List) x);
                            }
                        }
                        System.out.println(cont);
                        return cont;
                    }

                    case "*": {
                        Double cont = (Double) list.remove(0);

                        while (list.size() > 0) {
                            Object x = list.remove(0);

                            if (x instanceof Number) {
                                cont *= (Double) x;
                                System.out.println(cont);

                            } else if (x instanceof List) {
                                cont *= (Double) evaluate((List) x);
                            }
                        }
                        System.out.println(cont);
                        return cont;
                    }

                    case "/": {
                        Double cont = (Double) list.remove(0);

                        while (list.size() > 0) {
                            Object x = list.remove(0);

                            if (x instanceof Number) {
                                cont /= (Double) x;
                                System.out.println(cont);

                            } else if (x instanceof List) {
                                cont /= (Double) evaluate((List) x);
                            }
                        }


                        System.out.println(cont);
                        return cont;
                    }

                    case "<": {
                        Boolean res = false;
                        Double cont = (Double) list.remove(0);

                        while (list.size() > 0) {
                            Object x = list.remove(0);

                            if (x instanceof Number) {
                                res = cont < (Double) x;
                                System.out.println(cont);

                            } else if (x instanceof List) {
                                res = cont < (Double) evaluate((List) x);
                            }
                        }

                        System.out.println(res);
                        return res;
                    }

                    case ">": {
                        Boolean res = false;
                        Double cont = (Double) list.remove(0);

                        while (list.size() > 0) {
                            Object x = list.remove(0);
                            if (x instanceof Number) {
                                res = cont > (Double) x;
                                System.out.println(cont);
                            } else if (x instanceof List) {
                                res = cont > (Double) evaluate((List) x);
                            }
                        }

                        System.out.println(res);
                        return res;
                    }

                    case "EQUAL": {
                        Boolean res = false;
                        Double cont = (Double) list.remove(0);

                        while (list.size() > 0) {
                            Object x = list.remove(0);
                            if (x instanceof Number) {
                                res = cont.equals((Double) x);
                                System.out.println(cont);
                            } else if (x instanceof List) {
                                res = cont.equals((Double) evaluate((List) x));
                            }
                        }
                        System.out.println(res);
                        return res;
                    }

                    case "ATOM": {
                        Object x = list.remove(0);
                        if (x instanceof List){
                            System.out.println("TRUE");
                            return true;
                        } else {
                            System.out.println("FALSE");
                            return false;
                        }
                    }

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
                        functions.put(name, function);
                        break;
                    }

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
