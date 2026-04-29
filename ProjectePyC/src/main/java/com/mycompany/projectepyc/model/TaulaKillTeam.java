/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

/**
 *
 * @author Mario
 */
public class TaulaKillTeam extends Taula {
 
    private String ambient;

    public TaulaKillTeam(int numero, String ambient) {
        super(numero);
        this.ambient = ambient;
    }

    /**
     * Retorna l'ambient de la taula.
     * 
     * @return una cadena amb l'ambient (ex: "Obert").
     */

    public String getAmbient() {
        return ambient;
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
