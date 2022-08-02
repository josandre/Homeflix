package DataAcces;

import Controller.BL;
import Model.Video;

import java.sql.*;
import java.util.ArrayList;

/**
 * Esta clase se conecta con la base de datos y permite insertar, borrar, seleccionar y modificar los datos del video
 */
public class DAVideo {



    /**
     * @param video
     * @return actualizacion de la base de datos
     * @throws SQLException
     */
    public int annadirVideo(Video video)throws SQLException{
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String insert = "Insert into Video(nombre, categoria, fecha, descripcion, calificacion, enlace, enlaceImagen, idUsuario) values( ?, ?, ?, ?, ?, ?, ?, ?)";


        try (Connection connection = connectionManager.abrirConexion()) {
            try ( PreparedStatement statement = connection.prepareStatement(insert)) {
                statement.setString(1, video.getNombre());
                statement.setString(2, video.getCategoria());
                statement.setDate(3, Date.valueOf(video.getFecha()));
                statement.setString(4, video.getDescripcion());
                statement.setInt(5, video.getCalificacion());
                statement.setString(6, video.getArchivo());
                statement.setString(7, video.getThumbnailVideo());
                statement.setInt(8, video.getUserId());
                return statement.executeUpdate();


            }
        }
    }

    public ArrayList<Video> obtenerVideos(int idUser) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        ArrayList<Video> result = new ArrayList<>();
        String select = "Select Nombre, Descripcion, Enlace, enlaceImagen, idUsuario, id  FROM Video WHERE idUsuario = ?";

        try(Connection connection = connectionManager.abrirConexion()){
            try (PreparedStatement statement = connection.prepareStatement(select)){
                statement.setInt(1, idUser);
                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()){
                    Video video = new Video();
                    video.setNombre(resultSet.getString("Nombre"));
                    video.setDescripcion(resultSet.getString("Descripcion"));
                    video.setThumbnailVideo(resultSet.getString("enlaceImagen"));
                    video.setUserId(resultSet.getInt("idUsuario"));
                    video.setId(resultSet.getInt("id"));
                    video.setArchivo(resultSet.getString("Enlace"));
                    result.add(video);
                }
                return result;

            }
        }

    }

    public ArrayList<Video> buscarVideos(String criterio) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        ArrayList<Video> result = new ArrayList<>();
        String select = "Select Nombre, Descripcion FROM Video where Descripcion like ? or Nombre like ?";

        try(Connection connection = connectionManager.abrirConexion()){
            try (PreparedStatement statement = connection.prepareStatement(select)){
                statement.setString(1, "%" + criterio + "%");
                statement.setString(2, "%" + criterio + "%");

                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()){
                    Video video = new Video();
                    video.setNombre(resultSet.getString("Nombre"));
                    video.setDescripcion(resultSet.getString("Descripcion"));
                    result.add(video);
                }
                return result;
            }
        }

    }
}
