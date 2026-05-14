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
    
}