package transaction;
import java.time.LocalDateTime;
import avion.*;
import vol.*;
import personne.*;
import connection.*;
import java.util.*;
import java.sql.*;

public class Reservation {
    private String idReservation, idTokken;
    private LocalDateTime dateReservation;
    private String nomVisiteur, idPrixVol;

    ReservationValide[] reservationValides;
    Tokken tokken;
    PrixVol prixVol;

    public void insertDonnee(Connexion c) throws Exception{
        String sql = "insert into Reservation (idTokken,dateReservation,nomVisiteur,idPrixVol) values (?,?,?,?)";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdTokken());
        statement.setTimestamp(2, Timestamp.valueOf(this.getDateReservation()));
        statement.setString(3, this.getNomVisiteur());
        statement.setString(4, this.getIdPrixVol());
        statement.executeUpdate();
        statement.close();
    }
    public Reservation[] getDonnee(Connexion c) throws Exception{
        String sql = "SELECT * FROM Reservation";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<Reservation> resultats = new ArrayList<Reservation>();
        while (rs.next()) {
            Reservation reservation = new Reservation();
            reservation.setIdReservation(rs.getString(1));
            reservation.setIdTokken(rs.getString(2));
            reservation.setDateReservation(rs.getTimestamp(3).toLocalDateTime());
            reservation.setNomVisiteur(rs.getString(4));
            reservation.setIdPrixVol(rs.getString(5));
            resultats.add(reservation);
        }
        statement.close();
        return (Reservation[]) resultats.toArray();
    }
    public Reservation getDonneeById(Connexion c) throws Exception{
        String sql = "SELECT * FROM Reservation where idReservation = ? ";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdReservation());
        ResultSet rs = statement.executeQuery();
        Reservation reservation = new Reservation();
        if (rs.next()) {
            reservation.setIdReservation(rs.getString(1));
            reservation.setIdTokken(rs.getString(2));
            reservation.setDateReservation(rs.getTimestamp(3).toLocalDateTime());
            reservation.setNomVisiteur(rs.getString(4));
            reservation.setIdPrixVol(rs.getString(5));
            statement.close();
            return reservation;
        }
        statement.close();
        return null;
    }
    public void deleteDonnee(Connexion c) throws Exception{
        String sql = "delete from Reservation where idReservation = ?";
        PreparedStatement statement = c.getConnection().prepareStatement(sql);
        statement.setString(1, this.getIdReservation());
        statement.executeUpdate();
        statement.close();
    }

    public Reservation(){}
    public Reservation(String idReservation, String idTokken,LocalDateTime dateReservation, String nomVisiteur, String idPrixVol){
        this.setIdReservation(idReservation);
        this.setIdTokken(idTokken);
        this.setDateReservation(dateReservation);
        this.setNomVisiteur(nomVisiteur);
        this.setIdPrixVol(idPrixVol);
    }
    
    public LocalDateTime getDateReservation() {
        return dateReservation;
    }
    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }
    public ReservationValide[] getReservationValides() {
        return reservationValides;
    }
    public Tokken getTokken() {
        return tokken;
    }
    public PrixVol getPrixVol() {
        return prixVol;
    }
    public String getIdReservation() {
        return idReservation;
    }
    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }
    public String getIdTokken() {
        return idTokken;
    }
    public void setIdTokken(String idTokken) {
        this.idTokken = idTokken;
    }
    public String getNomVisiteur() {
        return nomVisiteur;
    }
    public void setNomVisiteur(String nomVisiteur) {
        this.nomVisiteur = nomVisiteur;
    }
    public String getIdPrixVol() {
        return idPrixVol;
    }
    public void setIdPrixVol(String idPrixVol) {
        this.idPrixVol = idPrixVol;
    }    
    
}