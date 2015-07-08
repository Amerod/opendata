/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.io.*;
import java.sql.*;
/**
 * 
 * @author andres
 */
public class Conexion {
    public Conexion(){

    } 
    public Connection getConexion(){
                Connection con = null;
        try{
            File archivo = new File("DatosDB.txt");
            FileReader lee = new FileReader(archivo);
            BufferedReader br = new BufferedReader(lee);
            String[] datos;
            String linea = br.readLine();
            datos = linea.split(",");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost/"+datos[2],datos[0],datos[1]);
        }
        catch(Exception e){
        
        }
        return con;
    }
}
