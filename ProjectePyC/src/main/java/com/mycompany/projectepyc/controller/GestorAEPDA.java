/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.controller;

import com.mycompany.projectepyc.exception.AEPDAException;
import com.mycompany.projectepyc.model.Club;
import com.mycompany.projectepyc.model.Participant;
import com.mycompany.projectepyc.model.ParticipantSorteig;
import com.mycompany.projectepyc.model.Taula;
import com.mycompany.projectepyc.persistence.Persistencia;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controlador que gestiona la lògica de l'aplicació AEPDA.
 *
 * <p>
 * Aquesta classe connecta la vista amb el model i la persistència, validant les
 * regles de negoci abans de realitzar canvis.</p>
 *
 * @author PyC
 * @version 2.0
 */
public class GestorAEPDA {

    /**
     * Diccionari de clubs registrats (Clau: Nom del club).
     */
    private Map<String, Club> clubs;

    private Map<Integer, Taula> taules; // Col·lecció avançada (Requisit Sprint 2)

    /**
     * Objecte per gestionar la persistència en fitxers.
     */
    private Persistencia persistencia;
    
    /**
     * Inicialitza el gestor carregant les dades existents.
     *
     * @throws IOException si falla la lectura dels fitxers.
     * @throws AEPDAException si hi ha incoherències en les dades.
     */
    public GestorAEPDA() throws IOException, AEPDAException {
        persistencia = new Persistencia();
        this.clubs = persistencia.carregarData();
        this.taules = persistencia.llegirTaules();
    }

    /**
     * Registra un nou club a l'associació.
     *
     * @param nom el nom del club.
     * @throws AEPDAException si el club ja existeix.
     * @throws IOException si falla l'escriptura en disc.
     */
    public void registrarClub(String nom) throws AEPDAException, IOException {
        if (clubs.containsKey(nom.toUpperCase())) {
            throw new AEPDAException("Ja existeix un club amb el nom: " + nom);
        }
        Club nou = new Club(nom);
        clubs.put(nom.toUpperCase(), nou);
        persistencia.escriureClub(nou);
    }

    /**
     * Afegeix un participant a un club específic.
     *
     * @param nomClub nom del club destí.
     * @param id identificador del participant.
     * @param nick sobrenom del participant.
     * @throws AEPDAException si el club no existeix o el participant està
     * duplicat.
     * @throws IOException si falla la persistència.
     */
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

    /**
     * Verifica si un participant existeix en qualsevol dels clubs.
     *
     * @param id l'identificador a buscar.
     * @return true si es troba, false en cas contrari.
     */
    private boolean existeixParticpantClub(String id) {
        for (Club c : clubs.values()) {
            if (c.existsParticipant(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna un llistat textual de tots els clubs.
     *
     * @return String amb la informació resumida.
     */
    public String llistatClubs() {
        String resultat = "";

        if (clubs.isEmpty()) {
            resultat = "No hi ha clubs registrats.";
        } else {
            resultat += "*** LLISTAT DE CLUBS ***\n";
            for (Club c : clubs.values()) {
                resultat +="- " + c.getNom();
                resultat +=" (" + c.getParticipants().size() + " membres)\n";
            }
        }

        return resultat;
    }

    /**
     * Modifica el nickname d'un participant seguint la regla d'un sol punt de
     * sortida.
     */
    public void modificarParticipant(String id, String nouNick) throws AEPDAException {
        Participant p = cercarParticipantGlobal(id);
        
        if (p == null) {
            throw new AEPDAException("No s'ha trobat cap participant amb l'ID: " + id);
        }
        
        p.setNickname(nouNick);
    }
    
    private Participant cercarParticipantGlobal(String id) throws AEPDAException {
        String nomClubTrobat = "";
        boolean trobat = false;
        
        for(Club c : clubs.values()) {
            if(trobat == false && c.existsParticipant(id)){
                nomClubTrobat = c.getNom().toUpperCase();
                trobat = true;
            }
        }
        if (trobat == false) {
            throw new AEPDAException("No s'ha trobat cap participant amb l'ID: " + id);
        }
        return clubs.get(nomClubTrobat).getParticipants().get(id);
    }


    /**
     * Elimina un participant seguint la regla de flux net i sortida única.
     */
    public void esborrarParticipant(String id) throws AEPDAException, IOException {
        boolean eliminat = false;

        for (Club c : clubs.values()) {
            if (!eliminat && c.existsParticipant(id)) {
                c.getParticipants().remove(id);
                eliminat = true;
            }
        }

        if (eliminat) {
            persistencia.guardarTotsParticipants(clubs);
        } else {
            throw new AEPDAException("No es pot esborrar: ID " + id + " no trobat.");
        }
    }

    /**
     * Genera un text amb la informació detallada d'un club i els seus membres.
     *
     * @param nom el nom del club a consultar.
     * @return un String formatat amb les dades del club i la llista de
     * participants.
     * @throws AEPDAException si el club no existeix en el sistema.
     */
    public String infoClub(String nom) throws AEPDAException {
        String info = "";
        String clau = nom.toUpperCase(); // Normalitzem la clau segons el model Galaxia [6]

        if (!clubs.containsKey(clau)) {
            throw new AEPDAException("No s'ha trobat cap club amb el nom: " + nom);
        }

        Club c = clubs.get(clau);
        info += "--- INFORMACIÓ DEL CLUB ---\n";
        info += "Nom: " + c.getNom() + "\n";

        if (c.getParticipants().isEmpty()) {
            info += "Aquest club encara no té membres inscrits.\n";
        } else {
            info += "*** MEMBRES DEL CLUB ***\n";
            // Recorrem els valors del mapa de participants [2]
            for (Participant p : c.getParticipants().values()) {
                info += "- " + p.getNickname();
                info += " [ID: " + p.getID() + "]\n";
            }
            info += "Total: " + c.getParticipants().size() + " membres.";
        }
        return info;
    }
    
    public void addMesa(int num, String ambient, String escenari) throws AEPDAException, IOException {
        // Validació Throw Early
        if (taules.containsKey(num)) {
            throw new AEPDAException("La taula " + num + " ja existeix.");
        }

        Taula novaMesa = new Taula(num, ambient, escenari);
        taules.put(num, novaMesa);
        persistencia.escriureTaula(novaMesa);
    }
    
    public String llistatTaules() {
        String info = "";
        if (taules.isEmpty()) {
            info = "No hi ha taules registrades.";
        } else {
            for (Taula m : taules.values()) {
                info += "Taula " + m.getNumero();
                info += ": " + m.getEscenari();
                info += " (" + m.getAmbient() + ")\n";
            }
        }
        return info;
    }
    
    public String generarSorteigRonda1() throws AEPDAException {
        String info = "";

        List<ParticipantSorteig> sorteig = prepararSorteig();
        info += "--- SORTEIG 1a RONDA (ALEATORI) ---\n";

        int numTaula = 1;
        boolean possible = true;

        while (sorteig.size() >= 2 && numTaula <= 30 && possible) {

            int index1 = (int) (Math.random() * sorteig.size());
            ParticipantSorteig p1 = sorteig.get(index1);
            sorteig.remove(index1);

            ParticipantSorteig p2 = triarRivalAleatori(p1, sorteig);

            Taula t = taules.get(numTaula);
            p1.getP().registrarPartida(numTaula, t.getAmbient());
            p2.getP().registrarPartida(numTaula, t.getAmbient());

            info += "Taula " + numTaula + " " + t.getEscenari() + " [" + t.getAmbient() + "]: " + p1.getP().getNickname() + " vs " + p2.getP().getNickname() + "\n";

            numTaula += 1;
        }

        if (numTaula == 1) {
            info = "No hi ha participants suficients.";
        }
        return info;
    }

    private ParticipantSorteig triarRivalAleatori(ParticipantSorteig p1, List<ParticipantSorteig> sorteig) throws AEPDAException {
        int intents = 0;
        int indexRival = -1;
        boolean trobat = false;

        while (intents < sorteig.size() && trobat == false) {
            int index2 = (int) (Math.random() * sorteig.size());
            if (!sorteig.get(index2).getNomClub().equalsIgnoreCase(p1.getNomClub())) {
                indexRival = index2;
                trobat = true;
            }
            intents++;
        }

        if (trobat == false) {
            throw new AEPDAException("No s'ha trobat cap rival disponible, tots els que queden pertanyen al club " + p1.getNomClub());
        }
        ParticipantSorteig p2 = sorteig.get(indexRival);
        sorteig.remove(indexRival);
        return p2;
    }

    private List<ParticipantSorteig> prepararSorteig() {
        List<ParticipantSorteig> llista = new ArrayList<>();
        for (Club c : clubs.values()) {
            for (Participant p : c.getParticipants().values()) {
                llista.add(new ParticipantSorteig(p, c.getNom()));
            }
        }
        return llista;
    }
}