/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projectepyc;

import Classes.Club;
import Classes.Participant;
import com.mycompany.pyc.AskData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Mario
 */
public class ProjectePyC {
    
    private static AskData ask;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<Club> clubs;

    public static void main(String[] args) throws IOException {
        ask = new AskData();
        clubs = new ArrayList<>();

        String opcio;

        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            System.out.println("Tria una opcio: ");
            opcio = br.readLine();

            switch (opcio) {
                case "1": //ALTA CLUB
                    altaClub();
                    break;

                case "2": //ALTA PARTICIPANT
                    altaParticipant();
                    break;

                case "3": //MODIFICAR PARTICIPANT

                    break;

                case "4": //ESBORRAR PARTICIPANT

                    break;

                case "5": //LLISTAT CLUBS
                    llistatClubs();
                    break;
                    
                case "6": //MOSTRA INFORMACIO D'UN CLUB
                    informacioClub();
                    break;

                case "7": //GUARDAR CANVIS

                    break;    
                    
                case "8": //SORTIR
                    System.out.println("Tancant l'ull de Sauron...");
                    System.out.println("Fins la proxima sesio de joc.");
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion incorrecta");

            }
        }
    }

    public static void mostrarMenu() {
        System.out.println("--- AEPDA ---");
        System.out.println("1. Alta club");
        System.out.println("2. Alta participant");
        System.out.println("3. Modificar participant");
        System.out.println("4. Esborrar participant");
        System.out.println("5. Llistat clubs");
        System.out.println("6. Mostrar informacio d'un club");
        System.out.println("7. Guardar Canvis");
        System.out.println("8. Sortir");
        System.out.println("------------------------------");
    }

    //METODOS UTILES
    public static Club retornarClub(String nombre) {
        int club = -1;
        for (int i = 0; i < clubs.size(); i++) {
            //Si encontramos una unidad que sí exista
            if (clubs.get(i).getNom().equalsIgnoreCase(nombre)) {
                club = i;
            }
        }
        Club existent = clubs.get(club);
        return existent;
    }

    public static boolean comprovacioClub(String nombre) {
        for (int i = 0; i < clubs.size(); i++) {
            //Si encontramos una unidad que sí exista
            if (clubs.get(i).getNom().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }
    
    public static Participant retornarParticipant(String nickname) {
        int participant = -1;
        for (Club C : clubs) {
            for (Participant p : C.getParticipants()) {
                if(p.getNickname().equalsIgnoreCase(nickname)) {
                    return p;
                }
            }
        }
        return null;
    }
    
    public static boolean comprovacioParticipant(String ID) {
        for (Club C : clubs) {                     
            for (Participant p : C.getParticipants()) {         
                if (p.getID().equalsIgnoreCase(ID)) {
                    return true;
                }
            }
        }
        return false;
    }
    

    //METODOS DEL MENU
    
    public static void altaClub() throws IOException {
        System.out.println("--- ALTA CLUB ---");
        String nom = "";
        nom = ask.askString("Diguem el nom del club: ");
        while (comprovacioClub(nom) == true) {
            System.out.println("Aquest club ja esta registrat");
            nom = ask.askString("Diguem el nom del club: ");
        }

        Club nou = new Club(nom);
        clubs.add(nou);
    }

    public static void altaParticipant() throws IOException {
        if (clubs.isEmpty()) {
            System.out.println("No hi ha cap club registrat");
        } else {
            System.out.println("--- ALTA PARTICIPANT ---");
            String C = ask.askString("Digue'm el nom del club (ha d'estar registrat): ");
            while (comprovacioClub(C) == false) {
                System.out.println("Aquesta club no esta registrat");
                C = ask.askString("Digue'm el nom del club (ha d'estar registrat): ");
            }
            Club altaParticipants = retornarClub(C);

            String ID = "";
            ID = ask.askString("Digue'm el ID del participant: ");
            while (comprovacioParticipant(ID) == true) {
                System.out.println("Aquest ID ja esta registrat");
                ID = ask.askString("Digue'm el ID del particpant: ").toUpperCase();
            }

            String nickname = "";
            nickname = ask.askString("Digue'm el nickname: ");
            while (comprovacioParticipant(nickname) == true) {
                System.out.println("Aquest nickname ja esta registrat");
                nickname = ask.askString("Digue'm el nickname: ").toUpperCase();
            }

            Participant nou = new Participant(ID,nickname);
            altaParticipants.asignarParticipant(nou);;
            System.out.println("El participant: " + nickname + " ha sigut registrat i forma part del club: " + altaParticipants.getNom());
        }

    }
    
    public static void llistatClubs() throws IOException {
        if (clubs.isEmpty()) {
            System.out.println("No hi ha cap club registrat");
        } else {
            System.out.println("--- LLISTAT DE CLUBS INSCRITS ---");
            for (int i =0; i < clubs.size(); i++) {
                Club C = clubs.get(i);
                System.out.println("Club: " + C.getNom());
            }
        }
    }
    
    public static void informacioClub() throws IOException {
        if (clubs.isEmpty()) {
            System.out.println("No hi ha cap club registrat");
        } else {
            System.out.println("--- INFORMACIO DELS CLUBS---");
            String C = ask.askString("Digue'm el nom del club (ha d'estar registrat): ");
            while (comprovacioClub(C) == false) {
                System.out.println("Aquesta club no esta registrat");
                C = ask.askString("Digue'm el nom del club (ha d'estar registrat): ");
            }
            Club clubSeleccionat = retornarClub(C);
            
            ArrayList<Participant> membres = clubSeleccionat.getParticipants();
            
            if(membres.isEmpty()) {
                System.out.println("No hi ha cap participant registrat al club");
            } else {
                System.out.println("Llistat de membres del club: " + clubSeleccionat.getNom());
                for(Participant p: membres) {
                    System.out.println("- Nickname: " + p.getNickname() + " | ID: " + p.getID());
                }
                System.out.println("----------------------------");
                System.out.println("Total membres del club: " + membres.size());
            }
            
        }
    }

}
