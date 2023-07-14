package vol;
import java.time.LocalDateTime;
import avion.*;
import transaction.*;
import personne.*;
import connection.*;
import java.util.*;
import java.sql.*;

public class Vol {
    private String idVol;
    private LocalDateTime dateDepart;
    private String idAircraft, idRegionDepart, idRegionArriver;

    private VolValide[] valValides;
    private Aircraft aircraft;
    private Region regionDepart, regionArriver;

    public void insertDonnee(Connexion c) throws Exception{
        String sql = "insert into Vol (dateDepart,idAircraft,idRegionDepart,idRegionArriver) values (?,?,?,?)";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setTimestamp(1, Timestamp.valueOf(this.getDateDepart()));
        statement.setString(2, this.getIdAircraft());
        statement.setString(3, this.getIdRegionDepart());
        statement.setString(4, this.getIdRegionArriver());
        statement.executeUpdate();
        statement.close();
    }

    public Vol[] getDonnee(Connexion c) throws Exception{
        String sql = "SELECT * FROM Vol";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<Vol> resultats = new ArrayList<Vol>();
        while (rs.next()) {
            Vol vol = new Vol();
            vol.setIdVol(rs.getString(1));
            vol.setDateDepart(rs.getTimestamp(2).toLocalDateTime());
            vol.setIdAircraft(rs.getString(3));
            vol.setIdRegionDepart(rs.getString(4));
            vol.setIdRegionArriver(rs.getString(5));
            resultats.add(vol);
        }
        statement.close();
        return (Vol[]) resultats.toArray();
    }
    public Vol getDonneeById(Connexion c) throws Exception{
        String sql = "SELECT * FROM Vol where idVol = ? ";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdVol());
        ResultSet rs = statement.executeQuery();
        Vol vol = new Vol();
        if (rs.next()) {
            vol.setIdVol(rs.getString(1));
            vol.setDateDepart(rs.getTimestamp(2).toLocalDateTime());
            vol.setIdAircraft(rs.getString(3));
            vol.setIdRegionDepart(rs.getString(4));
            vol.setIdRegionArriver(rs.getString(5));
            statement.close();
            return vol;
        }
        return null;
    }
    public void deleteDonnee(Connexion c) throws Exception{
        String sql = "delete from Vol where idVol = ?";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdVol());
        statement.executeUpdate();
        statement.close();
    }

    public Vol(String idVol, LocalDateTime dateDepart, String idAircraft, String idRegionDepart,String idRegionArriver){
        this.setIdVol(idVol);
        this.setDateDepart(dateDepart);
        this.setIdAircraft(idAircraft);
        this.setIdRegionDepart(idRegionDepart);
        this.setIdRegionArriver(idRegionArriver);
    }

    public Vol(){}
    
    public VolValide[] getValValides() {
        return valValides;
    }
    public Aircraft getAircraft() {
        return aircraft;
    }
    public Region getRegionDepart() {
        return regionDepart;
    }
    public Region getRegionArriver() {
        return regionArriver;
    }
    public String getIdVol() {
        return idVol;
    } 
    public void setIdVol(String idVol) {
        this.idVol = idVol;
    }
    public LocalDateTime getDateDepart() {
        return dateDepart;
    }
    public void setDateDepart(LocalDateTime dateDepart) {
        this.dateDepart = dateDepart;
    }
    public String getIdAircraft() {
        return idAircraft;
    }
    public void setIdAircraft(String idAircraft) {
        this.idAircraft = idAircraft;
    }
    public String getIdRegionDepart() {
        return idRegionDepart;
    }
    public void setIdRegionDepart(String idRegionDepart) {
        this.idRegionDepart = idRegionDepart;
    }
    public String getIdRegionArriver() {
        return idRegionArriver;
    }
    public void setIdRegionArriver(String idRegionArriver) {
        this.idRegionArriver = idRegionArriver;
    }

    
}
