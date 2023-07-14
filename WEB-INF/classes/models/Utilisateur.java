package personne;
import java.time.LocalDateTime;
import avion.*;
import vol.*;
import transaction.*;
import connection.*;
import java.util.*;
import java.sql.*;

public class Utilisateur {
    private String idUtilisateur, nom, motDePass;
    private int identification;

    VolValide[] volValides;

    public void insertDonnee(Connexion c) throws Exception{
        String sql = "insert into Utilisateur (nom,motDePass,identnitficaion) values (?,?,?)";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getNom());
        statement.setString(2, this.getMotDePass());
        statement.setInt(3, this.getIdentification());
        statement.executeUpdate();
        statement.close();
    }
    public Utilisateur[] getDonnee(Connexion c) throws Exception{
        String sql = "SELECT * FROM Utilisateur";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<Utilisateur> resultats = new ArrayList<Utilisateur>();
        while (rs.next()) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setIdUtilisateur(rs.getString(1));
            utilisateur.setNom(rs.getString(2));
            utilisateur.setMotDePass(rs.getString(3));
            utilisateur.setIdentification(rs.getInt(4));
            resultats.add(utilisateur);
        }
        statement.close();
        return (Utilisateur[]) resultats.toArray();
    }
    public Utilisateur getDonneeById(Connexion c) throws Exception{
        String sql = "SELECT * FROM Utilisateur where idUtilisateur = ? ";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdUtilisateur());
        ResultSet rs = statement.executeQuery();
        Utilisateur utilisateur = new Utilisateur();
        if (rs.next()) {
            utilisateur.setIdUtilisateur(rs.getString(1));
            utilisateur.setNom(rs.getString(2));
            utilisateur.setMotDePass(rs.getString(3));
            utilisateur.setIdentification(rs.getInt(4));
            statement.close();
            return utilisateur;
        }
        statement.close();
        return null;
    }
    public void deleteDonnee(Connexion c) throws Exception{
        String sql = "delete from Utilisateur where idUtilisateur = ?";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdUtilisateur());
        statement.executeUpdate();
        statement.close();
    }

    public Utilisateur(){}
    public Utilisateur(String idUtilisateur, String nom, String motDePass,int identification){
        this.setIdUtilisateur(idUtilisateur);
        this.setNom(nom);
        this.setMotDePass(motDePass);
        this.setIdentification(identification);
    }
    public VolValide[] getVolValides(){
        return this.volValides;
    }
    public String getIdUtilisateur() {
        return this.idUtilisateur;
    }
    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getMotDePass() {
        return motDePass;
    }
    public void setMotDePass(String motDePass) {
        this.motDePass = motDePass;
    }
    public int getIdentification() {
        return identification;
    }
    public void setIdentification(int identification) {
        this.identification = identification;
    }

    
}