package connection;

import java.sql.*;

public class Connexion
{
    private Connection c;
    public Connection getConnection()
    {
        return this.c;
    }
    private Connection makeConnexion() throws Exception
    {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://localhost:1434;encrypt=true;database=Avion;integratedSecurity=true;" ;
        Connection c = DriverManager.getConnection(connectionUrl);
        return c;
    }
    public Connexion() throws Exception
    {
        this.c = this.makeConnexion();
    }
}