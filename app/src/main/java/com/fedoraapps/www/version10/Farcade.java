package com.fedoraapps.www.version10;

import java.util.ArrayList;

/**
 * Created by maxi on 12/05/2016.
 */
public class Farcade {

  static   ArrayList<terminal> listaTerminal = new ArrayList<>();

    public Farcade(){};


    public void guardarEnLista(terminal t){
        this.listaTerminal.add(t);
    }


}
