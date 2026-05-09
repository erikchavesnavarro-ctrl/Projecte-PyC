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
import com.mycompany.projectepyc.model.TaulaKillTeam;
import com.mycompany.projectepyc.model.TaulaMESBG;
import com.mycompany.projectepyc.persistence.AEPDADAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controlador que gestiona la lògica de l'aplicació AEPDA.
 *
 * <p>
 * Aquesta classe connecta la vista amb el model i la persistència, validant les
 * regles de negoci abans de realitzar canvis.
 * </p>
 *
 * @author PyC
 * @version 2.0
 */
public class GestorAEPDA {

    private AEPDADAO aepdaDAO;

    /**
     * Inicialitza el gestor carregant les dades existents.
     *
     * @throws IOException si falla la lectura dels fitxers.
     * @throws AEPDAException si hi ha incoherències en les dades.
     */
    public GestorAEPDA() throws IOException, AEPDAException {
        aepdaDAO = new AEPDADAO();
    }

    /**
     * Registra un nou club a l'associació.
     *
     * @param nom el nom del club.
     * @throws AEPDAException si el club ja existeix.
     * @throws IOException si falla l'escriptura en disc.
     */
    public void registrarClub(String nom) throws AEPDAException, IOException, SQLException {
        if (aepdaDAO.existeClub(nom)) {
            throw new AEPDAException("Ja existeix un club amb aquest nom");
        }
        Club nouClub = new Club(nom);
        aepdaDAO.registrarClub(nouClub);
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
    public void afegirParticipantClub(String nomClub, int id, String nick) throws AEPDAException, IOException, SQLException {
        if (!aepdaDAO.existeClub(nomClub)) {
            throw new AEPDAException("El club " + nomClub + " no existeix.");
        }

        // Verificació global: el participant no pot estar en cap altre club
        if (aepdaDAO.existeParticipant(id)) {
            throw new AEPDAException("El participant amb ID " + id + " ja està registrat en un altre club.");
        }

        Participant p = new Participant(id, nick);
        aepdaDAO.registrarParticipant(p, nomClub);
    }

    /**
     * Retorna un llistat textual de tots els clubs.
     *
     * @return String amb la informació resumida.
     */
    public String llistatClubs() throws SQLException {
        String resultat = "";

        List<Club> llistaClubs = aepdaDAO.agafarTotsClubs();

        if (llistaClubs.isEmpty()) {
            resultat = "No hi ha clubs registrats.";
        } else {
            resultat += "*** LLISTAT DE CLUBS ***\n";
            for (Club c : llistaClubs) {
                int participants = aepdaDAO.comptarParticipantsClub(c.getNom());

                resultat += "- " + c.getNom();
                resultat += " (" + participants + " membres)\n";
            }
        }

        return resultat;
    }

    /**
     * Cerca un participant i en modifica el sobrenom.
     *
     * @param id l'identificador del participant.
     * @param nouNick el nou sobrenom a assignar.
     * @throws AEPDAException si el participant no existeix.
     */
    public void modificarParticipant(int id, String nouNick) throws AEPDAException, SQLException {
        String modificacio = "";
        if (!aepdaDAO.existeParticipant(id)) {
            throw new AEPDAException("El participant amb ID " + id + " no existeix.");
        }
        aepdaDAO.modificarParticipant(id, nouNick);
    }

    /**
     * Elimina un participant seguint la regla de flux net i sortida única.
     */
    public void esborrarParticipant(int id) throws AEPDAException, IOException, SQLException {
        if (!aepdaDAO.existeParticipant(id)) {
            throw new AEPDAException("No hi ha cap participant amb aquest id");
        }

        aepdaDAO.borrarParticipant(id);
    }

    /**
     * Genera un text amb la informació detallada d'un club i els seus membres.
     *
     * @param nom el nom del club a consultar.
     * @return un String formatat amb les dades del club i la llista de
     * participants.
     * @throws AEPDAException si el club no existeix en el sistema.
     */
    public String infoClub(String nomClub) throws AEPDAException, SQLException {
        String info = "";

        List<Participant> participants = aepdaDAO.agafarParticipantsClub(nomClub);

        if (!aepdaDAO.existeClub(nomClub)) {
            throw new AEPDAException("No s'ha trobat cap club amb el nom: " + nomClub);
        }

        info += "--- INFORMACIÓ DEL CLUB ---\n";
        info += "Club: " + nomClub + "\n";

        if (participants.isEmpty()) {
            info += "Aquest club encara no té membres inscrits.\n";
        } else {
            info += "*** MEMBRES DEL CLUB ***\n";
            for (Participant p : aepdaDAO.agafarParticipantsClub(nomClub)) {
                info += "- " + p.getNickname();
                info += " [ID: " + p.getID() + "]\n";
            }
            info += "Total: " + participants.size() + " membres.";
        }
        return info;
    }

    /**
     * Registra una nova taula al sistema.
     *
     * @param num número de taula.
     * @param ambient tipus d'entorn.
     * @param escenari nom del mapa.
     * @throws AEPDAException si el número de taula ja està registrat.
     * @throws IOException si falla l'escriptura en el fitxer.
     */
    public void addMesaKillTeam(int num, String ambient) throws AEPDAException, IOException, SQLException {
        if (aepdaDAO.existeTaula(num)) {
            throw new AEPDAException("La taula " + num + " ja existeix.");
        }

        TaulaKillTeam novaMesa = new TaulaKillTeam(num, ambient);
        aepdaDAO.registrarTaulaKillTeam(novaMesa);
    }

    public void addMesaMESBG(int num, String escenari) throws AEPDAException, IOException, SQLException {
        if (aepdaDAO.existeTaula(num)) {
            throw new AEPDAException("La taula " + num + " ja existeix.");
        }

        TaulaMESBG novaMesa = new TaulaMESBG(num, escenari);
        aepdaDAO.registrarTaulaMESBG(novaMesa);
    }

    /**
     * Genera un llistat formatat de totes les taules.
     *
     * @return un String amb la informació de les taules.
     */
    public String llistatTaulesKillTeam() throws SQLException {
        String info = "";

        List<TaulaKillTeam> taulesKillTeam = aepdaDAO.agafarTaulesKillTeam();

        if (taulesKillTeam.isEmpty()) {
            info = "No hi ha taules registrades.";
        } else {
            for (TaulaKillTeam m : taulesKillTeam) {
                info += "Taula " + m.getNumero();
                info += " - Ambient: " + m.getAmbient() + "\n";
            }
        }
        return info;
    }

    public String llistatTaulesMESBG() throws SQLException {
        String info = "";

        List<TaulaMESBG> taulesMESBG = aepdaDAO.agafarTaulesMESBG();

        if (taulesMESBG.isEmpty()) {
            info = "No hi ha taules registrades.";
        } else {
            for (TaulaMESBG m : taulesMESBG) {
                info += "Taula " + m.getNumero();
                info += " - Escenari: " + m.getEscenari() + "\n";
            }
        }
        return info;
    }

    /**
     * Genera els aparellaments de la primera ronda i registra l'historial.
     *
     * <p>
     * En aquesta ronda s'inicia l'historial de cada jugador per garantir que no
     * repeteixin taula ni escenari en el futur.
     * </p>
     *
     * @return String amb l'informe detallat.
     * @throws AEPDAException si hi ha bloqueig per restriccions de club.
     */
    public String generarSorteigRonda1KillTeam() throws AEPDAException, SQLException {
        List<TaulaKillTeam> taulesKillTeam = aepdaDAO.agafarTaulesKillTeam();
        if (taulesKillTeam.isEmpty()) {
            throw new AEPDAException("No es pot realitzar el sorteig perquè no hi ha cap taula creada.");
        }
        String info = "";

        List<ParticipantSorteig> sorteig = aepdaDAO.agafarParticipantsSorteig();
        info += "--- SORTEIG 1a RONDA (ALEATORI) ---\n";

        int numTaula = 0;
        boolean possible = true;

        int mesasNecesarias = sorteig.size() / 2;
        if (taulesKillTeam.size() < mesasNecesarias) {
            throw new AEPDAException(
                    "Faltan mesas: necesitas " + mesasNecesarias + " y solo tienes " + taulesKillTeam.size());
        }

        while (sorteig.size() >= 2 && numTaula <= 30 && possible) {

            int index1 = (int) (Math.random() * sorteig.size());
            ParticipantSorteig p1 = sorteig.get(index1);
            sorteig.remove(index1);

            ParticipantSorteig p2 = triarRivalAleatori(p1, sorteig);

            TaulaKillTeam t = taulesKillTeam.get(numTaula);

            if (taulesKillTeam.isEmpty()) {
                p1.getP().registrarPartida(numTaula, t.getAmbient());
            }
            p2.getP().registrarPartida(numTaula, t.getAmbient());

            info += "Taula " + numTaula + " " + " [" + t.getAmbient() + "]: " + p1.getP().getNickname() + " vs "
                    + p2.getP().getNickname() + "\n";

            numTaula += 1;
        }

        if (numTaula == 1) {
            info = "No hi ha participants suficients.";
        }
        return info;
    }

    public String generarSorteigRonda1MESBG() throws AEPDAException, SQLException {
        List<TaulaMESBG> taulesMESBG = aepdaDAO.agafarTaulesMESBG();
        if (taulesMESBG.isEmpty()) {
            throw new AEPDAException("No es pot realitzar el sorteig perquè no hi ha cap taula creada.");
        }
        String info = "";

        List<ParticipantSorteig> sorteig = aepdaDAO.agafarParticipantsSorteig();
        info += "--- SORTEIG 1a RONDA (ALEATORI) ---\n";

        int numTaula = 0;
        boolean possible = true;

        int mesasNecesarias = sorteig.size() / 2;
        if (taulesMESBG.size() < mesasNecesarias) {
            throw new AEPDAException(
                    "Faltan mesas: necesitas " + mesasNecesarias + " y solo tienes " + taulesMESBG.size());
        }

        while (sorteig.size() >= 2 && numTaula <= 30 && possible) {

            int index1 = (int) (Math.random() * sorteig.size());
            ParticipantSorteig p1 = sorteig.get(index1);
            sorteig.remove(index1);

            ParticipantSorteig p2 = triarRivalAleatori(p1, sorteig);

            TaulaMESBG t = taulesMESBG.get(numTaula);
            p1.getP().registrarPartida(numTaula, t.getEscenari());
            p2.getP().registrarPartida(numTaula, t.getEscenari());

            info += "Taula " + numTaula + " " + " [" + t.getEscenari() + "]: " + p1.getP().getNickname() + " vs "
                    + p2.getP().getNickname() + "\n";

            numTaula += 1;
        }

        if (numTaula == 1) {
            info = "No hi ha participants suficients.";
        }
        return info;
    }

    /**
     * Selecciona un oponent aleatori que no pertanyi al mateix club que el
     * jugador donat.
     *
     * @param p1 el participant que busca oponent.
     * @param sorteig la llista de participants disponibles.
     * @return un objecte ParticipantSorteig que representa l'oponent vàlid
     * trobat.
     * @throws AEPDAException si tots els participants restants són del mateix
     * club que p1.
     */
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
            throw new AEPDAException(
                    "No s'ha trobat cap rival disponible, tots els que queden pertanyen al club " + p1.getNomClub());
        }
        ParticipantSorteig p2 = sorteig.get(indexRival);
        sorteig.remove(indexRival);
        return p2;
    }

    public ArrayList<String> getCopiaNombresClubs() throws SQLException {
        List<Club> clubsReals = aepdaDAO.agafarTotsClubs();
        ArrayList<String> nombresClubs = new ArrayList<>();
        for (Club c : clubsReals) {
            nombresClubs.add(c.getNom());
        }
        return nombresClubs;
    }

    public ArrayList<Participant> getCopiaParticipants(String nombreClub) throws AEPDAException, SQLException {
        if (!aepdaDAO.existeClub(nombreClub)) {
            throw new AEPDAException("El club " + nombreClub + " no existeix.");
        }
        ArrayList<Participant> copiaParticipants = new ArrayList<>(aepdaDAO.agafarParticipantsClub(nombreClub));
        return copiaParticipants;
    }

    public ArrayList<TaulaKillTeam> getCopiaTaulesKillTeam() throws AEPDAException, SQLException {
        List<TaulaKillTeam> taulesKillTeam = aepdaDAO.agafarTaulesKillTeam();

        if (taulesKillTeam.isEmpty()) {
            throw new AEPDAException("No hi ha cap taula registrada.");
        }
        ArrayList<TaulaKillTeam> copiaTaulesKillTeam = new ArrayList<>(aepdaDAO.agafarTaulesKillTeam());
        return copiaTaulesKillTeam;
    }

    public ArrayList<TaulaMESBG> getCopiaTaulesMESBG() throws AEPDAException, SQLException {
        List<TaulaMESBG> taulesMESBG = aepdaDAO.agafarTaulesMESBG();

        if (taulesMESBG.isEmpty()) {
            throw new AEPDAException("No hi ha cap taula registrada.");
        }
        ArrayList<TaulaMESBG> copiaTaulesMESBGs = new ArrayList<>(aepdaDAO.agafarTaulesMESBG());
        return copiaTaulesMESBGs;
    }

}
