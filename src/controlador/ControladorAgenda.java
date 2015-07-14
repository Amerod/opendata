/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;
/**
 *
 * @author andres
 */
public class ControladorAgenda implements ActionListener{
    
    AgendaDAO modeloAgenda = new AgendaDAO();
    VistaOpendata vistaAgenda = new VistaOpendata();
    
    public ControladorAgenda(AgendaDAO modeloAgenda,VistaOpendata vistaAgenda){
        this.modeloAgenda = modeloAgenda;
        this.vistaAgenda = vistaAgenda;
        this.vistaAgenda.btnDescargar.addActionListener(this);
        this.vistaAgenda.btnInsertar.addActionListener(this);
        this.vistaAgenda.btnConectar.addActionListener(this);
        this.vistaAgenda.txtFecha.setText(this.modeloAgenda.consigueFecha(this.vistaAgenda.txtFecha));
    }
    
    public void LlenarTabla(JTable Tabla){
        DefaultTableModel modeloT = new DefaultTableModel();
        Tabla.setModel(modeloT);
        modeloT.addColumn("Tablas");
        String[] columna = new String[1];
        int numRegistros = modeloAgenda.listPersona().size();
        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modeloAgenda.listPersona().get(i);
            modeloT.addRow(columna);
        }
    }
    public void iniciarAuto(AgendaDAO dao){
        Date horaDespertar = new Date(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        c.setTime(horaDespertar);
        if (c.get(Calendar.HOUR_OF_DAY)>=8){
        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR)+1);}
        c.set(Calendar.HOUR_OF_DAY, 8);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        horaDespertar = c.getTime();
        int tiempoRepeticion = 86400000;
        java.util.Timer temporizador = new java.util.Timer();
        this.vistaAgenda.txtProximaFecha.setText(String.valueOf(horaDespertar));
        temporizador.schedule(new Temporizador(dao), horaDespertar, tiempoRepeticion);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== vistaAgenda.btnConectar){
            if (!(vistaAgenda.txtUserDB.getText().isEmpty())&&!(vistaAgenda.txtNombreBase.getText().isEmpty())){
                try{
                        File datosDB = new File("DatosDB.txt");
                        BufferedWriter bw;
                        bw = new BufferedWriter(new FileWriter(datosDB));
                        PrintWriter escribir = new PrintWriter(bw);//para crear el objeto que escribe en el archivo
                        escribir.println(vistaAgenda.txtUserDB.getText()+","+vistaAgenda.txtPassDB.getText()+","+vistaAgenda.txtNombreBase.getText()+","+vistaAgenda.txtDireccion.getText());//para escribir en el archivo
                        escribir.flush();
                        escribir.close();
                        bw.close();
                        LlenarTabla(vistaAgenda.jTable1);
                }
                catch(Exception a){a.printStackTrace();}
                JOptionPane.showMessageDialog(null, "Se han guardado los datos.");
                vistaAgenda.btnDescargar.setEnabled(true);
                vistaAgenda.btnInsertar.setEnabled(true);
                           }else{JOptionPane.showMessageDialog(null, "Debes poner un usuario y una base de datos.");}
        }
        
        if(e.getSource()==vistaAgenda.btnInsertar){
            JOptionPane.showMessageDialog(null, "Insertando nuevos datos.");
            modeloAgenda.insertarTodo();
            JOptionPane.showMessageDialog(null, "Datos insertados.");
        }
        
        if (e.getSource()==vistaAgenda.btnDescargar){
            JOptionPane.showMessageDialog(null, "Descargando nuevos archivos.");
            boolean rpta = modeloAgenda.descargar();
            if(rpta){
                JOptionPane.showMessageDialog(null, "Descargado con exito.");
            }else{JOptionPane.showMessageDialog(null, "No se ha descargado.");}

            this.vistaAgenda.txtFecha.setText(this.modeloAgenda.consigueFecha(this.vistaAgenda.txtFecha));
        }
    }

}
    