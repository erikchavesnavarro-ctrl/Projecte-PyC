/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

/**
 * Representa una partida de Middle Earth Strategy Battle Game.
 *  
 * @author PyC
 * @version 1.0
 */

public class MESBG extends Joc {

    /** Punts totals obtinguts en la partida. */
    private int puntsTotals;
    /**
     * Indica si el general de l'exèrcit enemic ha estat eliminat durant l'enfrontament.
     */
    private boolean generalEnemicMort;

    /**
     * Crea una nova instància de resultats per a una partida de MESBG.
     * 
     * @param punts els punts de partida totals aconseguits.
     * @param mort  cert si el general rival ha mort, fals en cas contrari.
     */
    public MESBG(int punts, boolean mort) {
        super();
        this.puntsTotals = punts;
        this.generalEnemicMort = mort;
    }

    /**
     * Retorna la suma total de punts obtinguts en la partida.
     * 
     * @return el valor dels punts totals (entre 0 i 21).
     */
    @Override
    public int calcularPuntsPartida() {
        return puntsTotals;
    }

    /**
     * Informa sobre si el general enemic ha estat eliminat.
     * 
     * @return cert si el general enemic ha mort.
     */
    public boolean isGeneralEnemicMort() {
        return generalEnemicMort;
    }
}
