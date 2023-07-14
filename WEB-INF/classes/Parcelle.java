package parcelle;

import connection.BddObject;
import connection.annotation.ColumnName;
import connection.annotation.ForeignKey;
import parcelle.Engrai;
import statistique.Composition;
import utilisateur.Utilisateur;
import recolte.Recolte;
import application.Application;
import java.sql.Connection;
import agregation.Liste;

public class Parcelle extends BddObject {
    
    @ColumnName("id_parcelle")
    String idParcelle;
    String nom;
    @ForeignKey
    Utilisateur proprietaire;
    Application[] applications;
    Recolte[] recoltes;
    Composition composition;

    public void setComposition(Composition composition) {
        this.composition = composition;
    }

    public Composition getComposition() {
        return composition;
    }

    public void setNom(String nom) throws IllegalArgumentException {
        if (nom == null) throw new IllegalArgumentException("Nom est null");
        if (nom.isEmpty()) throw new IllegalArgumentException("Nom est vide");
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setIdParcelle(String idParcelle) throws IllegalArgumentException {
        if (idParcelle == null) throw new IllegalArgumentException("idParcelle est null");
        if (idParcelle.isEmpty()) throw new IllegalArgumentException("idParcelle est vide");
        this.idParcelle = idParcelle;
    }

    public String getIdParcelle() {
        return idParcelle;
    }

    public void setProprietaire(Utilisateur proprietaire) throws IllegalArgumentException {
        if (proprietaire == null) throw new IllegalArgumentException("Propriaite est null");
        this.proprietaire = proprietaire;
    }

    public void setApplications(Application[] applications) throws Exception {
        if (applications.length <= 0) throw new Exception(this.getNom() + " n'a pas encore d'engrais applique");
        this.applications = applications;
    }
    
    public Application[] getApplications() {
        return applications;
    }
    
    public void setRecoltes(Recolte[] recoltes) throws Exception {
        if (applications.length <= 0) throw new Exception(this.getNom() + " n'a pas encore ete recolte");
        this.recoltes = recoltes;
    }

    public Recolte[] getRecoltes() {
        return recoltes;
    }

    public Utilisateur getProprietaire() {
        return proprietaire;
    }

    public Parcelle() throws Exception {
        super();
        this.setTable("parcelle");
        this.setFunctionPK("nextval('seq_id_parcelle')");
        this.setCountPK(7);
        this.setPrefix("PAR");
        this.setPrimaryKeyName("idParcelle");
        this.setConnection("PostgreSQL");
    }

    public Parcelle(String idParcelle) throws Exception {
        this();
        this.setIdParcelle(idParcelle);   
    }

    public Application[] getApplications(Connection connection) throws Exception {
        Application application = new Application();
        application.setParcelle(this);
        return application.<Application>findAll(connection, null, Application.class);
    }
    
    public Recolte[] getRecoltes(Connection connection) throws Exception {
        Recolte recolte = new Recolte();
        recolte.setParcelle(this);
        return recolte.<Recolte>findAll(connection, null, Recolte.class);
    }

    public double getQuantite(Engrai engrai) {
        double somme = 0;
        for (Application application : this.getApplications()) {
            if (application.getEngrai().equals(engrai)) somme += application.getQuantite();
        }
        return somme;
    }

    public double getQuantiteEngraiTotal() throws Exception {
        return Liste.sommer(this.getApplications(), "getQuantite");
    }
    
    public double getQuantiteProduitTotal() throws Exception {
        return Liste.sommer(this.getRecoltes(), "getQuantite");
    }
    
    public double getNombreEpiTotal() throws Exception {
        return Liste.sommer(this.getRecoltes(), "getNombreEpi");
    }
    
    public double getPourcentage(Engrai engrai) throws Exception {
        return this.getQuantite(engrai) / this.getQuantiteEngraiTotal();
    }

    public double getResultat(Engrai engrai) throws Exception {
        return (this.getPourcentage(engrai) * this.getQuantiteProduitTotal());
    }
    
    public double getResultatAdditif(Engrai engrai) throws Exception {
        return (this.getResultat(engrai)) + ((engrai.getAddition(this).getValeur() / 100) * this.getResultat(engrai));
    }

    public Composition getComposition(Connection connection) throws Exception {
        Engrai[] engrais = new Engrai().<Engrai>findAll(connection, null, Engrai.class);
        Composition[] compositions = new Composition[engrais.length];
        for (int i = 0; i < compositions.length; i++)
            compositions[i] = new Composition(engrais[i], this.getPourcentage(engrais[i]));
        Composition composition = new Composition();
        composition.setCompositions(compositions);
        return composition;
    }

}
