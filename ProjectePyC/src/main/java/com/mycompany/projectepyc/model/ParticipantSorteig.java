/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

/**
 *
 * @author Mario
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
