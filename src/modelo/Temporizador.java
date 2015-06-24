/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.TimerTask;
import modelo.*;

/**
 *
 * @author andres
 */
public class Temporizador extends TimerTask{
        AgendaDAO dao;
        
    public Temporizador(AgendaDAO dao){
        this.dao = dao;
    }
    
    @Override
    public void run() {
        dao.listPersona();
    }
    
}
