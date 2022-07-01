package DataAcces;

import java.sql.*;

public class ConnectionManager {
    private Connection connection;
    private static ConnectionManager instancia;

    private ConnectionManager(){

    }

    public static ConnectionManager obtenerInstancia(){

        if(instancia == null){
            instancia = new ConnectionManager();
        }

        return instancia;
    }

    public Connection abrirConexion() throws SQLException{
        cerrarConexion();
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:33097/homeFlix","appUser","appUser");
        return this.connection;
    }

    public void cerrarConexion() throws SQLException{
        if(this.connection != null && !this.connection.isClosed()){
            this.connection.close();
        }
    }


}
