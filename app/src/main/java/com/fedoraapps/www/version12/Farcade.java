package com.fedoraapps.www.version12;

import java.util.ArrayList;

/**
 * Created by maxi on 12/05/2016.
 */
public class Farcade {

    static ArrayList<terminal> listaTerminal = new ArrayList<>();
    static ArrayList<viaje> listaViajes = new ArrayList<>();
    static ArrayList<encomienda> listaEncomiendas = new ArrayList<>();

    public Farcade(){};


    public void guardarEnListaterminal(terminal t){
        this.listaTerminal.add(t);
    }

    public void guardarListaViaje(viaje v){
       this.listaViajes.add(v);
    }

    public void guardarEnlistaEncomienda(encomienda e){
        this.listaEncomiendas.add(e);
    }

    static boolean getViajesPorCodigo(int codTerminal, int codViaje ){

        if(codTerminal == codViaje) {
            return true;}else{
            return  false;
        }


    }

    static boolean getEncomiendasPorCodigo(int codViaje, int  viajeAsociado){

        if(codViaje == viajeAsociado) {
            return true;}else{
            return  false;
        }


    }


}