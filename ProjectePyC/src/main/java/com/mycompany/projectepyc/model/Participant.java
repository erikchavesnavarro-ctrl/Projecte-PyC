package com.mycompany.projectepyc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Entitat que representa un participant amb el seu historial de partides.
 * 
 * <p>A més de les dades bàsiques, manté un registre de les meses i ambients 
 * visitats per evitar repeticions segons la Regla d'Or del client.</p>
 * 
 * @author PyC
 * @version 1.0
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
    
    private List<Integer> historialTaules;
    private List<String> historialAmbients;

    public Participant(String id, String nickname) {
        this.id = id;
        this.nickname = nickname;
        this.historialTaules = new ArrayList<>();
        this.historialAmbients = new ArrayList<>();

    }
        /**
     * Verifica si el participant ja ha jugat en una taula concreta.
     * 
     * @param numTaula el número de la taula a comprovar.
     * @return true si ja hi ha jugat, false en cas contrari.
     */

    public boolean haJugatTaula(int numTaula) {
        boolean trobat = historialTaules.contains(numTaula);
        return trobat;
    }
      /**
     * Verifica si el participant ja ha jugat en un ambient/escenari concret.
     * 
     * @param ambient el nom de l'ambient (Obert, Tancat, etc.).
     * @return true si ja hi ha jugat, false en cas contrari.
     */

    public boolean haJugatAmbient(String ambient) {
        boolean trobat = historialAmbients.contains(ambient.toUpperCase());
        return trobat;
    }
    
    
    /**
     * Registra una nova partida a l'historial del participant.
     * 
     * @param numTaula número de la taula on ha jugat.
     * @param ambient ambient de la taula.
     */

    public void registrarPartida(int numTaula, String ambient) {
        historialTaules.add(numTaula);
        historialAmbients.add(ambient.toUpperCase());
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