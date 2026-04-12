/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

/**
 *
 * @author willg
 */

/**
 * Representa una partida genèrica del torneig AEPDA.
 * 
 * <p>Aquesta classe defineix el marc comú de puntuació per a tots els jocs 
 * de l'associació.</p>
 * 
 * @author [Els vostres noms]
 * @version 1.0
 */

public abstract class Joc {

    
    
    
       /** Punts obtinguts per a la classificació general (3, 1 o 0). */
    protected int puntsTorneig;

    /**
     * Inicialitza una nova partida amb puntuació a zero.
     */

    
    public Joc() {
        this.puntsTorneig = 0;
    }


    /**
     * Calcula el total de punts de partida segons les regles específiques.
     * @return el total de punts obtinguts (0-21).
     */

    public abstract int calcularPuntsPartida();

    public int getPuntsTorneig() {
        return puntsTorneig;
    }
}

