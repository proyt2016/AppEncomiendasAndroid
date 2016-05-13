package com.fedoraapps.www.version12;

/**
 * Created by maxi on 12/05/2016.
 */
public class terminal {

    private int id;
    private String nombre;

    public terminal(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
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
}
