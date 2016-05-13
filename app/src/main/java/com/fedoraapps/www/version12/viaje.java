package com.fedoraapps.www.version12;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxi on 13/05/2016.
 */
public class viaje {
    private  int destinoCod;
    private String fechaV;
    private int codV;
    private String arrivoh;
    private String salidah;
    private String nombre;
    private int nroCoche;
    private int origenCod;
    private List<terminal> listaTerminalLadoViaje = new ArrayList<terminal>();
    private List<encomienda> ListadeEncomiendas = new ArrayList<encomienda>();

    public viaje(int destinoCod,int origenCod, String nombre, int codV,String fechaV, String arrivoh, String salidah, int nroCoche,List<terminal> terminalesAsociadas, List<encomienda> listadeEncomiendas){
        this.destinoCod = destinoCod;
        this.origenCod = origenCod;
        this.nombre = nombre;
        this.codV = codV;
        this.fechaV = fechaV;
        this.arrivoh = arrivoh;
        this.salidah = salidah;
        this.nroCoche = nroCoche;
        this.listaTerminalLadoViaje= terminalesAsociadas;
        this.ListadeEncomiendas = listadeEncomiendas;
    }

    public int getDestinoCod() {
        return destinoCod;
    }

    public String toString(){
        return nombre;
    }

    public void setDestinoCod(int destinoCod) {
        this.destinoCod = destinoCod;
    }

    public String getFechaV() {
        return fechaV;
    }

    public void setFechaV(String fechaV) {
        this.fechaV = fechaV;
    }

    public int getCodV() {
        return codV;
    }

    public void setCodV(int codV) {
        this.codV = codV;
    }

    public String getArrivoh() {
        return arrivoh;
    }

    public void setArrivoh(String arrivoh) {
        this.arrivoh = arrivoh;
    }

    public String getSalidah() {
        return salidah;
    }

    public void setSalidah(String salidah) {
        this.salidah = salidah;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNroCoche() {
        return nroCoche;
    }

    public void setNroCoche(int nroCoche) {
        this.nroCoche = nroCoche;
    }

    public int getOrigenCod() {
        return origenCod;
    }

    public void setOrigenCod(int origenCod) {
        this.origenCod = origenCod;
    }

    public List<terminal> getListaTerminalLadoViaje() {
        return listaTerminalLadoViaje;
    }

    public void setListaTerminalLadoViaje(List<terminal> listaTerminalLadoViaje) {
        this.listaTerminalLadoViaje = listaTerminalLadoViaje;
    }
}
