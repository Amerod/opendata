package opendata;
import java.io.*;
import modelo.*;
import controlador.ControladorAgenda;
/**
 *
 * @author andres
 */
public class Opendata {
    
   /* public static void mostrar_entrada(Agenda entrada){
        System.out.println("-- Datos de la entrada -- ");
        System.out.println("ID : "+entrada.getID());
        System.out.println("Nombre : "+entrada.getActe());
        System.out.println("Descripcion : "+entrada.getDescripcio());
        System.out.println("Lugar : "+entrada.getLloc());
        System.out.println("Fecha : "+entrada.getData_ini());
        System.out.println("Distrito : "+entrada.getDistricte());
        System.out.println("URL : "+entrada.getURL()); 
    };*/
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File archivo = null;
        FileReader lee = null;
        BufferedReader br = null;
        AgendaDAO dao = new AgendaDAO();
        ControladorAgenda controlador = new ControladorAgenda(dao);
        try{
            
        archivo = new File("src\\datos.csv");
        lee = new FileReader(archivo);
        br = new BufferedReader (lee);
        
        Agenda entrada = controlador.crear_entrada(br);
        
        while (entrada != null){
            System.out.println(entrada.getID());
            System.out.println(dao.insertAgenda(entrada));
            entrada = controlador.crear_entrada(br);
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
