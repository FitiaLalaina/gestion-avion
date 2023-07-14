package statistique;

import java.sql.Connection;
import agregation.Liste;
import connection.BddObject;
import parcelle.Parcelle;

public class MeilleurComposition extends Ratio {

    Parcelle parcelle;

    public MeilleurComposition(Parcelle parcelle, double valeur, double produit) throws Exception {
        super(valeur, produit);
        this.setParcelle(parcelle);
    }

    public void setParcelle(Parcelle parcelle) throws Exception {
        if  (parcelle == null) throw new Exception("Parcelle est null");
        this.parcelle = parcelle;
    }

    public Parcelle getParcelle() {
        return parcelle;
    }

    public static MeilleurComposition[] getMeilleurCompositions() throws Exception {
        MeilleurComposition[] compositions = null;
        try (Connection connection = BddObject.getPostgreSQL()) {
            Parcelle[] parcelles = new Parcelle().<Parcelle>findAll(connection, null, Parcelle.class);
            compositions = new MeilleurComposition[parcelles.length];
            for (int i = 0; i < compositions.length; i++) {
                parcelles[i].setApplications(parcelles[i].getApplications(connection));
                parcelles[i].setRecoltes(parcelles[i].getRecoltes(connection));
                parcelles[i].setComposition(parcelles[i].getComposition(connection));
                compositions[i] = new MeilleurComposition(parcelles[i], parcelles[i].getNombreEpiTotal(), parcelles[i].getQuantiteProduitTotal());
            }
        }
        Liste.sort(compositions, "getRatio", "DESC");
        return compositions;
    }
    
}
