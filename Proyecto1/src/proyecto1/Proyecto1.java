/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author moyha
 */
public class Proyecto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Parser pars = new Parser();
        String fileName = "datos.txt";
        String line = null;
        double x = 0;
        double y = 0;
        double res = 0;

        //Try para leer el documento
        try{
        FileReader fileReader = new FileReader(fileName); // Se crea el lector de documento
         
        BufferedReader bufferedReader = new BufferedReader(fileReader);//Secrea bufferedReader

        //Ciclo que corre mientras se pueda leer el documento
            while((line = bufferedReader.readLine()) != null) {
                    pars.Artimetica(line);
                    }
                bufferedReader.close(); 
        } 

        

        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");    
                //Si no se puede abrir o no se encuentra el archivo            
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");
                //Si no se puede abrir o no se encuentra el archivo                      
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
}
  

