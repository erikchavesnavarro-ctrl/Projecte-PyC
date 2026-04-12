/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

/**
 * Entidad auxiliar para gestionar los emparejamientos del sorteo.
 * Vincula a un participante con su club para validar las restricciones.
 * 
 * @version 1.0
 */

public class ParticipantSorteig {

    private Participant p;
    private String nomClub;

    public ParticipantSorteig(Participant p, String nomClub) {
        this.p = p;
        this.nomClub = nomClub;
    }

    public Participant getP() {
        return p;
    }

    public String getNomClub() {
        return nomClub;
    }

}
