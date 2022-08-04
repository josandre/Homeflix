package dataaccess;

import java.sql.*;

/**
 * Esta clase maneja la conexion a la base de datos
 */
public class ConnectionManager {
    private Connection connection;
    private static ConnectionManager instancia;

    /**
     * Este constructor es un patron de diseño llamado singleton que solo permite crear una instancia de este tipo, en toda la aplicacion
     */
    private ConnectionManager(){

    }

    /**
     * Esta funcicon crea la unica instancia de adminitracion de la conexion de la base de datos
     * @return la instancia de conexion
     */
    public static ConnectionManager obtenerInstancia(){

        if(instancia == null){
            instancia = new ConnectionManager();
        }

        return instancia;
    }

    /**
     * Esta funcion abre la conexion a la base de datos
     * @return la conexion a baase de datos
     * @throws SQLException
     */
    public Connection abrirConexion() throws SQLException{
        cerrarConexion();
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:33097/homeflix","root","JaAch060896");
        return this.connection;
    }

    /**
     * Esta funcion cierra la conexion a la base de datos
     * @throws SQLException
     */
    public void cerrarConexion() throws SQLException{
        if(this.connection != null && !this.connection.isClosed()){
            this.connection.close();
        }
    }


}
