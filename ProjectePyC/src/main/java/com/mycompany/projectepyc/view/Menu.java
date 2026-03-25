/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.projectepyc.view;

import static com.mycompany.projectepyc.ProjectePyC.altaClub;
import static com.mycompany.projectepyc.ProjectePyC.altaParticipant;
import static com.mycompany.projectepyc.ProjectePyC.esborrarParticipant;
import static com.mycompany.projectepyc.ProjectePyC.guardar;
import static com.mycompany.projectepyc.ProjectePyC.informacioClub;
import static com.mycompany.projectepyc.ProjectePyC.llistatClubs;
import static com.mycompany.projectepyc.ProjectePyC.modificarParticipant;
import com.mycompany.projectepyc.controller.GestorAEPDA;
import java.io.IOException;

/**
 *
 * @author willg
 */
public class Menu {

    private AskData ask;
    private GestorAEPDA gestor;
    
    public void start() throws IOException{
        gestor = new GestorAEPDA();
        ask = new AskData();
        boolean salir = false;
        do {
            mostrarMenu();
            
            int opcio = ask.askInt("Tria una opcio: ");

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

                case 7: //GUARDAR CANVIS
                    guardar();
                    break;

                case 8: //SORTIR
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    
            }
        }while (!salir);
        System.out.println("Tancant l'ull de Sauron...");
        
        }
            
            
        private void mostrarMenu() {
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
        
               
}    
        



        
        
        
        
    
