package dataaccess;

import modelo.ListaReproduccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Video;

public class DAListaReproduccion {
    public int agregarListaReproduccion(ListaReproduccion listaVideo) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String insert = "Insert into ListaVideos(nombre, enlaceImagen, idUsuario) values(?, ?, ?)";

        try (Connection connection = connectionManager.abrirConexion()) {
            try (PreparedStatement statement = connection.prepareStatement(insert)) {
                statement.setString(1, listaVideo.getNombre());
                statement.setString(2, listaVideo.getArchivoImagen());
                statement.setInt(3, listaVideo.getIdUsuario());

                return statement.executeUpdate();
            }
        }
    }

    public ArrayList<ListaReproduccion> listarListasReproduccion(int idUsuario) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        ArrayList<ListaReproduccion> result = new ArrayList<>();
        String select = "Select nombre, enlaceImagen, idUsuario, id From ListaVideos WHERE idUsuario  = ?";

        try (Connection connection = connectionManager.abrirConexion()) {
            try (PreparedStatement statement = connection.prepareStatement(select)) {
                statement.setInt(1, idUsuario);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    ListaReproduccion listVideos = new ListaReproduccion();
                    listVideos.setNombre(resultSet.getString("nombre"));
                    listVideos.setArchivoImagen(resultSet.getString("enlaceimagen"));
                    listVideos.setIdUsuario(resultSet.getInt("idUsuario"));
                    listVideos.setId(resultSet.getInt("id"));
                    result.add(listVideos);
                }
                return result;
            }
        }
    }

    public int agregarVideoAPlaylist(int idVideo, int idPlayList) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String insert = "Insert into listaVideos_video (idListaVideos, idVideo ) values(?, ?)";
        try (Connection connection = connectionManager.abrirConexion()) {
            try (PreparedStatement statement = connection.prepareStatement(insert)) {
                statement.setInt(1, idPlayList);
                statement.setInt(2, idVideo);

                return statement.executeUpdate();
            }
        }
    }

    public ArrayList<Video> listaVideos(int idListaReproduccion) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        ArrayList<Video> result = new ArrayList<>();
        String innerJoin = "Select nombre, enlaceVideo, enlaceImagen, idVideo From listaVideos_video as LV Inner Join Video as V On V.id = LV.idVideo Where LV.idListaVideos = ?";
        try (Connection connection = connectionManager.abrirConexion()) {
            try (PreparedStatement statement = connection.prepareStatement(innerJoin)) {
                statement.setInt(1, idListaReproduccion);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Video video = new Video();
                    video.setNombre(resultSet.getString("nombre"));
                    video.setArchivo(resultSet.getString("enlaceVideo"));
                    video.setThumbnailVideo(resultSet.getString("enlaceImagen"));
                    video.setId(resultSet.getInt("idVideo"));
                    result.add(video);
                }
                return result;
            }
        }
    }

    public int borrarVideoDeLista(int idVideo) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String delete = "Delete from listaVideos_video Where idVideo = ?";

        try (Connection connection = connectionManager.abrirConexion()) {
            try (PreparedStatement statement = connection.prepareStatement(delete)) {
                statement.setInt(1, idVideo);

                return statement.executeUpdate();
            }
        }
    }

    public int borrarPlayListTablaIntermedia(int idPlayList)throws SQLException{
        ConnectionManager connectionMannager = ConnectionManager.obtenerInstancia();
        String delete = "Delete from listaVideos_video Where idListaVideos = ?";

        try(Connection connection = connectionMannager.abrirConexion()){
            try(PreparedStatement statement = connection.prepareStatement(delete)){
                statement.setInt(1, idPlayList);

                return  statement.executeUpdate();
            }
        }
    }

    public int borrarListaVideos(int idPlayList)throws SQLException{
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String delete = "Delete from ListaVideos Where id = ? ";
        try(Connection connection = connectionManager.abrirConexion()){
            try(PreparedStatement statement = connection.prepareStatement(delete)){
                statement.setInt(1,idPlayList);

                return  statement.executeUpdate();
            }
        }
    }


    public int borrarVideoEnPlayList(int idVideo)throws SQLException{
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String delete = "Delete from listaVideos_video Where idVideo = ? ";
        try(Connection connection = connectionManager.abrirConexion()){
            try(PreparedStatement statement = connection.prepareStatement(delete)){
                statement.setInt(1,idVideo);

                return  statement.executeUpdate();
            }
        }
    }

    public void modificarPlayLis(ListaReproduccion playlistActual) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String update = "UPDATE ListaVideos Set nombre = ?, enlaceImagen = ? Where id = ?";

        try (Connection connection = connectionManager.abrirConexion()) {
            try (PreparedStatement statement = connection.prepareStatement(update)) {
                statement.setString(1, playlistActual.getNombre());
                statement.setString(2, playlistActual.getArchivoImagen());
                statement.setInt(3, playlistActual.getId());

                statement.executeUpdate();
            }
        }
    }


}
