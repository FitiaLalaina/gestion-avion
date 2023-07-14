package statistique;

import java.sql.Connection;

import agregation.Liste;
import connection.BddObject;
import parcelle.Engrai;
import parcelle.Parcelle;

public class MeilleurPrixEngrai extends MeilleurEngrai {

    double prixUnitaireMoyenne;
    double quantite;

    public double getPrixUnitaireMoyenne() {
        return prixUnitaireMoyenne;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setPrixUnitaireMoyenne(double prixUnitaireMoyenne) {
        this.prixUnitaireMoyenne = prixUnitaireMoyenne;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    
    public MeilleurPrixEngrai(Engrai engrai, double prixUnitaire, double quantite, double prix, double produit) throws Exception {
        super(engrai, prix, produit);
        this.setPrixUnitaireMoyenne(prixUnitaire);
        this.setQuantite(quantite);
    }

    public static MeilleurPrixEngrai[] getMeilleurPrixEngrais() throws Exception {
        MeilleurPrixEngrai[] prix = null;
        try (Connection connection = BddObject.getPostgreSQL()) {
            MeilleurEngrai[] engrais = MeilleurEngrai.getMeilleurEngrais(null, connection);
            prix = new MeilleurPrixEngrai[engrais.length];
            for (int i = 0; i < prix.length; i++) {
                engrais[i].getEngrai().setDepenses(engrais[i].getEngrai().getDepenses(connection));
                // ! Le produit et la valeur sont inverse pour le ratio de triage
                // TODO Quantite des applications ou Quantite des depenses
                prix[i] = new MeilleurPrixEngrai(engrais[i].getEngrai(), engrais[i].getEngrai().getPrixUnitaireMoyenne(), engrais[i].getEngrai().getQuantite(), engrais[i].getProduit(), engrais[i].getEngrai().getPrixUnitaireMoyenne() * engrais[i].getEngrai().getQuantite());
            }
            Liste.sort(prix, "getRatio", "ASC");
        }
        return prix;
    }

}