/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

import com.mycompany.projectepyc.exception.AEPDAException;
import java.util.HashMap;
import java.util.Map;

/**
* Entitat que representa un club d'associats a l'AEPDA.
*
* <p>Cada club té un nom únic i gestiona el seu propi llistat de membres
* mitjançant un diccionari per optimitzar les cerques per identificador.</p>
*
* @author PyC
* @version 2.0
*/

public class Club {
    
    /**
    * Nom identificatiu del club.
    */

    private String nom;
    
    /**
    * Diccionari de participants on la clau és l'ID del participant.
    */
    
    private Map<String, Participant> participants;
    
    /**
    * Crea una nova instància de Club.
    *
    * @param nom el nom del club.
    */
    
    public Club(String nom) {
        this.nom = nom;
        this.participants = new HashMap<>();
    }
    
    /**
    * Retorna el nom del club.
    *
    * @return el nom.
    */

    public String getNom() {
        return nom;
    }
    
    /**
    * Retorna el mapa complet de participants.
    *
    * @return Map amb els membres del club.
    */
    
    public Map<String, Participant> getParticipants() {
        return participants;
    }
    
    /**
    * Afegeix un nou participant al club.
    *
    * @param p l'objecte Participant a inscriure.
    * @throws AEPDAException si el participant ja existeix en aquest club.
    */
    
    public void addParticipant(Participant p) throws AEPDAException {
        if (existsParticipant(p.getID())) {
            throw new AEPDAException("El participant amb ID " + p.getID() + " ja forma part d'aquest club.");
        }
        participants.put(p.getID(), p);
    }
    
    /**
    * Verifica si un participant ja està inscrit en el club.
    *
    * @param id l'identificador a buscar.
    * @return true si el participant hi és, false en cas contrari.
    */

    public boolean existsParticipant(String id) {
        return participants.containsKey(id);
    }
    
    /**
    * Retorna una representació textual del club i el seu nombre de membres.
    *
    * @return String informatiu.
    */
    @Override
    public String toString() {
        return "Club: " + nom + " (Membres: " + participants.size() + ")";
    }

}