package avion;
import vol.*;
import transaction.*;
import personne.*;
import connection.*;
import java.util.*;
import java.sql.*;

public class TypePlace {
    private String idTypePlace,nom;

    private NombrePlace[] nombrePlaces;

    public void insertDonnee(Connexion c) throws Exception{
        String sql = "insert into TypePlace (nom) values (?)";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getNom());
        statement.executeUpdate();
        statement.close();
    }
    public TypePlace[] getDonnee(Connexion c) throws Exception{
        String sql = "SELECT * FROM TypePlace";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<TypePlace> resultats = new ArrayList<TypePlace>();
        while (rs.next()) {
            TypePlace typePlace = new TypePlace();
            typePlace.setIdTypePlace(rs.getString(1));
            typePlace.setNom(rs.getString(2));
            resultats.add(typePlace);
        }
        statement.close();
        return (TypePlace[]) resultats.toArray();
    }
    public TypePlace getDonneeById(Connexion c) throws Exception{
        String sql = "SELECT * FROM TypePlace where idTypePlace = ? ";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdTypePlace());
        ResultSet rs = statement.executeQuery();
        TypePlace typePlace = new TypePlace();
        if (rs.next()) {
            typePlace.setIdTypePlace(rs.getString(1));
            typePlace.setNom(rs.getString(2));
            statement.close();
            return typePlace;
        }
        statement.close();
        return null;
    }
    public void deleteDonnee(Connexion c) throws Exception{
        String sql = "delete from TypePlace where idTypePlace = ?";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdTypePlace());
        statement.executeUpdate();
        statement.close();
    }

    public TypePlace(){}
    public NombrePlace[] getNombrePlaces(){
        return this.nombrePlaces;
    }
    public String getIdTypePlace() {
        return idTypePlace;
    }
    public void setIdTypePlace(String idTypePlace) {
        this.idTypePlace = idTypePlace;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public TypePlace(String idTypePlace, String nom){
        this.setIdTypePlace(idTypePlace);
        this.setNom(nom);
    }
    
}
