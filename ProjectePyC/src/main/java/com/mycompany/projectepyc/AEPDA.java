/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.projectepyc;

import com.mycompany.projectepyc.view.Menu;
import java.io.IOException;

/**
 *
 * @author willg
 */
public class AEPDA {

    public static void main(String[] args) {
      
        Menu m = new Menu();
        try {
            m.start();
        } catch (IOException ex) {
            System.out.println("ERROR INESPERADO o que no debería darse: " + ex.getMessage());
        }
    }
    }
    

