/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

import java.util.ArrayList;

/**
 *
 * @author Mario
 */
public class Club {
    
    private String nom;
    private ArrayList<Participant> participants;

    public Club(String nom) {
        this.nom = nom;
        this.participants = new ArrayList();
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void asignarParticipant(Participant p) {
        participants.add(p);
    }
    
}

