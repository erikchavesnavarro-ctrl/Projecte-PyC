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
 
    /**
    * Ambient de la taula.
    */
    private String ambient;

    /**
     * Crea una nova instància de TaulaKillTeam amb el número de taula i l'ambient.
     *
     * @param numero El número d'identificació de la taula.
     * @param ambient L'ambient o tipus d'entorn de la taula.
     */
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

    /**
     * Obté el número de la taula.
     * Aquest mètode sobreescriu el de la classe pare Taula.
     *
     * @return El número de la taula com a valor enter.
     */
    @Override
    public int getNumero() {
        return super.getNumero(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    
}
