/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


/**
 *
 * @author andres
 */
public class Agenda {
    int ID;
    String acte;
    String descripcio;
    String web_acte;
    String lloc;
    String data_ini;
    String data_fi;
    String districte;
    String URL;

    public Agenda(String[] datos) {
        this.ID = Integer.parseInt(datos[0]);
        this.acte = datos[1];
        this.descripcio = datos[2];
        this.web_acte = datos[4];
        this.lloc = datos[6];
        this.data_ini = datos[7];
        this.data_fi = datos[8];
        this.districte = datos[14];
        this.URL = datos[26];
    }

    public String getWeb_acte() {
        return web_acte;
    }

    public void setWeb_acte(String web_acte) {
        this.web_acte = web_acte;
    }
    
    public String getData_fi() {
        return data_fi;
    }

    public void setData_fi(String data_fi) {
        this.data_fi = data_fi;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getActe() {
        return acte;
    }

    public void setActe(String acte) {
        this.acte = acte;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getLloc() {
        return lloc;
    }

    public void setLloc(String lloc) {
        this.lloc = lloc;
    }

    public String getData_ini() {
        return data_ini;
    }

    public void setData_ini(String Data_ini) {
        this.data_ini = Data_ini;
    }

    public String getDistricte() {
        return districte;
    }

    public void setDistricte(String Districte) {
        this.districte = Districte;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
    
}
