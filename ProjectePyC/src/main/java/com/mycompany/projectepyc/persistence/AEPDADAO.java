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
    
    public boolean existeClub(String nombre) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("select * from club where name = ?;");
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next();
        rs.close();
        desconectar();
        return existe;
    }
    
    public void registrarClub(Club c) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("insert into club (nom) values(?);");
        ps.setString(1, c.getNom());
        ps.executeUpdate();
        ps.close();
        desconectar();
    }
    
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
    
    public void registrarParticipant(Participant p, String nomClub) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("insert into participant (id, nickname, club) values(?,?,?)");
        ps.setInt(1, p.getID());
        ps.setString(2, p.getNickname());
        ps.setString(3, nomClub);
        ps.executeUpdate();
        ps.close();
        desconectar();
    }
    
}
