/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

import com.mycompany.projectepyc.exception.AEPDAException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mario
 */
public class Club {
    
    private String nom;
    private Map<String, Participant> participants;

    public Club(String nom) {
        this.nom = nom;
        this.participants = new HashMap<>();
    }
    
    public String getNom() {
        return nom;
    }

    public Map<String, Participant> getParticipants() {
        return participants;
    }
    
    public void addParticipant(Participant p) throws AEPDAException {
        if (existsParticipant(p.getID())) {
            throw new AEPDAException("El participant amb ID " + p.getID() + " ja forma part d'aquest club.");
        }
        participants.put(p.getID(), p);
    }
    
    public boolean existsParticipant(String id) {
        return participants.containsKey(id);
    }
    
}
