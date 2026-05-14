/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

/**
 * Classe de Transferència d'Objectes (TO) que representa un resum de les dades d'un club.
 * S'utilitza per emmagatzemar i transportar el nom del club i la quantitat de participants que hi pertanyen.
 *
 * @author Mario
 */
public class ResumenTO {
   
    /**
    * Nom del club.
    */
    private String nombreClub;
    /**
    * Quantitat de participants que pertanyen al club.
    */
    private int cantidadParticipantes;

    /**
     * Crea una nova instància de ResumenTO amb el nom del club i el numero de participants especificats.
     *
     * @param nombreClub El nom del club.
     * @param cantidadParticipantes El nombre total de participants associats a aquest club.
     */
    public ResumenTO(String nombreClub, int cantidadParticipantes) {
        this.nombreClub = nombreClub;
        this.cantidadParticipantes = cantidadParticipantes;
    }

    /**
     * Obté el nom del club.
     *
     * @return El nom del club com a cadena de text (String).
     */
    public String getNombreClub() {
        return nombreClub;
    }

    /**
     * Obté la quantitat de participants que pertanyen al club.
     *
     * @return El nombre de participants com a valor enter (int).
     */
    public int getCantidadParticipantes() {
        return cantidadParticipantes;
    }
    
    
}
