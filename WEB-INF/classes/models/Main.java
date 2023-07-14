import connection.Connexion;
public class Main {
    public static void main(String[] args) {
       try {
        Connexion c = new Connexion();
        System.out.println(c.getConnection());
       } catch (Exception e) {
        e.printStackTrace();
       } 
    }
}