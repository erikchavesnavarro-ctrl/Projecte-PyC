/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.persistence;

import com.mycompany.projectepyc.model.Club;
import com.mycompany.projectepyc.model.Participant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mario
 */
public class AEPDADAO {
    
    private Connection conexion;
    private PreparedStatement ps;
    
    private void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/aepda";
        String user = "root";
        String pass = "root";
        conexion = DriverManager.getConnection(url, user, pass);
    }
    
    private void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
    
    
    //Método para comprobar que existe un club
    public boolean existeClub(String nombre) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("select * from club where nom = ?;");
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next();
        rs.close();
        desconectar();
        return existe;
    }
    
    //Método para registrar un club
    public void registrarClub(Club c) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("insert into club (nom) values(?);");
        ps.setString(1, c.getNom());
        ps.executeUpdate();
        ps.close();
        desconectar();
    }
    
    //Método para comprobar que existe un participante
    public boolean existeParticipant(int id) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("select * from participant where id = ?;");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next();
        rs.close();
        desconectar();
        return existe;
    }
    
    //Método para registrar un participante
    public void registrarParticipant(Participant p, String nomClub) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("insert into participant (id, nickname, club) values(?,?,?);");
        ps.setInt(1, p.getID());
        ps.setString(2, p.getNickname());
        ps.setString(3, nomClub);
        ps.executeUpdate();
        ps.close();
        desconectar();
    }
    
    //Método que devuelve una lista de clubes
    public List<Club> agafarTotsClubs() throws SQLException {
        List<Club> clubs = new ArrayList<>();
        conectar();
        ps = conexion.prepareStatement("select * from club;");
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            String nom = rs.getString("nom");
            clubs.add(new Club(nom));
        }
        rs.close();
        desconectar();
        return clubs;
    }
    
    //Método para contar cuantos participantes hay en x club
    public int comptarParticipantsClub(String nomClub) throws SQLException {
        int participants = 0;
        conectar();
        ps = conexion.prepareStatement("select count(club) from participant where club = '" + nomClub + "';");
        //ps.setString(1, nomClub);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            participants = rs.getInt(1);
        }
        rs.close();
        desconectar();
        return participants;
    }

    //Método para cambiar el nick de un participante
    public void modificarParticipant(int id, String nouNick) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("update participant set nickname = '" + nouNick + "' where id = " + id + ";");
        ps.executeUpdate();
        ps.close();
        desconectar();
    }
    
    //Método para coger el nickname de un participante
    public String agafarNicnkname(int id) throws SQLException {
        String nick = "";
        conectar();
        ps = conexion.prepareStatement("select nickname from participant where id = " + id + ";");
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            nick += rs.getString(1);
        }
        rs.close();
        desconectar();
        return nick;
    }
    
    //Método para borrar un participante
     public void borrarParticipant(int id) throws SQLException {
         conectar();
         ps = conexion.prepareStatement("delete from participant where id = '" + id + "';");
         ps.executeUpdate();
         ps.close();
         desconectar();
     }
    
    //Método que devuelve los participantes de un club
     public List<Participant> agafarParticipantsClub(String nomClub) throws SQLException {
         List<Participant> participants = new ArrayList<>();
        conectar();
        ps = conexion.prepareStatement("select id, nickname from participant where club = '" + nomClub + "';");
        ResultSet rs = ps.executeQuery();
        //ps.setString(1, nomClub);
        while(rs.next()) {
            int id = rs.getInt("id");
            String nickname = rs.getString("nickname");
            participants.add(new Participant(id, nickname));
        }
        rs.close();
        desconectar();
        return participants;
     }
     
//     //Método que devuelve un club
//     public Club c agafarClub()
}
