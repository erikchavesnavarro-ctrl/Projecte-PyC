/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

/**
 *
 * @author Mario
 */
public class ResumenTO {
    
    private String nombreClub;
    private int cantidadParticipantes;

    public ResumenTO(String nombreClub, int cantidadParticipantes) {
        this.nombreClub = nombreClub;
        this.cantidadParticipantes = cantidadParticipantes;
    }

    public String getNombreClub() {
        return nombreClub;
    }

    public int getCantidadParticipantes() {
        return cantidadParticipantes;
    }
    
    
}
