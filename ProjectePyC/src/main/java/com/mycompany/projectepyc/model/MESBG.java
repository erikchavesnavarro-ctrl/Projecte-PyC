/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

/**
 *
 * @author willg
 */

   public class MESBG extends Joc {

    private int puntsTotals;
    private boolean generalEnemicMort;

    public MESBG(int punts, boolean mort) {
        super();
        this.puntsTotals = punts;
        this.generalEnemicMort = mort;
    }

    @Override
    public int calcularPuntsPartida() {
        return puntsTotals;
    }

    public boolean isGeneralEnemicMort() {
        return generalEnemicMort;
    }
}

