package opendata;
import java.io.*;
import modelo.*;
import controlador.ControladorAgenda;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 *
 * @author andres
 */
public class Opendata {
    
        public static boolean descargar(){
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
        boolean actualizado = descargar();
        if (actualizado) {System.out.println("Se ha descargado un nuevo archivo.");
            archivo = new File("src\\datos.csv");
            lee = new FileReader(archivo);
            br = new BufferedReader (lee);

            Agenda entrada = controlador.crear_entrada(br);

            while (entrada != null){
                System.out.println(entrada.getID());
                System.out.println(dao.insertAgenda(entrada));
                entrada = controlador.crear_entrada(br);
            }
        }
        else{
            System.out.println("No se ha descargado un nuevo archivo.");
        }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if (lee!=null){
                lee.close();};
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
}
