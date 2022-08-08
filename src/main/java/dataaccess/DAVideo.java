package dataaccess;

import model.Video;

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
        String insert = "Insert into Video(nombre, categoria, fecha, descripcion, calificacion, enlaceVideo, enlaceImagen, idUsuario) values( ?, ?, ?, ?, ?, ?, ?, ?)";


        try (Connection connection = connectionManager.abrirConexion()) {
            try ( PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, video.getNombre());
                statement.setString(2, video.getCategoria());
                statement.setDate(3, Date.valueOf(video.getFecha()));
                statement.setString(4, video.getDescripcion());
                statement.setInt(5, video.getCalificacion());
                statement.setString(6, video.getArchivo());
                statement.setString(7, video.getThumbnailVideo());
                statement.setInt(8, video.getUserId());

                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if(resultSet.next())
                {
                    return  resultSet.getInt(1);
                }


            }
        }
        return 0;
    }

    public ArrayList<Video> obtenerVideos(int idUser) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        ArrayList<Video> result = new ArrayList<>();
        String select = "Select nombre, descripcion, enlaceVideo, enlaceImagen, id, idUsuario, categoria  FROM Video WHERE idUsuario = ?";

        try(Connection connection = connectionManager.abrirConexion()){
            try (PreparedStatement statement = connection.prepareStatement(select)){
                statement.setInt(1, idUser);
                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()){
                    Video video = new Video();
                    video.setNombre(resultSet.getString("nombre"));
                    video.setDescripcion(resultSet.getString("descripcion"));
                    video.setThumbnailVideo(resultSet.getString("enlaceImagen"));
                    video.setUserId(resultSet.getInt("idUsuario"));
                    video.setId(resultSet.getInt("id"));
                    video.setArchivo(resultSet.getString("enlaceVideo"));
                    video.setCategoria(resultSet.getString("categoria"));
                    result.add(video);
                }
                return result;

            }
        }

    }

    public ArrayList<Video> buscarVideos(String criterio) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        ArrayList<Video> result = new ArrayList<>();
        String select = "Select nombre, descripcion, enlaceVideo, enlaceImagen, id, idUsuario, categoria FROM Video where descripcion like ? or Nombre like ? or categoria like ?" ;

        try(Connection connection = connectionManager.abrirConexion()){
            try (PreparedStatement statement = connection.prepareStatement(select)){
                statement.setString(1, "%" + criterio + "%");
                statement.setString(2, "%" + criterio + "%");
                statement.setString(3, "%" + criterio + "%");

                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()){
                    Video video = new Video();
                    video.setNombre(resultSet.getString("nombre"));
                    video.setDescripcion(resultSet.getString("descripcion"));
                    video.setThumbnailVideo(resultSet.getString("enlaceImagen"));
                    video.setUserId(resultSet.getInt("idUsuario"));
                    video.setId(resultSet.getInt("id"));
                    video.setArchivo(resultSet.getString("enlaceVideo"));
                    video.setCategoria(resultSet.getString("categoria"));
                    result.add(video);
                }
                return result;
            }
        }

    }

    public int borrarVideo(int idVideo)throws SQLException{
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String delete = "Delete from Video Where id = ?";

        try (Connection connection = connectionManager.abrirConexion()) {
            try ( PreparedStatement statement = connection.prepareStatement(delete)) {
                statement.setInt(1, idVideo);

                return statement.executeUpdate();

            }
        }
    }

    public void modificarVideo(Video video) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String update = "UPDATE Video Set nombre = ?, categoria = ?, descripcion = ?, enlaceImagen = ? Where id = ?";

        try (Connection connection = connectionManager.abrirConexion()) {
            try ( PreparedStatement statement = connection.prepareStatement(update)) {

                statement.setString(1, video.getNombre());
                statement.setString(2, video.getCategoria());
                statement.setString(3, video.getDescripcion());
                statement.setString(4, video.getThumbnailVideo());

                statement.setInt(5, video.getId());


                statement.executeUpdate();

            }
        }
    }
}
