package DataAcces;

import Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAUsuario {

    public int annadirUsuario(Usuario usuario)throws SQLException{
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String insert = "Insert into Usuario(nombre, apellido, nombreUsuario, contrasenna, archivoImagen) values(?, ?, ?, ?, ?)";

        // abre la conexion y la cierra despues de hacer el insert, por eso los recursos deben ser cerrables
        try (Connection connection = connectionManager.abrirConexion()) {
            try (PreparedStatement statement = connection.prepareStatement(insert)) {
                statement.setString(1, usuario.getNombre());
                statement.setString(2, usuario.getApellido());
                statement.setString(3, usuario.getNombreUsuario());
                statement.setString(4, usuario.getContrasenna());
                statement.setString(5, usuario.getArchivoImagen());

                return statement.executeUpdate();
            }
        }
    }
}