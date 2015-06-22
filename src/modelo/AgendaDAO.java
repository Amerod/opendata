/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
/**
 *
 * @author andres
 */
public class AgendaDAO {
    Conexion conexion;
        
        public AgendaDAO(){
            conexion = new Conexion();
        }
               
        public ArrayList<String> listPersona(){
        ArrayList listaPersona = new ArrayList();
        String tabla;
        try{
            Connection acceDB = conexion.getConexion();
            PreparedStatement ps = acceDB.prepareStatement("show tables");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                tabla = rs.getNString(1);
                listaPersona.add(tabla);
            }
                    
        }catch(Exception e){}
        return listaPersona;
    }
        
        public String insertAgenda(Agenda entrada){
        String rptaRegistro=null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_insertAgenda(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, entrada.getID());
            cs.setString(2, entrada.getActe());
            cs.setString(3, entrada.getDescripcio());
            cs.setString(4, entrada.getWeb_acte());
            cs.setString(5, entrada.getLloc());
            cs.setString(6, entrada.getData_ini());
            cs.setString(7, entrada.getData_fi());
            cs.setString(8, entrada.getDistricte());
            cs.setString(9, entrada.getURL());
            
            int numAfectadas = cs.executeUpdate();
            
            if (numAfectadas > 0){
                rptaRegistro = "registro exitoso.";
            }
        }catch(Exception e){}
            return rptaRegistro;
        }
        
        public boolean descargar(){
            boolean respuesta = false;
            
            try{
                File archivo = new File("src\\datos.csv");
                URL url = new URL("http://terrassaopendata.blob.core.windows.net/opendata/2015_AGENDA_ACTIVITATS_CIUTAT_TERRASSA.csv");
                URLConnection urlCon = url.openConnection();
                System.out.println(urlCon.getContentType());
                //Pasar de milisegundos a fecha
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                //Primero con el archivo nuevo
                long milliSeconds= urlCon.getDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(milliSeconds);
                String fechaNuevo = formatter.format(calendar.getTime());
                //fin del archivo nuevo
                System.out.println(fechaNuevo);//mostramos resultado
                //Ahora con el archivo viejo
                milliSeconds = archivo.lastModified();
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(milliSeconds);
                String fechaViejo = formatter.format(calendar.getTime());
                //Fin del arhivo viejo
                System.out.println(fechaViejo);//mostramos resultado

                //comparamos si la fecha es la misma no descarga el nuevo archivo
                if (!fechaViejo.equals(fechaNuevo)){
                    InputStream is = urlCon.getInputStream();
                    FileOutputStream fos = new FileOutputStream("src\\datos.csv");
                    byte [] array = new byte[1000];
                    int leido = is.read(array);
                    while (leido > 0) {
                       fos.write(array,0,leido);
                       leido=is.read(array);
                    }
                    is.close();
                    fos.close();
                    respuesta = true;
                }
            }catch(Exception e){
                e.getStackTrace();
            }
                return respuesta;
        }
        
        public String nuevaTabla(String nombre){
        String rptaRegistro;
        try{
            Connection accesoDB = conexion.getConexion();
            Statement ps = accesoDB.createStatement();
            ps.executeUpdate("create table "+nombre+" like agenda;");
            ps.executeUpdate("insert into "+nombre+" select * from agenda;");
            rptaRegistro ="Se ha creado correctamente.";
        }catch(Exception e){
            rptaRegistro = "algo fue mal.";
        }
        return rptaRegistro;
        }
        
        public Agenda crear_entrada(BufferedReader br){
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
        
        public String insertarTodo(){
            String respuesta = "No se ha descargado un nuevo archivo.";
        try{
            boolean actualizado = descargar();
            if (actualizado) {
                respuesta ="Se ha descargado un nuevo archivo.";
                File archivo = new File("src\\datos.csv");
                FileReader lee = new FileReader(archivo);
                BufferedReader br = new BufferedReader (lee);

                Agenda entrada = crear_entrada(br);

                while (entrada != null){
                    insertAgenda(entrada);
                    entrada = crear_entrada(br);
                }
                lee.close();
            }
        }catch(Exception e){e.getStackTrace();}
        return respuesta;
        }
}
