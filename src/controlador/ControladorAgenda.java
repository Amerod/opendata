/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.Color;
import java.awt.event.*;
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
        this.vistaAgenda.btnActualizar.addActionListener(this);
        this.vistaAgenda.txtFecha.setText(this.modeloAgenda.consigueFecha(this.vistaAgenda.txtFecha));
    }
    
    public void LlenarTabla(JTable Tabla){
        DefaultTableModel modeloT = new DefaultTableModel();
        Tabla.setModel(modeloT);
        modeloT.addColumn("Estados");
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
        if (e.getSource()==vistaAgenda.btnActualizar){
            JOptionPane.showMessageDialog(null, "Actualizar datos");
            String rptaRegistro = modeloAgenda.insertarTodo();
            JOptionPane.showMessageDialog(null, rptaRegistro);
            this.vistaAgenda.txtFecha.setText(this.modeloAgenda.consigueFecha(this.vistaAgenda.txtFecha));
        }
    }

}
    