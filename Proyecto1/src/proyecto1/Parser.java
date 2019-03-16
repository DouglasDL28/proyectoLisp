/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

/**
 *
 * @author moyha
 */
import java.util.*;

public class Parser {
    
    double res;
    int contador = 0;
    String o = " ";
    String t = " ";
    double var1 = 0;
    double var2 = 0;
    double l = 0;
    double g = 0;
    String n;
    boolean y=true;
    ArrayList prueba = new ArrayList();
                                    
    Stack stack = new Stack();
    public void Artimetica(String line){
        char[] datos = line.toCharArray();// Array de Chars

                    for(int i = 0; i < datos.length; i++){
                    l=0;
                    System.out.println(prueba);
                    // Se recorre el array y se comprueba si se tiene la cantidad suficiente de datos     
                        switch(datos[i]){
                            case '+': // Si se encuentra un operando +
                            case '-': // Si se encuentra un operando -
                            case '*':// Si se encuentra un operando *
                            case '/': // Si se encuentra un operando /
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':    
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                            case '0':
                                stack.push(Character.toString(datos[i]));
                                prueba.add(datos[i]);
                                //Se pushean los datos al vector
                                break;
                            case ')':     
                                contador = 0;
                                ArrayList<String> operandos = new ArrayList<>();
                                // for aqui
                                // for int i =0; i menor size stack; i++
                                // 
                                y=true;
                                while(y){
                                    // while por if
                                    n = (String)stack.peek();
                                    t = n;
                                    if("+".equals(t) || "-".equals(t) || "*".equals(t) || "/".equals(t)){
                                        y=false;
                                        o = n;
                                    }
                                    else{
                                        y = true;
                                        n= (String)stack.pop();
                                        prueba.remove(prueba.size() -1);
                                        o = n;
                                        operandos.add(o);
                                    }

                                }
                                System.out.println(o);
                                if(o.equals("+")){
                                    for(String p: operandos){
                                        double k = Double.parseDouble(p);
                                        l += k;
                                    }
                                }
                                else if(o.equals("-")){
                                    for(String p: operandos){
                                        if(contador==0){
                                            var1 = Double.parseDouble(p);
                                        }
                                        else if(contador==1){
                                            var2=Double.parseDouble(p);
                                            l=var1-var2;
                                        }
                                        else{
                                            var1=Double.parseDouble(p);
                                            l -= var1;
                                        }
                                        contador++;
                                    }
                                }
                                else if(o.equals("*")){
                                    for(String p: operandos){
                                        if(contador==0){
                                            var1 = Double.parseDouble(p);
                                        }
                                        else if(contador==1){
                                            var2=Double.parseDouble(p);
                                            l=var1*var2;
                                        }
                                        else{
                                            var1=Double.parseDouble(p);
                                            l *= var1;
                                        }
                                        contador++;
                                    }
                                }
                                else if(o.equals("/")){
                                    for(String p: operandos){
                                        if(p == "0"){
                                            System.out.println("No se puede hacer.");
                                        }
                                        else{
                                            if(contador==0){
                                            var1 = Double.parseDouble(p);
                                            }
                                            else if(contador==1){
                                                var2=Double.parseDouble(p);
                                                l=var1/var2;
                                            }
                                            else{
                                                var1=Double.parseDouble(p);
                                                l /= var1;
                                            }
                                            contador++;
                                        }
                                    }
                                }
                                String u = Double.toString(l);                             
                                stack.pop();
                                prueba.remove(prueba.size()-1);
                                stack.push(u);
                                prueba.add(u);
                                System.out.println(stack.peek());
                                break;
                            default:
                                break;
                            }
                    }
            System.out.println("La respuesta es: " + stack.pop()); //Se da a conocer la respuesta en pantalla
    }
}
