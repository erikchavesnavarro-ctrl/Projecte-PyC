/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.exception;

/**
 * Gestiona els errors de lògica de negoci de l'aplicació AEPDA.
 * 
 * @author PyC
 * @version 1.0
 */

    public class AEPDAException extends Exception{
      /**
     * Construeix una excepció amb un missatge específic.
     * @param message el text descriptiu de l'error.
     */

    public AEPDAException(String message) {
        super(message);
    }
    
}

