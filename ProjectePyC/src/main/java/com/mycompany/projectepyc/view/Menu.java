/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.projectepyc.view;

import java.io.IOException;

import com.mycompany.projectepyc.controller.GestorAEPDA;
import com.mycompany.projectepyc.exception.AEPDAException;

/**
 * Gestiona el Menu y la vista con el usuario.
 *
 * @author PyC
 * @version 1.0
 */

public class Menu {

    /**
     * Objeto auxiliar con métodos para pedir datos al usuario.
     */

    private AskData ask;

    /**
     * Objeto que contiene la lógica de la aplicación.
     */

    private GestorAEPDA gestor;

    
  /**
     * Mètode d'arrencada de l'aplicació que gestiona el bucle principal.
     * 
     * @throws IOException si hi ha un problema crític d'entrada/sortida.
     */

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
                    case 1: // ALTA CLUB
                        altaClub();
                        break;

                    case 2: // ALTA PARTICIPANT
                        altaParticipant();
                        break;

                    case 3: // MODIFICAR PARTICIPANT
                        modificarParticipant();
                        break;

                    case 4: // ESBORRAR PARTICIPANT
                        esborrarParticipant();
                        break;

                    case 5: // LLISTAT CLUBS
                        llistatClubs();
                        break;

                    case 6: // MOSTRA INFORMACIO D'UN CLUB
                        informacioClub();
                        break;

                    case 7: //ALTA TAULA
                        altaTaula();
                        break;

                    case 8: //MOSTRAR MESAS
                        llistatTaules();    
                        break;
                    
                    case 9: //INICIAR SORTEIG 1A RONDA
                        iniciarSorteig();
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
        System.out.println("Tancant l'ull de Sauron...");
    }

    /**
     * Mostra les opcions disponibles del menú principal per pantalla.
     */


    private void mostrarMenu() {
        System.out.println("\n--- GESTIÓ AEPDA ---");
        System.out.println("1. Alta Club");
        System.out.println("2. Alta Participant");
        System.out.println("3. Modificar Nickname");
        System.out.println("4. Esborrar Participant");
        System.out.println("5. Llistat General");
        System.out.println("6. Mostrar Membres d'un Club");
        System.out.println("7. Registrar Nova Taula");
        System.out.println("8. Llistat Taules");
        System.out.println("9. Generar Sorteig 1a Ronda");
        System.out.println("0. Sortir");
    }

    /**
     * Demana les dades necessàries i gestiona l'alta d'un nou club.
     *
     * @throws IOException    si hi ha un error en la comunicació amb el teclat.
     * @throws AEPDAException si el club ja existeix segons la lògica del gestor.
     */

    private void altaClub() throws IOException, AEPDAException {
        System.out.println("\n--- ALTA DE NOU CLUB ---");

        String nom = ask.askString("Introdueix el nom del club: ");

        gestor.registrarClub(nom);
        System.out.println("Club '" + nom + "' registrat correctament.");
    }

    /**
     * Demana les dades per a inscriure un participant en un club existent.
     *
     * @throws IOException    si falla l'entrada de dades.
     * @throws AEPDAException si el club no existeix o el participant està duplicat.
     */

    private void altaParticipant() throws IOException, AEPDAException {
        System.out.println("\n--- INSCRIPCIÓ DE PARTICIPANT ---");
        String nomClub = ask.askString("Nom del club on es vol inscriure: ");
        String id = ask.askString("ID (DNI/NIE) del participant: ");
        String nick = ask.askString("Sobrenom (Nickname) a la competició: ");

        gestor.afegirParticipantClub(nomClub, id, nick);
        System.out.println("Participant '" + nick + "' afegit amb èxit al club " + nomClub + ".");
    }

    /**
     * Gestiona la modificació del sobrenom d'un participant.
     *
     * @throws IOException    si hi ha error de persistència.
     * @throws AEPDAException si no es troba el participant.
     */

    private void modificarParticipant() throws IOException, AEPDAException {
        System.out.println("\n--- MODIFICAR SOBRENOM ---");
        String id = ask.askString("Introdueix l'ID del participant a modificar: ");
        String nouNick = ask.askString("Introdueix el nou sobrenom: ");

        gestor.modificarParticipant(id, nouNick);
        System.out.println("El sobrenom s'ha actualitzat correctament.");
    }

    /**
     * Gestiona l'eliminació d'un participant del sistema.
     *
     * @throws IOException    si falla l'actualització del fitxer.
     * @throws AEPDAException si l'identificador no és vàlid.
     */

    private void esborrarParticipant() throws IOException, AEPDAException {
        System.out.println("\n--- ESBORRAR PARTICIPANT ---");
        String id = ask.askString("ID del participant que vols eliminar: ");

        gestor.esborrarParticipant(id);
        System.out.println("El participant amb ID " + id + " ha estat eliminat.");
    }

    /**
     * Mostra per pantalla el llistat formatat de tots els clubs.
     */

    private void llistatClubs() {

        String info = gestor.llistatClubs();
        System.out.println(info);
    }

    /**
     * Demana el nom d'un club i en mostra la informació detallada.
     *
     * @throws IOException    si hi ha un error d'entrada de dades.
     * @throws AEPDAException si el club no existeix.
     */

    private void informacioClub() throws IOException, AEPDAException {

        String nom = ask.askString("Introdueix el nom del club que vols consultar: ");

        String dades = gestor.infoClub(nom);

        System.out.println(dades);
    }
 /**
     * Demana les dades d'una taula i gestiona el seu registre al sistema.
     * 
     * <p>Utilitza AskData per assegurar que el número de taula sigui un enter 
     * positiu i que els camps de text no estiguin buits.</p>
     * 
     * @throws IOException si hi ha un error en la lectura de teclat.
     * @throws AEPDAException si la taula ja existeix al gestor.
     */

    private void altaTaula() throws IOException, AEPDAException {
        System.out.println("--- ALTA DE NOVA TAULA ---");
        
        int num = ask.askInt("Número de la taula: ", "El número ha de ser 1 o superior.", 1);
        String ambient = ask.askString("Ambient (Obert/Tancat/Bheta Décima): ");
        String escenari = ask.askString("Nom de l'escenari: ");
        
        gestor.addMesa(num, ambient, escenari);
        System.out.println("Taula registrada correctament.");
    }

 /**
     * Executa la lògica del sorteig i en mostra l'informe per pantalla.
     * 
     * @throws AEPDAException si el sorteig no es pot realitzar per manca de jugadors o bloqueig.
     */

    private void iniciarSorteig() throws AEPDAException {
        System.out.println("\n--- INICIANT SORTEIG DE LA 1a RONDA ---");
        
        String informe = gestor.generarSorteigRonda1();
        System.out.println(informe);
    }

    /**
     * Mostra per pantalla el llistat de totes les taules del sistema.
     * 
     * <p>Aquest mètode de la vista agafa la informació 
     * del gestor i la imprimeix directament a la consola.</p>
     */
    private void llistatTaules() {
        System.out.println("--- LLISTAT DE LES TAULES ---");
        String info = gestor.llistatTaules();
        System.out.println(info);
    }

}
