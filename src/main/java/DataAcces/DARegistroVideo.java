package DataAcces;

import Model.Video;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DARegistroVideo {
    public int annadirVideo(Video video)throws SQLException{
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String insert = "Insert into Video(nombre, categoria, fecha, descripcion, calificacion) values( ?, ?, ?, ?, ?)";

        // abre la conexion y la cierra despues de hacer el insert, por eso los recursos deben ser cerrables
        try (Connection connection = connectionManager.abrirConexion()) {
            try ( PreparedStatement statement = connection.prepareStatement(insert)) {
                statement.setString(1, video.getNombre());
                statement.setString(2, video.getCategoria());
                statement.setDate(3, Date.valueOf(video.getFecha()));
                statement.setString(4, video.getDescripcion());
                statement.setInt(5, video.getCalificacion());

                return statement.executeUpdate();

            }
        }
    }

}
