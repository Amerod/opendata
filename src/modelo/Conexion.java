/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
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
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost/db_agenda","root","123456");
        }
        catch(Exception e){
        
        }
        return con;
    }
}
