package com.fedoraapps.www.version12;

/**
 * Created by maxi on 13/05/2016.
 */
public class encomienda {
//lacbus.firebaseio.com/AppEncomiendasAndroid/encomiendas.json

    private int CodBarra;
    private int destinoID;
    private  String emisor;
    private int cedEmisor;
    private int codEncomienda;
    private int origenID;
    private String receptor;
    private int cedReceptor;
    private int codViaje;

    public encomienda(int codBarra, int destinoID, String emisor, int cedEmisor, int codEncomienda, int origenID, String receptor,int cedReceptor, int codViaje){
        this.CodBarra = codBarra;
        this.destinoID = destinoID;
        this.emisor = emisor;
        this.cedEmisor = cedEmisor;
        this.codEncomienda = codEncomienda;
        this.origenID = origenID;
        this.receptor = receptor;
        this.cedReceptor = cedReceptor;
        this.codViaje = codViaje;
    }

    public String toString(){
        return this.codEncomienda + " "+receptor;
    }

    public int getCodBarra() {
        return CodBarra;
    }

    public void setCodBarra(int codBarra) {
        CodBarra = codBarra;
    }

    public int getDestinoID() {
        return destinoID;
    }

    public void setDestinoID(int destinoID) {
        this.destinoID = destinoID;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public int getCedEmisor() {
        return cedEmisor;
    }

    public void setCedEmisor(int cedEmisor) {
        this.cedEmisor = cedEmisor;
    }

    public int getCodEncomienda() {
        return codEncomienda;
    }

    public void setCodEncomienda(int codEncomienda) {
        this.codEncomienda = codEncomienda;
    }

    public int getOrigenID() {
        return origenID;
    }

    public void setOrigenID(int origenID) {
        this.origenID = origenID;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public int getCedReceptor() {
        return cedReceptor;
    }

    public void setCedReceptor(int cedReceptor) {
        this.cedReceptor = cedReceptor;
    }

    public int getCodViaje() {
        return codViaje;
    }

    public void setCodViaje(int codViaje) {
        this.codViaje = codViaje;
    }
}
