package transaction;
import connection.*;
import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;
import avion.*;
import vol.*;
import personne.*;

public class Remise {
    private String idRemise;
    private LocalDateTime dateFin;
    private String idPrixVol;
    private int pourcentage;

    private PrixVol prixVol;

    public void insertDonnee(Connexion c) throws Exception{
        String sql = "insert into Region (dateFin,idPrixVol,pourcentage) values (?,?,?)";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setTimestamp(1, Timestamp.valueOf(this.getDateFin()));
        statement.setString(2, this.getIdPrixVol());
        statement.setInt(3, this.getPourcentage());
        statement.executeUpdate();
        statement.close();
    }
    public Remise[] getDonnee(Connexion c) throws Exception{
        String sql = "SELECT * FROM Remise";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<Remise> resultats = new ArrayList<Remise>();
        while (rs.next()) {
            Remise remise = new Remise();
            remise.setIdRemise(rs.getString(1));
            remise.setDateFin(rs.getTimestamp(2).toLocalDateTime());
            remise.setIdPrixVol(rs.getString(3));
            remise.setPourcentage(rs.getInt(4));
            resultats.add(remise);
        }
        statement.close();
        return (Remise[]) resultats.toArray();
    }
    public Remise getDonneeById(Connexion c) throws Exception{
        String sql = "SELECT * FROM Remise where idRemise = ?";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdRemise());
        ResultSet rs = statement.executeQuery();
        Remise remise = new Remise();
        if (rs.next()) {
            remise.setIdRemise(rs.getString(1));
            remise.setDateFin(rs.getTimestamp(2).toLocalDateTime());
            remise.setIdPrixVol(rs.getString(3));
            remise.setPourcentage(rs.getInt(4));
            statement.close();
            return remise;
        }
        statement.close();
        return null;
    }
    public void deleteDonnee(Connexion c) throws Exception{
        String sql = "delete from Remise where idRemise = ?";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdRemise());
        statement.executeUpdate();
        statement.close();
    }
    public Remise(){}
    public Remise(String idRemise, LocalDateTime dateFin, String idPrixVol, int pourcentage) {
        this.setIdRemise(idRemise);
        this.setDateFin(dateFin);
        this.setIdPrixVol(idPrixVol);
        this.setPourcentage(pourcentage);
    }
    public PrixVol getPrixVol(){
        return this.prixVol;
    }
    public String getIdRemise() {
        return idRemise;
    }
    public void setIdRemise(String idRemise) {
        this.idRemise = idRemise;
    }
    public LocalDateTime getDateFin() {
        return dateFin;
    }
    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }
    public String getIdPrixVol() {
        return idPrixVol;
    }
    public void setIdPrixVol(String idPrixVol) {
        this.idPrixVol = idPrixVol;
    }
    public int getPourcentage() {
        return pourcentage;
    }
    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    
}
