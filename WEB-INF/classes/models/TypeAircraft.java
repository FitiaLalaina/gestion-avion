package avion;
import vol.*;
import transaction.*;
import personne.*;
import connection.*;
import java.util.*;
import java.sql.*;

public class TypeAircraft {
    private String idTypeAircraft, nom;
    
    private Aircraft[] aircrafts;

    public void insertDonnee(Connexion c) throws Exception{
        String sql = "insert into TypeAircraft (nom) values (?)";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getNom());
        statement.executeUpdate();
        statement.close();
    }
    public TypeAircraft[] getDonnee(Connexion c) throws Exception{
        String sql = "SELECT * FROM TypeAircraft";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<TypeAircraft> resultats = new ArrayList<TypeAircraft>();
        while (rs.next()) {
            TypeAircraft typeAircraft = new TypeAircraft();
            typeAircraft.setIdTypeAircraft(rs.getString(1));
            typeAircraft.setNom(rs.getString(2));
            resultats.add(typeAircraft);
        }
        statement.close();
        return (TypeAircraft[]) resultats.toArray();
    }
    public TypeAircraft getDonneeById(Connexion c) throws Exception{
        String sql = "SELECT * FROM TypeAircraft where idTypeAircraft = ? ";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdTypeAircraft());
        ResultSet rs = statement.executeQuery();
        TypeAircraft typeAircraft = new TypeAircraft();
        if (rs.next()) {
            typeAircraft.setIdTypeAircraft(rs.getString(1));
            typeAircraft.setNom(rs.getString(2));
            statement.close();
            return typeAircraft;
        }
        statement.close();
        return null;
    }
    public void deleteDonnee(Connexion c) throws Exception{
        String sql = "delete from TypeAircraft where idTypeAircraft = ?";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdTypeAircraft());
        statement.executeUpdate();
        statement.close();
    }

    public String getIdTypeAircraft() {
        return idTypeAircraft;
    }
    public void setIdTypeAircraft(String idTypeAircraft) {
        this.idTypeAircraft = idTypeAircraft;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Aircraft[] getAircrafts(){
        return this.aircrafts;
    }
    public TypeAircraft(){}
    public TypeAircraft(String idAircraft, String nom){
        this.setIdTypeAircraft(idTypeAircraft);
        this.setNom(nom);
    }
    
}