package utilisateur;

import connection.BddObject;

public class Utilisateur extends BddObject {

    String idUtilisateur;
    String nom;

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Exception {
        if (nom == null) throw new Exception("Nom de l'Utilisateur est null");
        if (nom.isEmpty()) throw new Exception("Nom de l'utilisateur est vide");
        this.nom = nom;
    }

    public void setIdUtilisateur(String idUtilisateur) throws Exception {
        if (idUtilisateur == null) throw new Exception("L'ID Utilisateur est null");
        if (idUtilisateur.isEmpty()) throw new Exception("ID" + this.getClass().getSimpleName() + " est vide");
        this.idUtilisateur = idUtilisateur;
    }

    public Utilisateur(String nom) throws Exception {
        this();
        this.setNom(nom);
    }

    public Utilisateur(String idUtilisateur, String nom) throws Exception {
        this();
        this.setIdUtilisateur(idUtilisateur);
        this.setNom(nom);
    }

    public Utilisateur() throws Exception {
        this.setTable("utilisateur");
        this.setPrimaryKeyName("idUtilisateur");
    }

    public static String[] getFeatures() {
        return new String[] {"Application", "Depense", "Recolte", "Meilleur Engrai", "Meilleur Engrai Additif", "Meilleur Composition", "Meilleur Prix Engrai"};
    }

    public static String getActive(String feature, String page) {
        page = page.replace(".jsp", "").replace("-", " ");
        return feature.toLowerCase().equals(page.toLowerCase()) ? "active" : "text-dark";
    }

}
