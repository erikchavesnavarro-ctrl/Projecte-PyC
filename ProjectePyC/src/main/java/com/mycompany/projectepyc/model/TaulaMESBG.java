/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

/**
 *
 * @author Mario
 */
public class TaulaMESBG extends Taula {
    
    private String escenari;

    public TaulaMESBG(int numero, String escenari) {
        super(numero);
        this.escenari = escenari;
    }

    /**
     * Retorna l'escenari muntat a la taula.
     * 
     * @return el nom de l'escenari.
     */

    public String getEscenari() {
        return escenari;
    }

    @Override
    public Joc getPartidaActual() {
        return super.getPartidaActual(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void setPartidaActual(Joc partida) {
        super.setPartidaActual(partida); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public int getNumero() {
        return super.getNumero(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
