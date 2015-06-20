package opendata;
import java.io.*;
import modelo.*;
import controlador.ControladorAgenda;
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
