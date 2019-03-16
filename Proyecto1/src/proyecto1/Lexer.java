/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.io.*;
import java.util.*;

/**
 *
 * @author moyha
 */
public class Lexer {
   
    int parentAbierto = 0;
    int parentCerrado = 0;
    
    public ArrayList ReadFile(String line){  
        ArrayList<List> codigo = new ArrayList<>();
        try{
                FileReader fileReader = new FileReader("datos.txt");

                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while((line = bufferedReader.readLine()) != null) {                          
                        
                    }
                bufferedReader.close(); 
                }           
        catch(Exception e){
                        System.out.println(e);
                }

        return codigo;
    }
}

