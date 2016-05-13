package com.fedoraapps.www.version12;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxi on 12/05/2016.
 */
public class terminal {

    private int id;
    private String nombre;
    private List<viaje> listaviaje = new ArrayList<viaje>();

    public terminal(int id, String nombre, List<viaje> listaDeViajes){
        this.id = id;
        this.nombre = nombre;
        this.listaviaje = listaDeViajes;
    }

    public String toString(){return id+" "+nombre;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<viaje> getListaviaje(){
        return this.listaviaje;
    }
    public void setViajeEnlistaTerminal(viaje v){
        this.listaviaje.add(v);
    }
}
