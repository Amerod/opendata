/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.event.*;
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
        this.vistaAgenda.btnNueva.addActionListener(this);
        this.vistaAgenda.txtNueva.addActionListener(this);
        
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==vistaAgenda.btnActualizar){
            JOptionPane.showMessageDialog(null, "Actualizar datos");
            String rptaRegistro = modeloAgenda.insertarTodo();
            this.LlenarTabla(vistaAgenda.Tabla1);
            JOptionPane.showMessageDialog(null, rptaRegistro);
        }
        if (e.getSource()==vistaAgenda.btnNueva){
            if(!vistaAgenda.txtNueva.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Crear nueva tabla");
            String rptaRegistro = modeloAgenda.nuevaTabla(vistaAgenda.txtNueva.getText());
            this.LlenarTabla(vistaAgenda.Tabla1);
            JOptionPane.showMessageDialog(null, rptaRegistro);}
            else{JOptionPane.showMessageDialog(null, "Inserta un nombre.");}
        }
    }

}
    