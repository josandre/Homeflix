package dataaccess;

import modelo.Usuario;
import modelo.Video;

import java.sql.*;

public class DAUsuario {

    public int annadirUsuario(Usuario usuario) throws SQLException {
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

    public Usuario buscarUsuario(String contrasenna, String nombreUsuario) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String select = "Select nombre, apellido, archivoImagen, id FROM Usuario WHERE nombreUsuario = ? and contrasenna = ?";

        try (Connection connection = connectionManager.abrirConexion()) {
            try (PreparedStatement statement = connection.prepareStatement(select)) {
                statement.setString(1, nombreUsuario);
                statement.setString(2, contrasenna);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    String archivoImagen = resultSet.getString("archivoImagen");
                    int id = resultSet.getInt("id");
                    Usuario usuario = new Usuario();

                    usuario.setNombreUsuario(nombreUsuario);
                    usuario.setNombre(nombre);
                    usuario.setApellido(apellido);
                    usuario.setArchivoImagen(archivoImagen);
                    usuario.setContrasenna(contrasenna);
                    usuario.setId(id);

                    return usuario;
                }
                return null;
            }
        }
    }

    public boolean buscarNombreUsuario(String nombreUsuario) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String select = "Select id FROM Usuario WHERE nombreUsuario = ? ";

        try (Connection connection = connectionManager.abrirConexion()) {
            try (PreparedStatement statement = connection.prepareStatement(select)) {
                statement.setString(1, nombreUsuario);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return true;
                }
                return false;
            }
        }
    }

    public void modificarUsuario(Usuario usuarioActual) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String update = "UPDATE Usuario Set nombre = ?, apellido = ?, nombreUsuario = ?, contrasenna = ?, archivoImagen = ? Where id = ?";

        try (Connection connection = connectionManager.abrirConexion()) {
            try (PreparedStatement statement = connection.prepareStatement(update)) {
                statement.setString(1, usuarioActual.getNombre());
                statement.setString(2, usuarioActual.getApellido());
                statement.setString(3,usuarioActual.getNombreUsuario());
                statement.setString(4, usuarioActual.getContrasenna());
                statement.setString(5, usuarioActual.getArchivoImagen());
                statement.setInt(6, usuarioActual.getId());

                statement.executeUpdate();
            }
        }
    }

    public int borrarCuenta(int idUsuario) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String delete = "Delete from Usuario Where id = ?";

        try (Connection connection = connectionManager.abrirConexion()) {
            try (PreparedStatement statement = connection.prepareStatement(delete)) {
                statement.setInt(1, idUsuario);

                return statement.executeUpdate();
            }
        }
    }
}