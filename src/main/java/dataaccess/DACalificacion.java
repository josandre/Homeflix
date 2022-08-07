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

    public void borrarCalificacion (int idCalificacion) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String delete = "Delete from Calificacion Where idCalificacion = ?";

        try (Connection connection = connectionManager.abrirConexion()) {
            try (PreparedStatement statement = connection.prepareStatement(delete)) {
                statement.setInt(1, idCalificacion);
                ResultSet resultSet = statement.executeQuery();
            }
        }
    }
}
