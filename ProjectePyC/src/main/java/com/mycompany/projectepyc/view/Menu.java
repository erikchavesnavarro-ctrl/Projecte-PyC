/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.projectepyc.view;

import com.mycompany.projectepyc.controller.GestorAEPDA;
import com.mycompany.projectepyc.exception.AEPDAException;
import java.io.IOException;



/**
 *
 * @author willg
 */
public class Menu {

    private AskData ask;
    private GestorAEPDA gestor;

    public void start() throws IOException {
        
        try {
            gestor = new GestorAEPDA();
        } catch (AEPDAException ex) {
            System.out.println("Error que no hauria de produir-se: " + ex.getMessage());
        }

        
        ask = new AskData();

        boolean salir = false;

        while (!salir) {
            try {
                mostrarMenu();
                int opcio = ask.askInt("Tria una opció: ");

                
                switch (opcio) {
                    case 1: //ALTA CLUB
                        altaClub();
                        break;

                    case 2: //ALTA PARTICIPANT
                        altaParticipant();
                        break;

                    case 3: //MODIFICAR PARTICIPANT
                        modificarParticipant();
                        break;

                    case 4: //ESBORRAR PARTICIPANT
                        esborrarParticipant();
                        break;

                    case 5: //LLISTAT CLUBS
                        llistatClubs();
                        break;

                    case 6: //MOSTRA INFORMACIO D'UN CLUB
                        informacioClub();
                        break;

                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opció incorrecta.");
                }
            } catch (AEPDAException ex) {
               
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
        System.out.println("Fins la pròxima!");
    }

    private void mostrarMenu() {
        System.out.println("\n--- GESTIÓ AEPDA ---");
        System.out.println("1. Alta Club");
        System.out.println("2. Alta Participant");
        System.out.println("3. Modificar Nickname");
        System.out.println("4. Esborrar Participant");
        System.out.println("5. Llistat General");
        System.out.println("0. Sortir");
    }

    private void altaClub() throws IOException, AEPDAException {
        System.out.println("\n--- ALTA DE NOU CLUB ---");
       
        String nom = ask.askString("Introdueix el nom del club: ");

        
        gestor.registrarClub(nom);
        System.out.println("Club '" + nom + "' registrat correctament.");
    }

    private void altaParticipant() throws IOException, AEPDAException {
        System.out.println("\n--- INSCRIPCIÓ DE PARTICIPANT ---");
        String nomClub = ask.askString("Nom del club on es vol inscriure: ");
        String id = ask.askString("ID (DNI/NIE) del participant: ");
        String nick = ask.askString("Sobrenom (Nickname) a la competició: ");

       
        gestor.afegirParticipantClub(nomClub, id, nick);
        System.out.println("Participant '" + nick + "' afegit amb èxit al club " + nomClub + ".");
    }

    private void modificarParticipant() throws IOException, AEPDAException {
        System.out.println("\n--- MODIFICAR SOBRENOM ---");
        String id = ask.askString("Introdueix l'ID del participant a modificar: ");
        String nouNick = ask.askString("Introdueix el nou sobrenom: ");

        
        gestor.modificarParticipant(id, nouNick);
        System.out.println("El sobrenom s'ha actualitzat correctament.");
    }

    private void esborrarParticipant() throws IOException, AEPDAException {
        System.out.println("\n--- ESBORRAR PARTICIPANT ---");
        String id = ask.askString("ID del participant que vols eliminar: ");

        gestor.esborrarParticipant(id);
        System.out.println("El participant amb ID " + id + " ha estat eliminat.");
    }

    private void llistatClubs() {
        
        String info = gestor.llistatClubs();
        System.out.println(info);
    }
    
    private void informacioClub() throws IOException, AEPDAException {
    
    String nom = ask.askString("Introdueix el nom del club que vols consultar: ");
    
    
    String dades = gestor.infoClub(nom);
    
    
    System.out.println(dades);
}

}
