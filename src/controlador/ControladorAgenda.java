/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.io.BufferedReader;
import modelo.*;
/**
 *
 * @author andres
 */
public class ControladorAgenda {
    
    AgendaDAO modeloAgenda = new AgendaDAO();
    
    public ControladorAgenda(AgendaDAO modeloAgenda){
        this.modeloAgenda = modeloAgenda;
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
}
    