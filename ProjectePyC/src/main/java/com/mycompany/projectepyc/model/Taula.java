/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

/**
 * Representa una taula de joc del torneig.
 * 
 * <p>
 * Cada taula té un identificador numèric, un ambient i un escenari
 * específic assignat.
 * </p>
 * 
 * @author PyC
 * @version 1.0
 */

public class Taula {

    private int numero;
    private String ambient;
    private String escenari;
    private Joc partidaActual;

    /**
     * Crea una nova instància de Mesa.
     * 
     * @param numero   el número identificador de la taula.
     * @param ambient  el tipus d'ambient (Obert, Tancat o Bheta Décima).
     * @param escenari el nom de l'escenari o mapa.
     */

    public Taula(int numero, String ambient, String escenari) {
        this.numero = numero;
        this.ambient = ambient;
        this.escenari = escenari;

    }

    /**
     * Retorna el número de la taula.
     * 
     * @return l'identificador enter.
     */

    public int getNumero() {
        return numero;
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
     * Retorna l'escenari muntat a la taula.
     * 
     * @return el nom de l'escenari.
     */

    public String getEscenari() {
        return escenari;
    }

    /**
     * Assigna una partida a la taula.
     * 
     * @param partida el joc (KillTeam o MESBG) a realitzar.
     */

    public void setPartidaActual(Joc partida) {
        this.partidaActual = partida;
    }

    /**
     * Retorna la partida que s'està disputant actualment en aquesta taula.
     * 
     * @return l'objecte {@link Joc} assignat a la taula.
     */
    public Joc getPartidaActual() {
        return partidaActual;
    }

}
