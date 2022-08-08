package dataaccess;

import model.Calificacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DACalificacion {

    public int guardarCalificacion (Calificacion calificacion) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String insert = "Insert into Calificacion(idVideo, idUsuario, estado) values (?, ?, ?)";

        try (Connection connection = connectionManager.abrirConexion()) {
            try (PreparedStatement statement = connection.prepareStatement(insert)) {
                statement.setInt(1, calificacion.getIdVideo());
                statement.setInt(2, calificacion.getIdUsuario());
                statement.setBoolean(3, calificacion.isEstado());
                return statement.executeUpdate();
            }
        }
    }

    public void borrarCalificacion (int idVideo, int idUsuario) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String delete = "Delete from Calificacion Where idVideo = ? and idUsuario = ?";

        try (Connection connection = connectionManager.abrirConexion()) {
            try (PreparedStatement statement = connection.prepareStatement(delete)) {
                statement.setInt(1, idVideo);
                statement.setInt(2, idUsuario);

                statement.executeUpdate();
            }
        }
    }

    public Calificacion obtenerCalificacionActual (int idVideo, int idUsuario) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String select = "Select idCalificacion, estado From Calificacion Where idVideo = ? and idUsuario = ?";

        try (Connection connection = connectionManager.abrirConexion()) {
            try (PreparedStatement statement = connection.prepareStatement(select)) {
                statement.setInt(1, idVideo);
                statement.setInt(2, idUsuario);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int idCalificacion = resultSet.getInt("idCalificacion");
                    boolean estado = resultSet.getBoolean("estado");

                    Calificacion calificacion = new Calificacion();
                    calificacion.setIdCalificacion(idCalificacion);
                    calificacion.setEstado(estado);

                    return calificacion;
                }
            }
        }
        return null;
    }

    public int borrarVideo(int idVideo)throws SQLException{
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String delete = "Delete from Calificacion Where idVideo = ?";

        try (Connection connection = connectionManager.abrirConexion()) {
            try ( PreparedStatement statement = connection.prepareStatement(delete)) {
                statement.setInt(1, idVideo);

                return statement.executeUpdate();

            }
        }
    }
}
