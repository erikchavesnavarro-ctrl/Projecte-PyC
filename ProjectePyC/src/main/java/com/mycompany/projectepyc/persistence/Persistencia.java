/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.persistence;

import com.mycompany.projectepyc.exception.AEPDAException;
import com.mycompany.projectepyc.model.Club;
import com.mycompany.projectepyc.model.Participant;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mario
 */
public class Persistencia {
    
    private File carpeta;    
    private File ficheroClubs;
    private File ficheroParticipants;
    
    public Persistencia() {
        String rutaCarpeta = "aepda_data";
        
        
        carpeta = new File(rutaCarpeta);
        if (!carpeta.exists()) {
            carpeta.mkdir(); // Crea la carpeta si no existeix [5, 6]
        }
        
        ficheroClubs = new File(rutaCarpeta + File.separator + "clubs.txt");
        ficheroParticipants = new File(rutaCarpeta + File.separator + "participants.txt");
    }

    public Map<String, Club> carregarData() throws IOException, AEPDAException {
        Map<String, Club> clubs = new HashMap<>();

        // 1. Llegim els clubs
        if (ficheroClubs.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(ficheroClubs))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String nomClub = linea.trim();
                    clubs.put(nomClub, new Club(nomClub));
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
                        
                        if (clubs.containsKey(nomClub)) {
                            clubs.get(nomClub).addParticipant(new Participant(id, nick));
                        }
                    }
                }
            }
        }
        return clubs;
    }

    public void escriureClub(Club c) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroClubs, true))) {
            bw.write(c.getNom());
            bw.newLine();
        }
    }
    
    public void escriureParticipant(String nomClub, Participant p) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroParticipants, true))) {
            String linea = nomClub + "," + p.getID() + "," + p.getNickname(); // Format CSV
            bw.write(linea);
            bw.newLine();
        }
    }
    
    public void saveAllParticipants(Map<String, Club> clubs) throws IOException {
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

    
}
