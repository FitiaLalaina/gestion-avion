package avion;
import vol.*;
import transaction.*;
import personne.*;
import connection.*;
import java.sql.*;
import java.util.ArrayList;

public class Aircraft {
    private String idAircraft, nom, idTypeAircraft;

    private TypeAircraft typeAircraft;
    private NombrePlace[] nombrePlaces;
    private Vol[] vols;

    public Aircraft[] getDonnee(Connexion c) throws Exception{
        String sql = "SELECT * FROM Aircraft";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<Aircraft> resultats = new ArrayList<Aircraft>();
        while (rs.next()) {
            Aircraft aircraft = new Aircraft();
            aircraft.setIdAircraft(rs.getString(1));
            aircraft.setNom(rs.getString(2));
            aircraft.setIdTypeAircraft(rs.getString(3));
            resultats.add(aircraft);
        }
        statement.close();
        return (Aircraft[]) resultats.toArray();
    }
    public Aircraft getDonneeById(Connexion c) throws Exception{
        String sql = "SELECT * FROM Aircraft where idAircraft = ? ";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdAircraft());
        ResultSet rs = statement.executeQuery();
        Aircraft aircraft = new Aircraft();
        if (rs.next()) {
            aircraft.setIdAircraft(rs.getString(1));
            aircraft.setNom(rs.getString(2));
            aircraft.setIdTypeAircraft(rs.getString(3));
            statement.close();
            return aircraft;
        }
        return null;
    }
    public void deleteDonnee(Connexion c) throws Exception{
        String sql = "delete from Aircraft where idAircraft = ?";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdAircraft());
        statement.executeUpdate();
        statement.close();
    }
    public void insertDonnee(Connexion c) throws Exception{
        String sql = "insert into Aircraft (nom,idTypeAircraft) values (?,?)";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getNom());
        statement.setString(2, this.getIdTypeAircraft());
        statement.executeUpdate();
        statement.close();
    }
    public Aircraft(){}
    public String getIdAircraft() {
        return idAircraft;
    }
    public void setIdAircraft(String idAircraft) {
        this.idAircraft = idAircraft;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getIdTypeAircraft() {
        return idTypeAircraft;
    }
    public void setIdTypeAircraft(String idTypeAircraft) {
        this.idTypeAircraft = idTypeAircraft;
    }
    public Aircraft(String idAircraft, String nom, String idTypeAircraft){
        this.setIdAircraft(idAircraft);
        this.setNom(nom);
        this.setIdTypeAircraft(idTypeAircraft);
    }
}