/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.persistence;

import com.mycompany.projectepyc.exception.AEPDAException;
import com.mycompany.projectepyc.model.Club;
import com.mycompany.projectepyc.model.Participant;
import com.mycompany.projectepyc.model.Taula;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Gestiona la persistència en fitxers del projecte AEPDA.
 *
 * <p>
 * Utilitza un fitxer per als clubs i un altre per a tots els participants.</p>
 *
 * @author PyC
 * @version 2.0
 */
public class Persistencia {

    private File carpeta;
    private File ficheroClubs;
    private File ficheroParticipants;

    /**
     * Inicialitza rutes i fitxers. Crea la carpeta si no existeix.
     */
    public Persistencia() {
        String rutaCarpeta = "aepda_data";

        carpeta = new File(rutaCarpeta);
        if (!carpeta.exists()) {
            carpeta.mkdir(); // Crea la carpeta si no existeix [5, 6]
        }

        ficheroClubs = new File(rutaCarpeta + File.separator + "clubs.txt");
        ficheroParticipants = new File(rutaCarpeta + File.separator + "participants.txt");
    }

    /**
     * Llegeix tots els clubs i els seus membres dels fitxers.
     *
     * @return un Map amb els clubs carregats.
     * @throws IOException si hi ha un error de disc.
     * @throws AEPDAException si les dades són incoherents.
     */
    public Map<String, Club> carregarData() throws IOException, AEPDAException {
        Map<String, Club> clubs = new HashMap<>();

        // 1. Llegim els clubs
        if (ficheroClubs.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(ficheroClubs))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String nomClub = linea;
                    clubs.put(nomClub.toUpperCase(), new Club(nomClub));
                }
            }
        }

        // 2. Llegim els participants i els assignem al seu club [4]
        if (ficheroParticipants.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(ficheroParticipants))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] d = linea.split(",");
                    if (d.length == 3) {
                        String nomClub = d[0];
                        String id = d[1];
                        String nick = d[2];

                        if (clubs.containsKey(nomClub.toUpperCase())) {
                            clubs.get(nomClub.toUpperCase()).addParticipant(new Participant(id, nick));
                        }
                    }
                }
            }
        }
        return clubs;
    }

    /**
     * Guarda un nou club al fitxer (mode append).
     *
     * @param c el club a guardar.
     * @throws IOException si falla l'escriptura.
     */
    public void escriureClub(Club c) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroClubs, true))) {
            bw.write(c.getNom());
            bw.newLine();
        }
    }

    /**
     * Guarda un participant vinculat a un club (mode append).
     *
     * @param nomClub nom del club al qual pertany.
     * @param p el participant.
     * @throws IOException si falla l'escriptura.
     */
    public void escriureParticipant(String nomClub, Participant p) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroParticipants, true))) {
            String linea = nomClub + "," + p.getID() + "," + p.getNickname(); // Format CSV
            bw.write(linea);
            bw.newLine();
        }
    }

    /**
     * Actualitza el fitxer de participants amb les dades actuals dels clubs.
     *
     * @param clubs mapa de clubs que conté els participants.
     * @throws IOException si falla l'escriptura.
     */
    public void guardarTotsParticipants(Map<String, Club> clubs) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroParticipants))) {
            for (Club c : clubs.values()) {
                for (Participant p : c.getParticipants().values()) {
                    String linea = c.getNom() + "," + p.getID() + "," + p.getNickname();
                    bw.write(linea);
                    bw.newLine();
                }
            }
        }
    }

    public void escriureTaula(Taula m) throws IOException {
        // Utilitzem try-with-resources per tancar el stream automàticament
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("aepda_data/mesas.txt", true))) {
            String linea = m.getNumero() + ";" + m.getAmbient() + ";" + m.getEscenari();
            bw.write(linea);
            bw.newLine();
        }
    }

    public Map<Integer, Taula> llegirTaules() throws IOException {
        Map<Integer, Taula> taulesCarregades = new HashMap<>();
        File f = new File("aepda_data/mesas.txt");

        if (f.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] d = linea.split(";");
                    int num = Integer.parseInt(d[0]);
                    taulesCarregades.put(num, new Taula(num, d[1], d[2]));
                }
            }
        }
        return taulesCarregades; // Punt únic de sortida
    }

}
