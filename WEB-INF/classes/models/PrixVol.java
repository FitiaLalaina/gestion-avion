package transaction;
import connection.*;
import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;
import avion.*;
import vol.*;
import personne.*;

public class PrixVol {
    private String idPrixVol, idVol, idNombrePlace;
    private double prix;

    private Vol vol;
    private NombrePlace nombrePlace;
    private Remise[] remises;
    private Reservation[] reservervations; 

    public void insertDonnee(Connexion c) throws Exception{
        String sql = "insert into PrixVol (idVol,idNombrePlace,prix) values (?,?,?)";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdVol());
        statement.setString(2, this.getIdNombrePlace());
        statement.setDouble(3, this.getPrix());
        statement.executeUpdate();
        statement.close();
    }
    public PrixVol[] getDonnee(Connexion c) throws Exception{
        String sql = "SELECT * FROM PrixVol";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<PrixVol> resultats = new ArrayList<PrixVol>();
        while (rs.next()) {
            PrixVol prixVol = new PrixVol();
            prixVol.setIdPrixVol(rs.getString(1));
            prixVol.setIdVol(rs.getString(2));
            prixVol.setIdNombrePlace(rs.getString(3));
            prixVol.setPrix(rs.getDouble(4));
            resultats.add(prixVol);
        }
        statement.close();
        return (PrixVol[]) resultats.toArray();
    }
    public PrixVol getDonneeById(Connexion c) throws Exception{
        String sql = "SELECT * FROM PrixVol where idPrixVol = ? ";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdPrixVol());
        ResultSet rs = statement.executeQuery();
        PrixVol prixVol = new PrixVol();
        if (rs.next()) {
            prixVol.setIdPrixVol(rs.getString(1));
            prixVol.setIdVol(rs.getString(2));
            prixVol.setIdNombrePlace(rs.getString(3));
            prixVol.setPrix(rs.getDouble(4));
            statement.close();
            return prixVol;
        }
        statement.close();
        return null;
    }
    public void deleteDonnee(Connexion c) throws Exception{
        String sql = "delete from PrixVol where idPrixVol = ?";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdPrixVol());
        statement.executeUpdate();
        statement.close();
    }

    public PrixVol(){}
    public PrixVol(String idPrixVol, String idVol, String idNombrePlace, double prix) {
        this.setIdPrixVol(idPrixVol);
        this.setIdVol(idVol);
        this.setIdNombrePlace(idNombrePlace);
        this.setPrix(prix);
    }
    public Remise[] getRemises(){
        return this.remises;
    }
    public Vol getVol() {
        return vol;
    }
    public NombrePlace getNombrePlace() {
        return nombrePlace;
    }
    public Reservation[] getReservation(){
        return  this.reservervations;
    }
    public String getIdPrixVol() {
        return idPrixVol;
    }
    public void setIdPrixVol(String idPrixVol) {
        this.idPrixVol = idPrixVol;
    }
    public String getIdVol() {
        return idVol;
    }
    public void setIdVol(String idVol) {
        this.idVol = idVol;
    }
    public String getIdNombrePlace() {
        return idNombrePlace;
    }
    public void setIdNombrePlace(String idNombrePlace) {
        this.idNombrePlace = idNombrePlace;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    
}
