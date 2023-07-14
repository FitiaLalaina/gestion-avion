package transaction;
import java.time.LocalDateTime;
import avion.*;
import vol.*;
import personne.*;
import connection.*;
import java.util.*;
import java.sql.*;

public class VolValide {
    private String idVolValide, idVol;
    private LocalDateTime dateValidation;
    private String idUtilisateur;

    private Vol vol;
    private Utilisateur utilisateur;

    public void insertDonnee(Connexion c) throws Exception{
        String sql = "insert into VolValide (idVol,dateValidation,idUtilisateur) values (?,?,?)";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, getIdVol());
        statement.setTimestamp(2, Timestamp.valueOf(this.getDateValidation()));
        statement.setString(3, this.getIdUtilisateur());
        statement.executeUpdate();
        statement.close();
    }

    public VolValide[] getDonnee(Connexion c) throws Exception{
        String sql = "SELECT * FROM VolValide";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<VolValide> resultats = new ArrayList<VolValide>();
        while (rs.next()) {
            VolValide volValide = new VolValide();
            volValide.setIdVolValide(rs.getString(1));
            volValide.setIdVol(rs.getString(2));
            volValide.setDateValidation(rs.getTimestamp(3).toLocalDateTime());
            resultats.add(volValide);
        }
        statement.close();
        return (VolValide[]) resultats.toArray();
    }
    public VolValide getDonneeById(Connexion c) throws Exception{
        String sql = "SELECT * FROM VolValide where idVolValide = ? ";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdVolValide());
        ResultSet rs = statement.executeQuery();
        VolValide volValide = new VolValide();
        if (rs.next()) {
            volValide.setIdVolValide(rs.getString(1));
            volValide.setIdVol(rs.getString(2));
            volValide.setDateValidation(rs.getTimestamp(3).toLocalDateTime());
            statement.close();
            return volValide;
        }
        return null;
    }
    public void deleteDonnee(Connexion c) throws Exception{
        String sql = "delete from VolValide where idVolValide = ?";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdVolValide());
        statement.executeUpdate();
        statement.close();
    }

    public VolValide(){}
    public VolValide(String idVolValide, String idVol, LocalDateTime dateValidation, String idUtilisateur){
        this.setIdVolValide(idVolValide);
        this.setIdVol(idVol);
        this.setDateValidation(dateValidation);
        this.setIdUtilisateur(idUtilisateur);
    }
    public Vol getVol() {
        return vol;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public String getIdVolValide() {
        return idVolValide;
    }
    public void setIdVolValide(String idVolValide) {
        this.idVolValide = idVolValide;
    }
    public String getIdVol() {
        return idVol;
    }
    public void setIdVol(String idVol) {
        this.idVol = idVol;
    }
    public LocalDateTime getDateValidation() {
        return dateValidation;
    }
    public void setDateValidation(LocalDateTime dateValidation) {
        this.dateValidation = dateValidation;
    }
    public String getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    
}
