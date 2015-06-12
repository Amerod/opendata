package opendata;
import java.io.*;
/**
 *
 * @author andres
 */
public class Opendata {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File archivo = null;
        FileReader lee = null;
        BufferedReader br = null;
        String linea = "";
        String separador = ";";
        // TODO code application logic here
        try{
            archivo = new File("src\\datos.csv");
            lee = new FileReader (archivo);
            br = new BufferedReader (lee);
            while((linea = br.readLine()) != null){
            String[] datos = linea.split(separador);
            if (!datos[0].equals("﻿ID")){
                   System.out.println(datos[0]);
            };
            };
        } catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                lee.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
}
