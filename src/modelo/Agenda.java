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
    String email_acte;
    String web_acte;
    String a_carrec;
    String lloc;
    String data_ini;
    String data_fi;
    String gratuit;
    String preu;
    String districte;
    String tipus_acte;
    String observacions;
    String cicle;
    String descripcio_cicle;
    String cicle_data_ini;
    String cicle_data_fi;
    String x;
    String y;
    String longitud;
    String latitud;
    String url;
    

   
    public Agenda(String[] datos) {
    this.ID = Integer.valueOf(datos[0]);
    this.acte = datos[1];
    this.descripcio = datos[2];
    this.email_acte = datos[3];
    this.web_acte = datos[4];
    this.a_carrec = datos[5];
    this.lloc = datos[6];
    this.data_ini = datos[7];
    this.data_fi = datos[8];
    this.gratuit = datos[9];
    this.preu = datos[10];
    this.districte = datos[14];
    this.tipus_acte = datos[15];
    this.observacions = datos[16];
    this.cicle = datos[17];
    this.descripcio_cicle = datos[18];
    if (datos[19].isEmpty()){
    this.cicle_data_ini = null;
    this.cicle_data_fi = null;
    }else{
    this.cicle_data_ini = datos[19];
    this.cicle_data_fi = datos[20];
    };
    this.x = datos[22];
    this.y = datos[23];
    this.longitud = datos[24];
    this.latitud = datos[25];
    this.url = datos[26];
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

    public String getEmail_acte() {
        return email_acte;
    }

    public void setEmail_acte(String email_acte) {
        this.email_acte = email_acte;
    }

    public String getWeb_acte() {
        return web_acte;
    }

    public void setWeb_acte(String web_acte) {
        this.web_acte = web_acte;
    }

    public String getA_carrec() {
        return a_carrec;
    }

    public void setA_carrec(String a_carrec) {
        this.a_carrec = a_carrec;
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

    public void setData_ini(String data_ini) {
        this.data_ini = data_ini;
    }

    public String getData_fi() {
        return data_fi;
    }

    public void setData_fi(String data_fi) {
        this.data_fi = data_fi;
    }

    public String getGratuit() {
        return gratuit;
    }

    public void setGratuit(String gratuit) {
        this.gratuit = gratuit;
    }

    public String getPreu() {
        return preu;
    }

    public void setPreu(String preu) {
        this.preu = preu;
    }

    public String getDistricte() {
        return districte;
    }

    public void setDistricte(String districte) {
        this.districte = districte;
    }

    public String getTipus_acte() {
        return tipus_acte;
    }

    public void setTipus_acte(String tipus_acte) {
        this.tipus_acte = tipus_acte;
    }

    public String getObservacions() {
        return observacions;
    }

    public void setObservacions(String observacions) {
        this.observacions = observacions;
    }

    public String getCicle() {
        return cicle;
    }

    public void setCicle(String cicle) {
        this.cicle = cicle;
    }

    public String getDescripcio_cicle() {
        return descripcio_cicle;
    }

    public void setDescripcio_cicle(String descripcio_cicle) {
        this.descripcio_cicle = descripcio_cicle;
    }

    public String getCicle_data_ini() {
        return cicle_data_ini;
    }

    public void setCicle_data_ini(String cicle_data_ini) {
        this.cicle_data_ini = cicle_data_ini;
    }

    public String getCicle_data_fi() {
        return cicle_data_fi;
    }

    public void setCicle_data_fi(String cicle_data_fi) {
        this.cicle_data_fi = cicle_data_fi;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
    
}
