package DataAcces;

import Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAUsuario {

    public int annadirUsuario(Usuario usuario)throws SQLException{
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String insert = "Insert into Usuario(nombre, apellido, nombreUsuario, contrasenna, archivoImagen) values(?, ?, ?, ?, ?)";


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

    public Usuario buscarUsuario(String contrasenna, String nombreUsuario) throws SQLException{
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String select = "Select nombre, apellido, archivoImagen FROM Usuario WHERE nombreUsuario = ? and contrasenna = ?";

        try(Connection connection = connectionManager.abrirConexion()){
            try(PreparedStatement statement = connection.prepareStatement(select)){
                statement.setString(1, nombreUsuario);
                statement.setString(2, contrasenna);


                ResultSet resultSet = statement.executeQuery();

                if(resultSet.next()){
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    String archivoImagen = resultSet.getString("archivoImagen");
                    Usuario usuario = new Usuario();

                    usuario.setNombreUsuario(nombreUsuario);
                    usuario.setNombre(nombre);
                    usuario.setApellido(apellido);
                    usuario.setArchivoImagen(archivoImagen);

                    return usuario;
                }

                return null;
            }
        }
    }


}