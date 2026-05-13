/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectepyc.persistence;

import com.mycompany.projectepyc.model.Club;
import com.mycompany.projectepyc.model.Participant;
import com.mycompany.projectepyc.model.ParticipantSorteig;
import com.mycompany.projectepyc.model.ResumenTO;
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
 * Classe Data Access Object (DAO) encarregada de gestionar la persistència de dades.
 * 
 * @author Mario
 */
public class AEPDADAO {
    
    private Connection conexion;
    private PreparedStatement ps;

    /**
     * Estableix la connexió amb la base de dades local MySQL.
     * 
     * @throws SQLException Si es produeix un error en intentar connectar amb la base de dades.
     */
    private void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/aepda";
        String user = "root";
        String pass = "root";
        conexion = DriverManager.getConnection(url, user, pass);
    }

    /**
     * Tanca la connexió activa amb la base de dades, si n'hi ha cap.
     * 
     * @throws SQLException Si es produeix un error en intentar tancar la connexió.
     */
    private void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }

    /**
     * Comprova si existeix un club a la base de dades amb el nom especificat.
     * 
     * @param nombre El nom del club a buscar.
     * @return true si el club existeix, false si no existeix.
     * @throws SQLException si es produeix un error d'accés a la base de dades.
     */
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
    
    /**
     * Registra un nou club a la base de dades.
     * 
     * @param c L'objecte Club que conté les dades a insertar.
     * @throws SQLException si es produeix un error durant la inserció.
     */
    public void registrarClub(Club c) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("insert into club (nom) values(?);");
        ps.setString(1, c.getNom());
        ps.executeUpdate();
        ps.close();
        desconectar();
    }
    
    /**
     * Comprova si existeix un participant a la base de dades mitjançant la seva ID.
     * 
     * @param id L'identificador únic del participant.
     * @return true si el participant existeix, false si no existeix.
     * @throws SQLException si es produeix un error en la consulta.
     */
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

    /**
     * Registra un nou participant a un club específic.
     * 
     * @param p L'objecte Participant amb les dades a insertar.
     * @param nomClub El nom del club al qual es registra el participant.
     * @throws SQLException si es produeix un error durant l'insert.
     */
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

    /**
     * Obté una llista amb tots els clubs registrats a la base de dades.
     * 
     * @return Una List<Club> amb tots els clubs.
     * @throws SQLException si es produeix un error quan es consulta la taula de clubs.
     */
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

    /**
     * Compta el numero de participants que pertanyen a un club.
     * 
     * @param nomClub El nom del club del qual es volen comptar els participants.
     * @return El nombre total de participants del club.
     * @throws SQLException si es produeix un error en realitzar el recompte.
     */
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

    /**
     * Modifica el nickname d'un participant existent.
     * 
     * @param id L'identificador del participant a modificar.
     * @param nouNick El nou nickname que se li assignarà.
     * @throws SQLException si es produeix un error durant l'actualització del nickname.
     */
    public void modificarParticipant(int id, String nouNick) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("update participant set nickname = ? where id = ?;");
        ps.setString(1, nouNick);
        ps.setInt(2, id);
        ps.executeUpdate();
        ps.close();
        desconectar();
    }

    /**
     * Obté el nickname d'un participant mitjançant la seva ID.
     * 
     * @param id L'identificador del participant.
     * @return El nickname del participant.
     * @throws SQLException si es produeix un error en la consulta.
     */
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

    /**
     * Elimina un participant de la base de dades.
     * 
     * @param id L'identificador del participant que es vol esborrar.
     * @throws SQLException si es produeix un error durant l'eliminació.
     */
    public void borrarParticipant(int id) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("delete from participant where id = ?;");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        desconectar();
    }

    /**
     * Obté una llista amb tots els participants que pertanyen a un club en específic.
     * 
     * @param nomClub El nom del club.
     * @return Una List<Participant> amb els participants del club.
     * @throws SQLException si es produeix un error en consultar els participants.
     */
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

    /**
     * Registra una nova taula de KillTeam a la base de dades.
     * Afegeix el registre a la taula genèrica 'taula' i, a continuació, a 'taulakillteam'.
     * 
     * @param t L'objecte TaulaKillTeam a insertar.
     * @throws SQLException si es produeix un error durant la doble inserció.
     */
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

    /**
     * Registra una nova taula de MESBG a la base de dades.
     * Afegeix el registre a la taula genèrica 'taula' i, a continuació, a 'taulamesbg'.
     * 
     * @param t L'objecte TaulaMESBG a insertar.
     * @throws SQLException si es produeix un error durant la doble inserció.
     */
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

    /**
     * Comprova si ja existeix una taula amb el número indicat.
     * 
     * @param numero El número de la taula a comprovar.
     * @return true si la taula existeix, false si no existeix.
     * @throws SQLException si es produeix un error d'accés a la base de dades.
     */
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

    /**
     * Obté una llista de totes les taules registrades com a KillTeam.
     * 
     * @return Una List<TaulaKillTeam> amb les taules de KillTeam que existeixen.
     * @throws SQLException si es produeix un error en la consulta.
     */
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

    /**
     * Obté una llista de totes les taules registrades com a MESBG.
     * 
     * @return Una List<TaulaMESBG> amb les taules de MESBG que existeixen.
     * @throws SQLException si es produeix un error en la consulta.
     */
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
    
    /**
     * Obté la llista de participants per al sorteig.
     * Realitza un 'JOIN' entre les taules 'participant' i 'club' per obtenir tota la informació necessària de forma eficient.
     * 
     * @return Una List<ParticipantSorteig> amb els participants del sorteig i els seus clubs.
     * @throws SQLException si es produeix un error en executar la consulta.
     */
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
    
    /**
     * Obté un resum general amb la quantitat de participants que té cada club.
     * Utilitza un 'LEFT JOIN' per assegurar que es llistin tots els clubs, fins i tot si no tenen participants.
     * 
     * @return Una List<ResumenTO> amb el resum de dades de clubs i les seves quantitats de participants.
     * @throws SQLException si es produeix un error en executar la consulta.
     */
    public List<ResumenTO> getResumen() throws SQLException {
        List<ResumenTO> resumen = new ArrayList<>();
        conectar();
        ps = conexion.prepareStatement("select c.nom, count(p.club) as quantitat from club as c left join participant as p on c.nom = p.club group by c.nom");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String club = rs.getString("c.nom");
            int quantitat = rs.getInt("quantitat");
            resumen.add(new ResumenTO(club, quantitat));
        }
        rs.close();
        ps.close();
        desconectar();
        return resumen;
    }

}
