/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

/**
 * Entidad auxiliar para gestionar los emparejamientos del sorteo.
 * Vincula a un participante con su club para validar las restricciones.
 * 
 * @author PyC
 * @version 1.0
 */

public class ParticipantSorteig {

    /** L'objecte participant que entrarà al sorteig. */
    private Participant p;
    /** El nom del club al qual pertany el participant per validar restriccions. */
    private String nomClub;

    /**
     * Crea una nova instància de ParticipantSorteig.
     * 
     * @param p       l'objecte {@link Participant} que participa.
     * @param nomClub el nom del club al qual està inscrit.
     */
    public ParticipantSorteig(Participant p, String nomClub) {
        this.p = p;
        this.nomClub = nomClub;
    }

    /**
     * Retorna l'objecte participant vinculat.
     * 
     * @return l'instància de {@link Participant}.
     */
    public Participant getP() {
        return p;
    }

    /**
     * Retorna el nom del club del participant.
     * 
     * @return el nom identificatiu del club.
     */
    public String getNomClub() {
        return nomClub;
    }

}
