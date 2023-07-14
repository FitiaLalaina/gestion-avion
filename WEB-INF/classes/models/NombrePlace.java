package avion;
import vol.*;
import transaction.*;
import personne.*;
import java.util.*;
import connection.*;
import java.sql.*;

public class NombrePlace {
    private String idNombrePlace, idTypePlace;
    private int nombrePlaces;
    private String idAircrcaft;

    private TypePlace typePlace;
    private Aircraft aircraft;
    private PrixVol[] prixVols;

    public NombrePlace(){

    }
    
    public void insertDonnee(Connexion c) throws Exception{
        String sql = "insert into NombrePlace (idTypePlace,nombrePlaces,idAircraft) values (?,?,?)";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdTypePlace());
        statement.setInt(2, this.getNombrePlaces());
        statement.setString(2, this.getIdAircrcaft());
        statement.executeUpdate();
        statement.close();
    }
    public NombrePlace[] getDonnee(Connexion c) throws Exception{
        String sql = "SELECT * FROM NombrePlace";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<NombrePlace> resultats = new ArrayList<NombrePlace>();
        while (rs.next()) {
            NombrePlace nombrePlace = new NombrePlace();
            nombrePlace.setIdNombrePlace(rs.getString(1));
            nombrePlace.setIdTypePlace(rs.getString(2));
            nombrePlace.setNombrePlaces(rs.getInt(3));
            nombrePlace.setIdAircrcaft(rs.getString(4));
            resultats.add(nombrePlace);
        }
        statement.close();
        return (NombrePlace[]) resultats.toArray();
    }
    public NombrePlace getDonneeById(Connexion c) throws Exception{
        String sql = "SELECT * FROM NombrePlace where idNombrePlace = ? ";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdNombrePlace());
        ResultSet rs = statement.executeQuery();
        NombrePlace nombrePlace = new NombrePlace();
        if (rs.next()) {
            nombrePlace.setIdNombrePlace(rs.getString(1));
            nombrePlace.setIdTypePlace(rs.getString(2));
            nombrePlace.setNombrePlaces(rs.getInt(3));
            nombrePlace.setIdAircrcaft(rs.getString(4));
            statement.close();
            return nombrePlace;
        }
        statement.close();
        return null;
    }
    public void deleteDonnee(Connexion c) throws Exception{
        String sql = "delete from NombrePlace where idNombrePlace = ?";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdNombrePlace());
        statement.executeUpdate();
        statement.close();
    }

    public NombrePlace(String idNombrePlace, String idTypePlace,int nombrePlaces, String idAircraft){
        this.setNombrePlaces(nombrePlaces);
        this.setIdTypePlace(idTypePlace);
        this.setIdNombrePlace(idNombrePlace);
        this.setIdAircrcaft(idAircraft);
    }
    
    public TypePlace getTypePlace() {
        return typePlace;
    }
    public Aircraft getAircraft() {
        return aircraft;
    }
    public PrixVol[] getPrixVols() {
        return prixVols;
    }
    public String getIdNombrePlace() {
        return idNombrePlace;
    }
    public void setIdNombrePlace(String idNombrePlace) {
        this.idNombrePlace = idNombrePlace;
    }
    public String getIdTypePlace() {
        return idTypePlace;
    }
    public void setIdTypePlace(String idTypePlace) {
        this.idTypePlace = idTypePlace;
    }
    public int getNombrePlaces() {
        return nombrePlaces;
    }
    public void setNombrePlaces(int nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }
    public String getIdAircrcaft() {
        return idAircrcaft;
    }
    public void setIdAircrcaft(String idAircrcaft) {
        this.idAircrcaft = idAircrcaft;
    }
}
