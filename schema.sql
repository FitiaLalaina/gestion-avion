CREATE SEQUENCE seq_id_engrai
    INCREMENT 1
    MINVALUE 1 
    MAXVALUE 1000
    CYCLE;

-- Table pour le stockage des engrais
CREATE TABLE engrai (
    id_engrai VARCHAR(50) PRIMARY KEY,
    nom VARCHAR(50) NOT NULL
);

CREATE SEQUENCE seq_id_parcelle
    INCREMENT 1
    MINVALUE 1 
    MAXVALUE 1000
    CYCLE;

-- Table pour le stockage des parcelles
CREATE TABLE parcelle (
    id_parcelle VARCHAR(50) PRIMARY KEY,
    nom VARCHAR(50) NOT NULL
);

CREATE SEQUENCE seq_id_application
    INCREMENT 1
    MINVALUE 1 
    MAXVALUE 1000
    CYCLE;

-- Table pour les operations sur les engrais de chaque parcelle
CREATE TABLE application (
    id_application VARCHAR(50) PRIMARY KEY,
    id_engrai VARCHAR(50) REFERENCES engrai(id_engrai),
    id_parcelle VARCHAR(50) REFERENCES parcelle(id_parcelle),
    quantite DOUBLE PRECISION NOT NULL DEFAULT 0,
    date VARCHAR(50) NOT NULL DEFAULT NOW()
);


CREATE SEQUENCE seq_id_depense
    INCREMENT 1
    MINVALUE 1 
    MAXVALUE 1000
    CYCLE;

-- Table pour les operations sur les depenses d'engrai de chaque parcelle
CREATE TABLE depense (
    id_depense VARCHAR(50) PRIMARY KEY,
    id_engrai VARCHAR(50) REFERENCES engrai(id_engrai),
    quantite VARCHAR(50) NOT NULL DEFAULT 0,
    prix_unitaire VARCHAR(50) NOT NULL DEFAULT 0,
    date VARCHAR(50) NOT NULL DEFAULT NOW()
);

-- Table pour les operations sur les depenses d'engrai de chaque parcelle
CREATE TABLE depense (
    id_depense INT PRIMARY KEY AUTO_INCREMENT,
    id_engrai VARCHAR(50),
    quantite DOUBLE PRECISION NOT NULL DEFAULT 0,
    prix_unitaire DOUBLE PRECISION NOT NULL DEFAULT 0,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE SEQUENCE seq_id_recolte
    INCREMENT 1
    MINVALUE 1 
    MAXVALUE 1000
    CYCLE;

-- Table pour les operations sur les depenses d'engrai de chaque parcelle
CREATE TABLE recolte (
    id_recolte VARCHAR(50) PRIMARY KEY,
    id_parcelle VARCHAR(50) REFERENCES parcelle(id_parcelle),
    nombre INTEGER NOT NULL DEFAULT 0,
    quantite DOUBLE PRECISION NOT NULL DEFAULT 0,
    date DATE NOT NULL DEFAULT NOW()
);

CREATE SEQUENCE seq_id_addition
    INCREMENT 1
    MINVALUE 1 
    MAXVALUE 1000
    CYCLE;


CREATE TABLE addition (
    id_addition VARCHAR(50) PRIMARY KEY,
    id_engrai VARCHAR(50) REFERENCES engrai(id_engrai),
    min DOUBLE PRECISION NOT NULL DEFAULT 0,
    max DOUBLE PRECISION NOT NULL DEFAULT 0,
    valeur DOUBLE PRECISION NOT NULL DEFAULT 0
);

SELECT id_engrai, SUM(quantite) as quantite
FROM application
GROUP BY id_engrai;

SELECT id_parcelle, SUM(quantite) as quantite
FROM application
GROUP BY id_parcelle;

SELECT id_parcelle , SUM(quantite) as quantite
FROM recolte
GROUP BY id_parcelle;

-- View pour afficher les quantites des engrais pour chaque parcelle
SELECT id_parcelle, id_engrai, SUM(quantite)
FROM application
GROUP BY id_parcelle, id_engrai;