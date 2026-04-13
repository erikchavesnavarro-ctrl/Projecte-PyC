/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;


/**
 * Representa una partida de Kill Team.
 * 
 * @author PyC
 * @version 1.0
 */

 public class KillTeam extends Joc {

    /** Punts de partida obtinguts mitjançant els CritOps. */
    private int critOps;
    /** Punts de partida obtinguts mitjançant els TacOps. */
    private int tacOps;
    /** Punts de partida obtinguts mitjançant els KillOps. */
    private int killOps;
    /** Punts de partida obtinguts en la missió principal. */
    private int primaryOps;
    
   /**
     * Crea una partida de Kill Team amb els seus desglossament de punts.
     */

    public KillTeam(int crit, int tac, int kill, int primary) {
        super(); // Crida al constructor de Joc [5]
        this.critOps = crit;
        this.tacOps = tac;
        this.killOps = kill;
        this.primaryOps = primary;
    }

    /**
     * Calcula la suma total dels punts obtinguts en una partida de Kill Team.
     * 
     * <p>Aquest mètode implementa la lògica específica de Kill Team sumant els quatre 
     * criteris de puntuació: CritOps, TacOps, KillOps i Primary Ops.</p>
     * 
     * @return el total de punts de partida acumulats.
     */
    @Override
    public int calcularPuntsPartida() {
        int total = critOps + tacOps + killOps + primaryOps;
        return total; // Únic punt de sortida
    }
}

