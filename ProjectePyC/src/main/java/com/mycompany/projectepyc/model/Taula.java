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

}
