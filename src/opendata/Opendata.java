package opendata;
import java.io.*;
import modelo.*;
import controlador.*;
import vista.*;
/**
 *
 * @author andres
 */
public class Opendata {
    
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AgendaDAO dao = new AgendaDAO();
        VistaOpendata vistaAgenda = new VistaOpendata();
        ControladorAgenda controlador = new ControladorAgenda(dao,vistaAgenda);
        vistaAgenda.setVisible(true);
        vistaAgenda.setLocationRelativeTo(null);
        controlador.iniciarAuto(dao);
    }
    
}
