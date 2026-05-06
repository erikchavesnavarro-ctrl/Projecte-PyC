/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.projectepyc;

import com.mycompany.projectepyc.exception.AEPDAException;
import com.mycompany.projectepyc.view.Menu;
import com.mycompany.projectepyc.view.gui.MainJFrame;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Classe principal de l'aplicació AEPDA.
 *
 * @author PyC
 * @version 1.0
 */
public class AEPDA {

    public static void main(String[] args) throws IOException, AEPDAException {
        MainJFrame gui = new MainJFrame();
        gui.setVisible(true);
    }
}
