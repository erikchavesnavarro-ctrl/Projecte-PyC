/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.persistence;

import com.mycompany.projectepyc.model.Club;
import com.mycompany.projectepyc.model.Participant;
import com.mycompany.projectepyc.model.ParticipantSorteig;
import com.mycompany.projectepyc.model.Taula;
import com.mycompany.projectepyc.model.TaulaKillTeam;
import com.mycompany.projectepyc.model.TaulaMESBG;
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
        while (rs.next()) {
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
        ps = conexion.prepareStatement("select count(club) from participant where club = ?;");
        ps.setString(1, nomClub);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            participants = rs.getInt(1);
        }
        rs.close();
        desconectar();
        return participants;
    }

    //Método para cambiar el nick de un participante
    public void modificarParticipant(int id, String nouNick) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("update participant set nickname = ? where id = ?;");
        ps.setString(1, nouNick);
        ps.setInt(2, id);
        ps.executeUpdate();
        ps.close();
        desconectar();
    }

    //Método para coger el nickname de un participante
    public String agafarNicnkname(int id) throws SQLException {
        String nick = "";
        conectar();
        ps = conexion.prepareStatement("select nickname from participant where id = ?;");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            nick += rs.getString(1);
        }
        rs.close();
        desconectar();
        return nick;
    }

    //Método para borrar un participante
    public void borrarParticipant(int id) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("delete from participant where id = ?;");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        desconectar();
    }

    //Método que devuelve los participantes de un club
    public List<Participant> agafarParticipantsClub(String nomClub) throws SQLException {
        List<Participant> participants = new ArrayList<>();
        conectar();
        ps = conexion.prepareStatement("select id, nickname from participant where club = ?;");
        ps.setString(1, nomClub);
        ResultSet rs = ps.executeQuery();
        //ps.setString(1, nomClub);
        while (rs.next()) {
            int id = rs.getInt("id");
            String nickname = rs.getString("nickname");
            participants.add(new Participant(id, nickname));
        }
        rs.close();
        desconectar();
        return participants;
    }

    //Método para añadir mesa
    public void registrarTaulaKillTeam(TaulaKillTeam t) throws SQLException {
        conectar();
        /*Aquesta taula tenia l'atribut partida_actual_id, però no l'utilitzem al codi, així que l'he eliminat, ja que només
        generava problemes ja que era una foreign key que no aportava res*/
        ps = conexion.prepareStatement("insert into taula (numero) values(?)");
        ps.setInt(1, t.getNumero());
        ps.executeUpdate();

        ps = conexion.prepareStatement("insert into taulakillteam (numero, ambient) values(?,?);");
        ps.setInt(1, t.getNumero());
        ps.setString(2, t.getAmbient());
        ps.executeUpdate();
        ps.close();
        desconectar();
    }

    public void registrarTaulaMESBG(TaulaMESBG t) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("insert into taula (numero) values(?)");
        ps.setInt(1, t.getNumero());
        ps.executeUpdate();

        ps = conexion.prepareStatement("insert into taulamesbg (numero, escenari) values(?,?);");
        ps.setInt(1, t.getNumero());
        ps.setString(2, t.getEscenari());
        ps.executeUpdate();
        ps.close();
        desconectar();
    }

    //Método para comprobar que ya existe una mesa
    public boolean existeTaula(int numero) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("select * from taula where numero = ?;");
        ps.setInt(1, numero);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next();
        rs.close();
        desconectar();
        return existe;
    }

    //Método que devuelve una lista de clubes
    public List<TaulaKillTeam> agafarTaulesKillTeam() throws SQLException {
        List<TaulaKillTeam> taulesKillTeam = new ArrayList<>();
        conectar();
        ps = conexion.prepareStatement("select * from taulakillteam;");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int numero = rs.getInt("numero");
            String ambient = rs.getString("ambient");
            taulesKillTeam.add(new TaulaKillTeam(numero, ambient));
        }
        rs.close();
        desconectar();
        return taulesKillTeam;
    }

    public List<TaulaMESBG> agafarTaulesMESBG() throws SQLException {
        List<TaulaMESBG> taulesMESBG = new ArrayList<>();
        conectar();
        ps = conexion.prepareStatement("select * from taulamesbg;");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int numero = rs.getInt("numero");
            String escenari = rs.getString("escenari");
            taulesMESBG.add(new TaulaMESBG(numero, escenari));
        }
        rs.close();
        desconectar();
        return taulesMESBG;
    }
    
    //Métode que retorna la llista dels participants del sorteig
    public List<ParticipantSorteig> agafarParticipantsSorteig() throws SQLException {
        List<ParticipantSorteig> participantsSorteig = new ArrayList<>();
        conectar();
        /*Aqui he hagut de fer un join ja que la classe ParticipantSorteig té un participant, i a més el nom del club
        al que pertany aquest, de manera que fer un join m'ha semblat la millor forma d'aconseguir els atributs que necessitava.
        Pot ser hi ha una solució més simple, pero fer un join és al que estic més acostumat*/
        ps = conexion.prepareStatement("select p.id, p.nickname, c.nom as nom_club from participant as p join club as c on p.club = c.nom;");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nickname = rs.getString("nickname");
            String club = rs.getString("nom_club");
            Participant p = new Participant(id, nickname);
            participantsSorteig.add(new ParticipantSorteig(p, club));
        }
        rs.close();
        desconectar();
        return participantsSorteig;
    }
    
    

}
