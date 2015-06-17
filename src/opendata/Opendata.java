package opendata;
import java.io.*;
import opendata.modelo.Agenda;
/**
 *
 * @author andres
 */
public class Opendata {
    
    public static Agenda crear_entrada(BufferedReader br){
        String linea = "";
        String separador = ";";
        String[] datos = null;
        Agenda entrada = null;
        try{
            
                
            if ((linea = br.readLine()) != null){
                datos = linea.split(separador);     
                if (datos[0].equals("﻿ID")){
                   linea = br.readLine();
                   datos = linea.split(separador);
                };
                entrada = new Agenda(datos);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return entrada;
        }
    }
    
    public static void mostrar_entrada(Agenda entrada){
       // System.out.println("-- Datos de la entrada -- ");
        System.out.println("ID : "+entrada.getID());
      /*  System.out.println("Nombre : "+entrada.getActe());
        System.out.println("Descripcion : "+entrada.getDescripcio());
        System.out.println("Lugar : "+entrada.getLloc());
        System.out.println("Fecha : "+entrada.getData_ini());
        System.out.println("Distrito : "+entrada.getDistricte());
        System.out.println("URL : "+entrada.getURL());*/
    };
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File archivo = null;
        FileReader lee = null;
        BufferedReader br = null;
        
        try{
            
        archivo = new File("src\\datos.csv");
        lee = new FileReader(archivo);
        br = new BufferedReader (lee);
        
        Agenda entrada = crear_entrada(br);
        
        while (entrada != null){
            mostrar_entrada(entrada);
            entrada = crear_entrada(br);
        }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                lee.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
}
