package depense;

import java.sql.Connection;
import java.sql.Date;
import application.Application;
import connection.BddObject;
import connection.Column;
import connection.annotation.ColumnName;
import connection.annotation.ForeignKey;
import connection.annotation.PrimaryKey;
import formulaire.Formulaire;
import parcelle.Engrai;

public class Depense extends BddObject {
    
    @ColumnName("id_depense")
    String idDepense;
    @ForeignKey
    Engrai engrai; // type d'engrai a achete
    Date date;
    @ColumnName("prix_unitaire")
    Double prixUnitaire; // Prix unitaire de sur l'achat de cette engrai
    Double quantite; // quantite en kilo pour les engrais

    public void setIdDepense(String idDepense) throws Exception {
        if (idDepense == null) throw new Exception("idDepense est null");
        if (idDepense.isEmpty()) throw new Exception("idDepense est vide");
        this.idDepense = idDepense;
    }

    public String getIdDepense() {
        return idDepense;
    }

    public void setEngrai(Engrai engrai) throws Exception {
        if (engrai == null) throw new Exception("Engrai est null");
        this.engrai = engrai;
    }

    public void setEngrai(String idEngrai) throws Exception {
        this.setEngrai(new Engrai(idEngrai));
    }

    public Engrai getEngrai() {
        return engrai;
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

    public void setPrixUnitaire(Double prixUnitaire) throws Exception {
        if (prixUnitaire < 0) throw new Exception("Prix Unitaire doit etre positif");
        this.prixUnitaire = prixUnitaire;
    }

    public void setPrixUnitaire(String prixUnitaire) throws Exception {
        if (prixUnitaire == null) throw new Exception("Prix Unitaire est null");
        if (prixUnitaire.isEmpty()) throw new Exception("Prix Unitaire est vide");
        this.setPrixUnitaire(Double.valueOf(prixUnitaire));
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }
    
    public void setQuantite(Double quantite) throws Exception {
        if (quantite < 0) throw new Exception("Prix Unitaire doit etre positif");
        this.quantite = quantite;
    }

    public void setQuantite(String quantite) throws Exception {
        if (quantite == null) throw new Exception("Quantite est null");
        if (quantite.isEmpty()) throw new Exception("Quantite est vide");
        this.setQuantite(Double.valueOf(quantite));
    }

    public Double getQuantite() {
        return quantite;
    }

    public Depense() throws Exception {
        super();
        this.setTable("Depense");
        this.setPrimaryKeyName("idDepense");
        this.setConnection("PostgreSQL");
        this.setPrefix("DEP");
        this.setCountPK(7);
        this.setFunctionPK("nextval('seq_id_depense')");
    }

    public static Formulaire createFormulaire(String error) throws Exception {
        Formulaire form = Formulaire.createFormulaire(new Depense());
        form.getListeChamp()[0].setVisible(false, "");
        form.getListeChamp()[2].setVisible(false, "");
        form.getListeChamp()[3].setLabel("Prix Unitaire");
        form.setError(error);
        form.setAction("/katsaka/insert");
        form.setTitle("Saisie de depense");
        try (Connection connection = BddObject.getPostgreSQL()) {
            form.getListeChamp()[1].changeToDrop(new Engrai().<Engrai>findAll(connection, null, Engrai.class), "getNom", "getIdEngrai");
        }
        return form;
    }

    public void insert(Connection connection, Column... args) throws Exception {
        boolean connect = false;
        try {
            if (connection == null) {connection = this.getConnection(); connect = true;}
            this.setIdDepense(this.buildPrimaryKey(connection));
            super.insert(connection);
            if (connect) connection.commit();
        } catch (Exception e) {
            if (connect) connection.rollback();
            throw e;
        } finally {
            if (connect) connection.close();
        }
    }

}
