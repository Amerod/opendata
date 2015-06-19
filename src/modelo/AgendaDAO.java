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
public class AgendaDAO {
    Conexion conexion;
        
        public AgendaDAO(){
            conexion = new Conexion();
        }
        
        public String insertAgenda(Agenda entrada){
        String rptaRegistro=null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_insertAgenda(?,?,?,?,?,?,?)}");
            cs.setInt(1, entrada.getID());
            cs.setString(2, entrada.getActe());
            cs.setString(3, entrada.getDescripcio());
            cs.setString(4, entrada.getLloc());
            cs.setString(5, entrada.getData_ini());
            cs.setString(6, entrada.getDistricte());
            cs.setString(7, entrada.getURL());
            
            int numAfectadas = cs.executeUpdate();
            
            if (numAfectadas > 0){
                rptaRegistro = "registro exitoso.";
            }
        }catch(Exception e){}
            return rptaRegistro;
        }
    
}
