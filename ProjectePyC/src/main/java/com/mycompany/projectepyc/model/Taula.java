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
    
    
    private Joc partidaActual;

    /**
     * Crea una nova instància de Mesa.
     * 
     * @param numero   el número identificador de la taula.
     */

    public Taula(int numero) {
        this.numero = numero;
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
