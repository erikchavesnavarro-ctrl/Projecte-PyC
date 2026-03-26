/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.persistence;

import com.mycompany.projectepyc.model.Club;
import com.mycompany.projectepyc.model.Participant;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mario
 */
public class Persistencia {

    private static final String carpetaP = "aepda_data";

    private static final String separador = File.separator;

    private static final String fitxer_clubs = carpetaP + separador + "clubs.txt";

    private static void crearCarpeta() {
        File carpeta = new File(carpetaP);

        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
    }

    public static boolean guardar(ArrayList<Club> clubs) throws IOException {
        crearCarpeta();
        BufferedWriter bwClubs = new BufferedWriter(new FileWriter(fitxer_clubs));
        for (Club c : clubs) {
            bwClubs.write(
                    c.getNom()
            );
            bwClubs.newLine();
        }
        bwClubs.close();
        for (Club c : clubs) {
            String nomFitxer = carpetaP + separador + c.getNom() + ".txt";
            BufferedWriter bw = new BufferedWriter(new FileWriter(nomFitxer));
            for (Participant p : c.getParticipants()) {
                bw.write(
                        c.getNom() + ";"
                        + p.getID() + ";"
                        + p.getNickname()
                );
                bw.newLine();
            }
            bw.close();
        }
        return true;
    }
    
    public static ArrayList<Club> carregar() throws IOException {
        crearCarpeta();
        ArrayList<Club> clubs = new ArrayList<>();
        File f = new File(fitxer_clubs);
        if (!f.exists()) {
            return clubs;
        }
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linia;
        while ((linia = br.readLine()) != null) {
            String[] d = linia.split(";");
            String nom = d[0];
            Club c = new Club(nom);
            String nomFitxer = carpetaP + separador + nom + ".txt";
            File fParticipants = new File(nomFitxer);
            if (fParticipants.exists()) {
                BufferedReader brP = new BufferedReader(new FileReader(fParticipants));
                String lp;
                while ((lp = brP.readLine()) != null) {
                    String[] dp = lp.split(";");
                    if (dp.length >= 3) {
                            Participant p = new Participant(dp[5], dp[6]);
                            c.asignarParticipant(p);
                        }
                }
            }
            clubs.add(c);
        }
        return clubs;
    }

}
