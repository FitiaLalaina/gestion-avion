package recolte;

import java.sql.Connection;
import java.sql.Date;

import connection.BddObject;
import connection.Column;
import connection.annotation.ColumnName;
import connection.annotation.ForeignKey;
import formulaire.Formulaire;
import parcelle.Parcelle;
import application.Application;

public class Recolte extends BddObject {
    
    @ColumnName("id_recolte")
    String idRecolte;
    @ForeignKey
    Parcelle parcelle;
    @ColumnName("nombre")
    Double nombreEpi;
    Double quantite;
    Date date;

    public Recolte() throws Exception {
        super();
        this.setTable("recolte");
        this.setFunctionPK("nextval('seq_id_recolte')");
        this.setCountPK(7);
        this.setPrefix("REC");
        this.setConnection("PostgreSQL");
        this.setPrimaryKeyName("idRecolte");
    }

    public String getIdRecolte() {
        return idRecolte;
    }

    public void setIdRecolte(String idRecolte) throws Exception {
        if (idRecolte == null) throw new Exception("idRecolte est null");
        if (idRecolte.isEmpty()) throw new Exception("idRecolte est vide");
        this.idRecolte = idRecolte;
    }

    public void setParcelle(Parcelle parcelle) throws Exception {
        if (parcelle == null) throw new Exception("Parcelle est null");
        this.parcelle = parcelle;
    }

    public void setParcelle(String idParcelle) throws Exception {
        this.setParcelle(new Parcelle(idParcelle));
    }

    public Parcelle getParcelle() {
        return parcelle;
    }

    public void setNombreEpi(Double nombreEpi) throws Exception {
        if (nombreEpi < 0) throw new Exception("Nombre d'epi doit etre positif");
        this.nombreEpi = nombreEpi;
    }

    public void setNombreEpi(String nombreEpi) throws Exception {
        if (nombreEpi == null) throw new Exception("Nombre d'epi est null");
        if (nombreEpi.isEmpty()) throw new Exception("Nombre d'epi est vide");
        this.setNombreEpi(Double.valueOf(nombreEpi));
    }

    public Double getNombreEpi() {
        return nombreEpi;
    }

    public void setQuantite(Double quantite) throws Exception {
        if (quantite < 0) throw new Exception("Quantite doit etre positif");
        this.quantite = quantite;
    }

    public void setQuantite(String quantite) throws Exception {
        this.setQuantite(Double.valueOf(quantite));
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setDate(Date date) throws Exception {
        if (date == null) throw new Exception("Date est null");
        this.date = date;
    }

    public void setDate(String date) throws Exception {
        if (date == null) throw new Exception("Date est null");
        if (date.isEmpty()) throw new  Exception("Date est vide");
        this.setDate(Application.toDate(date));
    }

    public Date getDate() {
        return date;
    }

    public void insert(Connection connection, Column... args) throws Exception {
        boolean connect = false;
        try {
            if (connection == null) {connection = this.getConnection(); connect = true;}
            this.setIdRecolte(this.buildPrimaryKey(connection));
            super.insert(connection);
            if (connect) connection.commit();
        } catch (Exception e) {
            if (connect) connection.rollback();
            throw e;
        } finally {
            if (connect) connection.close();
        }
    }

    public static Formulaire createFormulaire(String error) throws Exception {
        Formulaire form = Formulaire.createFormulaire(new Recolte());
        form.getListeChamp()[0].setVisible(false, "");
        form.getListeChamp()[2].setLabel("Nombre d'epi");
        form.getListeChamp()[3].setLabel("Quantite en (kg)");
        form.getListeChamp()[4].setVisible(false, "");
        form.setError(error);
        form.setAction("/katsaka/insert");
        form.setTitle("Saisie de recolte");
        try (Connection connection = BddObject.getPostgreSQL()) {
            form.getListeChamp()[1].changeToDrop(new Parcelle().<Parcelle>findAll(connection, null, Parcelle.class), "getNom", "getIdParcelle");
        }
        return form;
    }

}
