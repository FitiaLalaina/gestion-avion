package transaction;
import connection.*;
import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;
import avion.*;
import vol.*;
import personne.*;

public class Region {
    String idRegion, nom, pays;

    private Vol[] volDeparts, volArriver;

    public void insertDonnee(Connexion c) throws Exception{
        String sql = "insert into Region (nom,pays) values (?,?)";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getNom());
        statement.setString(2, this.getPays());
        statement.executeUpdate();
        statement.close();
    }
    public Region[] getDonnee(Connexion c) throws Exception{
        String sql = "SELECT * FROM Region";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<Region> resultats = new ArrayList<Region>();
        while (rs.next()) {
            Region region = new Region();
            region.setIdRegion(rs.getString(1));
            region.setNom(rs.getString(2));
            region.setPays(rs.getString(3));
            resultats.add(region);
        }
        statement.close();
        return (Region[]) resultats.toArray();
    }
    public Region getDonneeById(Connexion c) throws Exception{
        String sql = "SELECT * FROM Region where idRegion = ? ";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdRegion());
        ResultSet rs = statement.executeQuery();
        Region region = new Region();
        if (rs.next()) {
            region.setIdRegion(rs.getString(1));
            region.setNom(rs.getString(2));
            region.setPays(rs.getString(3));
            statement.close();
            return region;
        }
        statement.close();
        return null;
    }
    public void deleteDonnee(Connexion c) throws Exception{
        String sql = "delete from Region where idRegion = ?";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdRegion());
        statement.executeUpdate();
        statement.close();
    }

    public Region(){}
    public Region(String idRegion, String nom, String pays){
        this.setIdRegion(idRegion);
        this.setNom(nom);
        this.setPays(pays);
    }
    public Vol[] getVolDeparts() {
        return volDeparts;
    }
    public Vol[] getVolArriver() {
        return volArriver;
    }
    public String getIdRegion() {
        return idRegion;
    }
    public void setIdRegion(String idRegion) {
        this.idRegion = idRegion;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPays() {
        return pays;
    }
    public void setPays(String pays) {
        this.pays = pays;
    }    
}
