package transaction;
import java.time.LocalDateTime;
import avion.*;
import vol.*;
import personne.*;
import connection.*;
import java.util.*;
import java.sql.*;

public class ReservationValide {
    private String idReservationValide, idReservation;

    private Reservation reservation;

    public void insertDonnee(Connexion c) throws Exception{
        String sql = "insert into ReservationValide (idReservation) values (?)";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdReservation());
        statement.executeUpdate();
        statement.close();
    }
    public ReservationValide[] getDonnee(Connexion c) throws Exception{
        String sql = "SELECT * FROM ReservationValide";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<ReservationValide> resultats = new ArrayList<ReservationValide>();
        while (rs.next()) {
            ReservationValide reservationValide = new ReservationValide();
            reservationValide.setIdReservationValide(rs.getString(1));
            reservationValide.setIdReservation(rs.getString(2));
            resultats.add(reservationValide);
        }
        statement.close();
        return (ReservationValide[]) resultats.toArray();
    }
    public ReservationValide getDonneeById(Connexion c) throws Exception{
        String sql = "SELECT * FROM ReservationValide where idReservationValide = ? ";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdReservationValide());
        ResultSet rs = statement.executeQuery();
        ReservationValide reservationValide = new ReservationValide();
        if (rs.next()) {
            reservationValide.setIdReservationValide(rs.getString(1));
            reservationValide.setIdReservation(rs.getString(2));
            statement.close();
            return reservationValide;
        }
        statement.close();
        return null;
    }
    public void deleteDonnee(Connexion c) throws Exception{
        String sql = "delete from ReservationValide where idReservationValide = ?";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdReservationValide());
        statement.executeUpdate();
        statement.close();
    }

    public ReservationValide(){}
    public ReservationValide(String idReservationValide,String idReservation){
        this.setIdReservationValide(idReservationValide);
        this.setIdReservation(idReservation);
    }
    public Reservation getReservation(){
        return this.reservation;
    }
    public String getIdReservationValide() {
        return idReservationValide;
    }
    public void setIdReservationValide(String idReservationValide) {
        this.idReservationValide = idReservationValide;
    }
    public String getIdReservation() {
        return idReservation;
    }
    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }
}