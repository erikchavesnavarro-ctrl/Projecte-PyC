/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.model;

/**
 *
 * @author Mario
 */
public class Participant {
   
    private String id;
    private String nickname;
    
    public Participant(String id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
    
    public String getID() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    @Override
    public String toString() {
        return "Participant: " + nickname + " (ID: " + id + ")";
    }
    
}