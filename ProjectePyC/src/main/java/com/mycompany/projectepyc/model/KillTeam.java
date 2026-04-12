/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;


/**
 * Representa una partida de Kill Team.
 */

 public class KillTeam extends Joc {

    private int critOps;
    private int tacOps;
    private int killOps;
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

    @Override
    public int calcularPuntsPartida() {
        int total = critOps + tacOps + killOps + primaryOps;
        return total; // Únic punt de sortida
    }
}

