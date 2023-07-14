package statistique;

import statistique.Ratio;
import java.sql.Connection;
import agregation.Liste;
import connection.BddObject;
import parcelle.Engrai;
import parcelle.Parcelle;

public class MeilleurEngrai extends Ratio {
    
    Engrai engrai;

    public void setEngrai(Engrai engrai) {
        this.engrai = engrai;
    }

    public Engrai getEngrai() {
        return engrai;
    }

    public MeilleurEngrai(Engrai engrai, double quantite, double produit) throws Exception {
        super(quantite, produit);
        this.setEngrai(engrai);
    }

    public static MeilleurEngrai[] getMeilleurEngrais(Parcelle additif, Connection connection) throws Exception {
        MeilleurEngrai[] meilleurEngrais = null;
        Engrai[] engrais = new Engrai().<Engrai>findAll(connection, null, Engrai.class);
        Parcelle[] parcelles = new Parcelle().<Parcelle>findAll(connection, null, Parcelle.class);
        for (Parcelle parcelle : parcelles) {
            parcelle.setApplications(parcelle.getApplications(connection));
            parcelle.setRecoltes(parcelle.getRecoltes(connection));
        }
        meilleurEngrais = new MeilleurEngrai[engrais.length];
        for (int i = 0; i < meilleurEngrais.length; i++) {
            if (additif != null) engrais[i].setAdditions(engrais[i].getAddition(connection));
            double quantite = 0;
            double produit = 0;
            for (Parcelle parcelle : parcelles) {
                quantite += parcelle.getQuantite(engrais[i]);
                produit += (additif != null && additif.getIdParcelle().equals(parcelle.getIdParcelle())) ? parcelle.getResultatAdditif(engrais[i]) : parcelle.getResultat(engrais[i]);
            }
            meilleurEngrais[i] = new MeilleurEngrai(engrais[i], quantite, produit);
        }
        Liste.sort(meilleurEngrais, "getRatio", "DESC");
        return meilleurEngrais;
    }
    
    public static MeilleurEngrai[] getMeilleurEngrais(Parcelle additif) throws Exception {
        MeilleurEngrai[] meilleurEngrais = null;
        try (Connection connection = BddObject.getPostgreSQL()) {
            meilleurEngrais  = MeilleurEngrai.getMeilleurEngrais(additif, connection);
        }
        return meilleurEngrais;
    }



}
