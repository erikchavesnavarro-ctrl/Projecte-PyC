/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

/**
 *
 * @author willg
 */
public abstract class Joc {

    protected int puntsTorneig;
    
    public Joc() {
        this.puntsTorneig = 0;
    }


    public abstract int calcularPuntsPartida();

    public int getPuntsTorneig() {
        return puntsTorneig;
    }
}

