/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

/**
* Entitat que representa un participant en els tornejos de l'AEPDA.
*
* <p>Aquesta classe emmagatzema la informació bàsica d'identificació
* de cada jugador inscrit al club.</p>
*
* @author PyC
* @version 2.0
*/
public class Participant {
    
    /**
       * Identificador únic del participant (ex: DNI o ID intern).
    */
    
    private String id;
    
    /**
    * Nom de guerra o sobrenom del participant.
    */
    
    private String nickname;
    
    /**
    * Crea una nova instància de Participant.
    *
    * @param id identificador únic del participant.
    * @param nickname nom públic que utilitzarà el jugador.
    */

    public Participant(String id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
    
    /**
    * Retorna l'ID del participant.
    *
    * @return el valor de l'ID.
    */
    
    public String getID() {
        return id;
    }
    
    /**
    * Retorna el sobrenom del participant.
    *
    * @return el valor del nickname.
    */

    public String getNickname() {
        return nickname;
    }
    
    /**
    * Actualitza el sobrenom del participant.
    *
    * @param nickname el nou sobrenom a assignar.
    */
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    /**
    * Retorna una representació textual del participant.
    *
    * @return String amb el nickname i l'ID.
    */

    @Override
    public String toString() {
        return "Participant: " + nickname + " (ID: " + id + ")";
    }
    
}