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
    
    /**
    * Escenari de la taula.
    */
    private String escenari;

    /**
     * Crea una nova instància de TaulaMESB amb el número de taula i l'escenari.
     *
     * @param numero El número d'identificació de la taula.
     * @param escenari  L'escenari de la taula.
     */
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
