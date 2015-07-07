/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.awt.Color;
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
import java.util.GregorianCalendar;
import javax.swing.JLabel;
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
            PreparedStatement ps = acceDB.prepareStatement("show tables;");
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
            CallableStatement cs = accesoDB.prepareCall("{call sp_insertAgenda(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            System.out.println(entrada.getData_ini());
            cs.setString(1,String.valueOf(entrada.getID()));
            cs.setString(2,entrada.getActe());
            cs.setString(3,entrada.getDescripcio());
            cs.setString(4,entrada.getEmail_acte());
            cs.setString(5,entrada.getWeb_acte());
            cs.setString(6,entrada.getA_carrec());
            cs.setString(7,entrada.getLloc());
            cs.setString(8,entrada.getData_ini());
            cs.setString(9,entrada.getData_fi());
            cs.setString(10,entrada.getGratuit());
            cs.setString(11,entrada.getPreu());
            cs.setString(12,entrada.getDistricte());
            cs.setString(13,entrada.getTipus_acte());
            cs.setString(14,entrada.getObservacions());
            cs.setString(15,entrada.getCicle());
            cs.setString(16,entrada.getDescripcio_cicle());
            cs.setString(17,entrada.getCicle_data_ini());
            cs.setString(18,entrada.getCicle_data_fi());
            cs.setString(19,entrada.getX());
            cs.setString(20,entrada.getY());
            cs.setString(21,entrada.getLongitud());
            cs.setString(22,entrada.getLatitud());
            cs.setString(23,entrada.getUrl());
            int numAfectadas = cs.executeUpdate();
            
            if (numAfectadas > 0){
                rptaRegistro = "registro exitoso.";
            }
        }catch(Exception e){}
            return rptaRegistro;
        }
        public String consigueFecha(JLabel label){
            String fechaViejo = "No hay archivo anterior.";
            Calendar calendar;
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try{
            File archivo = new File("src\\datos.csv");
            long milliSeconds = archivo.lastModified();
            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milliSeconds);
            fechaViejo = formatter.format(calendar.getTime());
            }catch(Exception e){}
            Calendar c2 = new GregorianCalendar();
            String hola = formatter.format(c2.getTimeInMillis());
            if (!fechaViejo.equals(hola)){
                label.setForeground(Color.RED);
            }else{label.setForeground(Color.BLACK);}
            return fechaViejo;
        }
        public boolean descargar(){
            boolean respuesta = false;
            
            try{
                File archivo = new File("src\\datos.csv");
                URL url = new URL("http://terrassaopendata.blob.core.windows.net/opendata/2015_AGENDA_ACTIVITATS_CIUTAT_TERRASSA.csv");
                URLConnection urlCon = url.openConnection();
                //Pasar de milisegundos a fecha
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                //Primero con el archivo nuevo
                long milliSeconds= urlCon.getDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(milliSeconds);
                String fechaNuevo = formatter.format(calendar.getTime());
                //fin del archivo nuevo
                //Ahora con el archivo viejo
                milliSeconds = archivo.lastModified();
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(milliSeconds);
                String fechaViejo = formatter.format(calendar.getTime());
                //Fin del arhivo viejo
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
        
        public String nuevaTabla(){
        String rptaRegistro;
        try{
            Connection accesoDB = conexion.getConexion();
            Statement ps = accesoDB.createStatement();
            ps.executeUpdate("create table agenda("+
            "ID int(8) PRIMARY KEY,"+
            "acte varchar(200),"+
            "descripcio varchar(200)," +
            "email_acte varchar(100)," +
            "web_acte varchar(100)," +
            "a_carrec varchar(100)," +
            "lloc varchar(100)," +
            "data_ini DATETIME," +
            "data_fi DATETIME," +
            "gratuit boolean," +
            "preu varchar(100)," +
            "districte varchar(10)," +
            "tipus_acte varchar(100)," +
            "observacions varchar(200)," +
            "cicle varchar(100)," +
            "descripcio_cicle varchar(200)," +
            "cicle_data_ini DATETIME," +
            "cicle_data_fi DATETIME," +
            "x real," +
            "y real," +
            "longitud real," +
            "latitud real," +
            "url varchar(100)" +
            ");" +
            "drop procedure if exists sp_insertAgenda;" +
            "drop procedure if exists sp_insertID;" +
            "drop procedure if exists sp_pasarDatos;" +
            "delimiter xd " +
            "	create procedure sp_insertAgenda(in id int(8),in acte varchar(200),in descripcio varchar(200),in web_acte varchar(100),in a_carrec varchar(100),in lloc varchar(100),in data_ini varchar(20),in data_fi varchar(20),in gratuito boolean,in preu varchar(100),in districte varchar(50),in tipus_acte varchar(100),in observacions varchar(200),in cicle varchar(100),in descripcio_cicle varchar(200),in cicle_data_ini DATETIME,in cicle_data_fi DATETIME,in x real,in y real,in longitud real,in latitud real,in url varchar(100))" +
            "    begin" +
            "		insert into agenda values(id,acte,descripcio,web_acte,a_carrec,lloc,data_ini,data_fi,gratuito,preu,districte,tipus_acte,observacions,cicle,descripcio_cicle,cicle_data_ini,cicle_data_fi,x,y,longitud,latitud,url);" +
            "    end;" +
            "xd" +
            "delimiter ;");
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
                if (datos[1].equals("ACTE")){
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
        
        public void insertarTodo(){
        try{
                File archivo = new File("src\\datos.csv");
                FileReader lee = new FileReader(archivo);
                BufferedReader br = new BufferedReader (lee);

                Agenda entrada = crear_entrada(br);

                while (entrada != null){
                    System.out.println(insertAgenda(entrada));
                    entrada = crear_entrada(br);
                    
                }
                lee.close();
        }catch(Exception e){e.getStackTrace();}
        }
}
