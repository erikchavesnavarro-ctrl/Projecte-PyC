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
    
    private String ID;
    private String nickname;

    public Participant(String ID, String nickname) {
        this.ID = ID;
        this.nickname = nickname;
    }

    public String getID() {
        return ID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
