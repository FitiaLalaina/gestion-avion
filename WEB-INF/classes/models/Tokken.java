package transaction;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import avion.*;
import vol.*;
import personne.*;
import connection.*;
import java.util.*;
import java.sql.*;

public class Tokken {
    private String idTokken, numero;

    private Reservation[] reservations;

    public void insertDonnee(Connexion c) throws Exception{
        String sql = "insert into Tokken (numero) values (?)";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getNumero());
        statement.executeUpdate();
        statement.close();
    }
    public Tokken[] getDonnee(Connexion c) throws Exception{
        String sql = "SELECT * FROM Tokken";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<Tokken> resultats = new ArrayList<Tokken>();
        while (rs.next()) {
            Tokken tokken = new Tokken();
            tokken.setIdTokken(rs.getString(1));
            tokken.setNumero(rs.getString(2));
            resultats.add(tokken);
        }
        statement.close();
        return (Tokken[]) resultats.toArray();
    }
    public Tokken getDonneeById(Connexion c) throws Exception{
        String sql = "SELECT * FROM Tokken where idTokken = ? ";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdTokken());
        ResultSet rs = statement.executeQuery();
        Tokken tokken = new Tokken();
        if (rs.next()) {
            tokken.setIdTokken(rs.getString(1));
            tokken.setNumero(rs.getString(2));
            statement.close();
            return tokken;
        }
        statement.close();
        return null;
    }
    public void deleteDonnee(Connexion c) throws Exception{
        String sql = "delete from Tokken where idTokken = ?";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdTokken());
        statement.executeUpdate();
        statement.close();
    }
    
    public Tokken(){}
    public Tokken(String idTokken, String numero){
        this.setIdTokken(idTokken);
        this.setNumero(numero);
    }

    public Reservation[] getReservation(){
        return this.reservations;
    }
    public String getIdTokken() {
        return idTokken;
    }
    public void setIdTokken(String idTokken) {
        this.idTokken = idTokken;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }    
}
