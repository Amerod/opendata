/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        // TODO code application logic here
        try{
            archivo = new File("D:\\a\\datos.csv");
            lee = new FileReader (archivo);
            br = new BufferedReader (lee);
            while(br.readLine() != null){
            System.out.println(br.readLine());
            System.out.println();
        }

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
