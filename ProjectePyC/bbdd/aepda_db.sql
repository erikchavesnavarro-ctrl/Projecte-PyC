CREATE DATABASE IF NOT EXISTS AEPDA;
USE AEPDA;

CREATE TABLE IF NOT EXISTS club (
    nom VARCHAR(50) NOT NULL,
    PRIMARY KEY (nom)
);

CREATE TABLE IF NOT EXISTS joc (
    id_joc INT NOT NULL,
    punts_torneig INT NOT NULL,
    PRIMARY KEY (id_joc)
);

CREATE TABLE IF NOT EXISTS participant (
    id INT NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    club VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_participant_club FOREIGN KEY (club) REFERENCES club(nom)
);

CREATE TABLE IF NOT EXISTS participant_sorteig (
    id_inscripció INT NOT NULL AUTO_INCREMENT,
    participant_id INT NOT NULL,
    PRIMARY KEY (id_inscripció),
    CONSTRAINT fk_sorteig_participant FOREIGN KEY (participant_id) REFERENCES participant(id)
);

CREATE TABLE IF NOT EXISTS taula (
    Numero INT NOT NULL,
    PRIMARY KEY (Numero)
);

CREATE TABLE IF NOT EXISTS mesbg (
    id_joc INT NOT NULL,
    puntsTotals INT NOT NULL,
    generaEnemicMort BOOLEAN NOT NULL,
    PRIMARY KEY (id_joc),
    CONSTRAINT fk_mesbg_joc FOREIGN KEY (id_joc) REFERENCES joc(id_joc)
);

CREATE TABLE IF NOT EXISTS killteam (
    id_joc INT NOT NULL,
    critOps INT NOT NULL,
    tacOps INT NOT NULL,
    killOps INT NOT NULL,
    primaryOps INT NOT NULL,
    PRIMARY KEY (id_joc),
    CONSTRAINT fk_kt_joc FOREIGN KEY (id_joc) REFERENCES joc(id_joc)
);

CREATE TABLE IF NOT EXISTS taulaMESBG (
    Numero INT NOT NULL,
    escenari VARCHAR(100) NOT NULL,
    PRIMARY KEY (Numero),
    CONSTRAINT fk_tmesbg_taula FOREIGN KEY (Numero) REFERENCES taula(Numero)
);

CREATE TABLE IF NOT EXISTS taulaKillTeam (
    Numero INT NOT NULL,
    ambient VARCHAR(15) NOT NULL,
    PRIMARY KEY (Numero),
    CONSTRAINT fk_tkt_taula FOREIGN KEY (Numero) REFERENCES taula(Numero)
);