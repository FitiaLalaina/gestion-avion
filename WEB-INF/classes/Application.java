package application;

import java.sql.Connection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import connection.BddObject;
import connection.Column;
import connection.annotation.ColumnName;
import connection.annotation.ForeignKey;
import parcelle.Engrai;
import parcelle.Parcelle;
import formulaire.Formulaire;

public class Application extends BddObject {

    @ColumnName("id_application")
    String idApplication;
    @ForeignKey
    Parcelle parcelle;
    @ForeignKey
    Engrai engrai;
    Double quantite;
    Date date;

    public Parcelle getParcelle() {
        return parcelle;
    }

    public void setParcelle(Parcelle parcelle) throws IllegalArgumentException {
        if (parcelle == null) throw new IllegalArgumentException("Parcelle est null");
        this.parcelle = parcelle;
    }

    public void setParcelle(String idParcelle, Connection connection) throws Exception {
        this.setParcelle(new Parcelle(idParcelle).<Parcelle>getById(connection, Parcelle.class));
    }
    
    public void setParcelle(String idParcelle) throws Exception {
        this.setParcelle(new Parcelle(idParcelle));
    }

    public Engrai getEngrai() {
        return engrai;
    }

    public void setEngrai(Engrai engrai) throws IllegalArgumentException {
        if (engrai == null) throw new IllegalArgumentException("Engrai est null");
        this.engrai = engrai;
    }
    
    public void setEngrai(String idEngrai, Connection connection) throws Exception {
        this.setEngrai(new Engrai(idEngrai).<Engrai>getById(connection, Engrai.class));
    }
    
    public void setEngrai(String idEngrai) throws Exception {
        this.setEngrai(new Engrai(idEngrai));
    }

    public void setQuantite(Double quantite) throws IllegalArgumentException {
        if (quantite < 0) throw new IllegalArgumentException("Quantite doit etre positif");
        this.quantite = quantite;
    }

    public void setQuantite(String quantite) throws IllegalArgumentException {
        if (quantite == null) throw new IllegalArgumentException("Quantite est null");
        if (quantite.isEmpty()) throw new IllegalArgumentException("Quantite est vide");
        this.setQuantite(Double.valueOf(quantite));
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String date) throws Exception {
        if (date == null) throw new Exception("Date est null");
        if (date.isEmpty()) throw new Exception("Date est vide");
        this.setDate(toDate(date));
    }

    public static Date toDate(String date) throws Exception {
        // Format date in client-side is YYYY-MM-ddThh:mm
        date = date.replace("T", ",");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm");
        java.util.Date date2 = formatter.parse(date);
        return new Date(date2.getTime());
    }

    public Date getDate() {
        return date;
    }

    public String getIdApplication() {
        return idApplication;
    }

    public void setIdApplication(String idApplication) {
        this.idApplication = idApplication;
    }

    public Application() throws Exception {
        super();
        this.setTable("application");
        this.setFunctionPK("nextval('seq_id_application')");
        this.setCountPK(7);
        this.setConnection("PostgreSQL");
        this.setPrimaryKeyName("idApplication");
        this.setPrefix("APP");
    }

    public Application(String idApplication) throws Exception {
        this();
        this.setIdApplication(idApplication);
    }

    public static Formulaire createFormulaire(String error) throws Exception {
        Formulaire form = Formulaire.createFormulaire(new Application());
        form.getListeChamp()[0].setVisible(false, "");
        form.getListeChamp()[3].setLabel("Quantite en (kg)");
        form.getListeChamp()[4].setVisible(false, "");
        form.setError(error);
        form.setAction("/katsaka/insert");
        form.setTitle("Saisie d'application");
        try (Connection connection = BddObject.getPostgreSQL()) {
            form.getListeChamp()[1].changeToDrop(new Parcelle().<Parcelle>findAll(connection, null, Parcelle.class), "getNom", "getIdParcelle");
            form.getListeChamp()[2].changeToDrop(new Engrai().<Engrai>findAll(connection, null, Engrai.class), "getNom", "getIdEngrai");
        }
        return form;
    }

    public void insert(Connection connection, Column... args) throws Exception {
        boolean connect = false;
        try {
            if (connection == null) {connection = this.getConnection(); connect = true;}
            this.setIdApplication(this.buildPrimaryKey(connection));
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