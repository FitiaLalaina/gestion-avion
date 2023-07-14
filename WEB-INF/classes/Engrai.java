package parcelle;

import java.sql.Connection;

import agregation.Liste;
import connection.BddObject;
import connection.annotation.ColumnName;
import connection.annotation.ForeignKey;
import parcelle.Parcelle;
import statistique.Addition;
import parcelle.Engrai;
import depense.Depense;
import application.Application;

public class Engrai extends BddObject {
    
    @ColumnName("id_engrai")
    String idEngrai;
    String nom;
    Depense[] depenses; // Liste des applications sur une parcelle
    Addition[] additions;

    public void setNom(String nom) throws IllegalArgumentException {
        if (nom == null) throw new IllegalArgumentException("Nom est null");
        if (nom.isEmpty()) throw new IllegalArgumentException("Nom est vide");
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setIdEngrai(String idEngrai) throws IllegalArgumentException {
        if (idEngrai == null) throw new IllegalArgumentException("idEngrai est null");
        if (idEngrai.isEmpty()) throw new IllegalArgumentException("idEngrai est vide");
        this.idEngrai = idEngrai;
    }

    public String getIdEngrai() {
        return idEngrai;
    }

    public void setDepenses(Depense[] depenses) throws Exception {
        if (depenses.length <= 0) throw new Exception("Pas encore de depense pour l'engrai " + this.getNom());
        this.depenses = depenses;
    }

    public Depense[] getDepenses() {
        return depenses;
    }

    public Engrai() throws Exception {
        super();
        this.setTable("engrai");
        this.setFunctionPK("nextval('seq_id_engrai')");
        this.setCountPK(7);
        this.setPrefix("EGR");
        this.setPrimaryKeyName("idEngrai");
        this.setConnection("PostgreSQL");
    }

    public Engrai(String idEngrai) throws Exception {
        this();
        this.setIdEngrai(idEngrai);
    }

    public boolean equals(Engrai engrai) {
        return engrai.getIdEngrai().equals(this.getIdEngrai());
    }

    public String toString() {
        return this.getNom();
    }

    public Depense[] getDepenses(Connection connection) throws Exception {
        Depense depense = new Depense();
        depense.setEngrai(this);
        return depense.<Depense>findAll(connection, null, Depense.class);
    }

    public double getPrixUnitaireMoyenne() throws Exception {
        return Liste.avg(this.getDepenses(), "getPrixUnitaire");
    }
    
    public double getQuantite() throws Exception {
        return Liste.sommer(this.getDepenses(), "getQuantite");
    }

    public Addition[] getAdditions() {
        return additions;
    }

    public void setAdditions(Addition[] additions) {
        this.additions = additions;
    }

    public Addition[] getAddition(Connection connection) throws Exception {
        Addition addition = new Addition();
        addition.setEngrai(this);
        return addition.<Addition>findAll(connection, null, Addition.class);
    }

    public Addition getAddition(Parcelle parcelle) throws Exception {
        double pourcentage = parcelle.getPourcentage(this);
        for (Addition addition : this.getAdditions()) {
            if (addition.getMin() <= pourcentage && pourcentage <= addition.getMax()) {
                return addition;
            }
        }
        return null;
    }

}
