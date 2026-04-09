/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.controller;

import com.mycompany.projectepyc.exception.AEPDAException;
import com.mycompany.projectepyc.model.Club;
import com.mycompany.projectepyc.model.Participant;
import com.mycompany.projectepyc.persistence.Persistencia;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author willg
 */
public class GestorAEPDA {
    
    private Map<String, Club> clubs;

    private Persistencia persistencia;

    public GestorAEPDA() throws IOException, AEPDAException {
        persistencia = new Persistencia();
        clubs = persistencia.carregarData();
    }

    public void registrarClub(String nom) throws AEPDAException, IOException {
        if (clubs.containsKey(nom.toUpperCase())) {
            throw new AEPDAException("Ja existeix un club amb el nom: " + nom);
        }
        Club nou = new Club(nom);
        clubs.put(nom.toUpperCase(), nou);
        persistencia.escriureClub(nou);
    }

    public void afegirParticipantClub(String nomClub, String id, String nick) throws AEPDAException, IOException {
        if (!clubs.containsKey(nomClub.toUpperCase())) {
            throw new AEPDAException("El club " + nomClub + " no existeix.");
        }

        // Verificació global: el participant no pot estar en cap altre club
        if (existeixParticpantClub(id)) {
            throw new AEPDAException("El participant amb ID " + id + " ja està registrat en un altre club.");
        }

        Club c = clubs.get(nomClub.toUpperCase());
        Participant p = new Participant(id, nick);
        c.addParticipant(p);
        persistencia.escriureParticipant(nomClub, p);
    }

    private boolean existeixParticpantClub(String id) {
        for (Club c : clubs.values()) {
            if (c.existsParticipant(id)) {
                return true;
            }
        }
        return false;
    }

    public String llistatClubs() {
        String resultat = "";

        if (clubs.isEmpty()) {
            resultat = "No hi ha clubs registrats.";
        } else {
            StringBuilder sb = new StringBuilder("*** LLISTAT DE CLUBS ***\n");
            for (Club c : clubs.values()) {
                sb.append("- ").append(c.getNom())
                        .append(" (").append(c.getParticipants().size()).append(" membres)\n");
            }
            resultat = sb.toString();
        }

        return resultat;
    }

    public void modificarParticipant(String id, String nouNick) throws AEPDAException, IOException {
        boolean trobat = false;

        for (Club c : clubs.values()) {
            // Si encara no l'hem trobat, mirem si està en aquest club
            if (!trobat && c.existsParticipant(id)) {
                c.getParticipants().get(id).setNickname(nouNick);
                trobat = true;
            }
        }

        if (trobat) {
            persistencia.saveAllParticipants(clubs);
        } else {
            throw new AEPDAException("No s'ha trobat el participant amb ID: " + id);
        }
    }
    
    public void esborrarParticipant(String id) throws AEPDAException, IOException {
        boolean eliminat = false;

        for (Club c : clubs.values()) {
            if (!eliminat && c.existsParticipant(id)) {
                c.getParticipants().remove(id);
                eliminat = true;
            }
        }

        if (eliminat) {
            persistencia.saveAllParticipants(clubs);
        } else {
            throw new AEPDAException("No es pot esborrar: ID " + id + " no trobat.");
        }
    }
    
    public String infoClub(String nom) throws AEPDAException {
    String info = "";
    String clau = nom.toUpperCase(); // Normalitzem la clau segons el model Galaxia [6]

    if (!clubs.containsKey(clau)) {
        throw new AEPDAException("No s'ha trobat cap club amb el nom: " + nom);
    }

    Club c = clubs.get(clau);
    StringBuilder sb = new StringBuilder();
    sb.append("--- INFORMACIÓ DEL CLUB ---\n");
    sb.append("Nom: ").append(c.getNom()).append("\n");
    
    if (c.getParticipants().isEmpty()) {
        sb.append("Aquest club encara no té membres inscrits.\n");
    } else {
        sb.append("*** MEMBRES DEL CLUB ***\n");
        // Recorrem els valors del mapa de participants [2]
        for (Participant p : c.getParticipants().values()) {
            sb.append("- ").append(p.getNickname())
              .append(" [ID: ").append(p.getID()).append("]\n");
        }
        sb.append("Total: ").append(c.getParticipants().size()).append(" membres.");
    }
    
    info = sb.toString();
    return info; 
}

}

