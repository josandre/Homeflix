package dataaccess;

import javafx.scene.control.Alert;
import model.Usuario;

import java.sql.*;

public class DAUsuario {

    public void annadirUsuario(Usuario usuario) {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psVerificacirUsuarioExiste = null;
        ResultSet resultSet = null;
        String insert = "INSERT INTO Usuario(nombre, apellido, nombreUsuario, contrasenna, archivoImagen) VALUES (?, ?, ?, ?, ?)";


        try {
            connection = connectionManager.abrirConexion();
            psVerificacirUsuarioExiste = connection.prepareStatement("SELECT * FROM Usuario WHERE nombreUsuario = ?");
            psVerificacirUsuarioExiste.setString(1, usuario.getNombreUsuario());
            resultSet = psVerificacirUsuarioExiste.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("nombre de usuario ingresado ya existe");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No puede utilizar este nombre de usuario");
                alert.showAndWait();
            } else {
                psInsert = connection.prepareStatement(insert);
                psInsert.setString(1, usuario.getNombre());
                psInsert.setString(2, usuario.getApellido());
                psInsert.setString(3, usuario.getNombreUsuario());
                psInsert.setString(4, usuario.getContrasenna());
                psInsert.setString(5, usuario.getArchivoImagen());
                psInsert.executeUpdate();
                }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psVerificacirUsuarioExiste != null) {
                try {
                    psVerificacirUsuarioExiste.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public Usuario buscarUsuario(String contrasenna, String nombreUsuario) throws SQLException{
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String select = "Select nombre, apellido, archivoImagen, id FROM Usuario WHERE nombreUsuario = ? and contrasenna = ?";

        try(Connection connection = connectionManager.abrirConexion()){
            try(PreparedStatement statement = connection.prepareStatement(select)){
                statement.setString(1, nombreUsuario);
                statement.setString(2, contrasenna);


                ResultSet resultSet = statement.executeQuery();

                if(resultSet.next()){
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    String archivoImagen = resultSet.getString("archivoImagen");
                    int id  = resultSet.getInt("id");
                    Usuario usuario = new Usuario();

                    usuario.setNombreUsuario(nombreUsuario);
                    usuario.setNombre(nombre);
                    usuario.setApellido(apellido);
                    usuario.setArchivoImagen(archivoImagen);
                    usuario.setId(id);

                    return usuario;
                }

                return null;
            }
        }
    }

    public boolean buscarUserName(String userName) throws SQLException{
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String select = "Select id FROM Usuario WHERE nombreUsuario = ? ";

        try(Connection connection = connectionManager.abrirConexion()){
            try(PreparedStatement statement = connection.prepareStatement(select)){
                statement.setString(1, userName);

                ResultSet resultSet = statement.executeQuery();

                if(resultSet.next()){
                    return true;

                }

                return false;
            }
        }
    }





}