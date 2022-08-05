package dataaccess;

import model.ListaReproduccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Video;

public class DAListaReproduccion {
    public int addReproductionList (ListaReproduccion listaVideo) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String insert = "Insert into ListaVideos(nombre, enlaceImagen, idUsuario) values(?, ?, ?)";

        try(Connection connection = connectionManager.abrirConexion()){
            try (PreparedStatement statement = connection.prepareStatement(insert)){
                statement.setString(1, listaVideo.getNombre());
                statement.setString(2,listaVideo.getArchivoImagen());
                statement.setInt(3,listaVideo.getUserId());

                return statement.executeUpdate();

            }
        }
    }

    public ArrayList<ListaReproduccion> toListReproductionLists(int userId) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        ArrayList<ListaReproduccion> result = new ArrayList<>();
        String select = "Select nombre, enlaceImagen, idUsuario, id From ListaVideos WHERE idUsuario  = ?";

        try(Connection connection = connectionManager.abrirConexion()){
            try(PreparedStatement statement = connection.prepareStatement(select)){
                statement.setInt(1, userId);
                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()){
                    ListaReproduccion listVideos = new ListaReproduccion();
                    listVideos.setNombre(resultSet.getString("nombre"));
                    listVideos.setArchivoImagen(resultSet.getString("enlaceimagen"));
                    listVideos.setUserId(resultSet.getInt("idUsuario"));
                    listVideos.setId(resultSet.getInt("id"));
                    result.add(listVideos);

                }
                return result;
            }
        }

    }

    public int addVideoToPlayList(int idVideo, int idPlayList)throws SQLException{
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        String insert = "Insert into listaVideos_video (idListaVideos, idVideo ) values(?, ?)";
        try(Connection connection = connectionManager.abrirConexion()){
            try (PreparedStatement statement = connection.prepareStatement(insert)){
                statement.setInt(1, idPlayList);
                statement.setInt(2,idVideo);


                return statement.executeUpdate();

            }
        }
    }

    public ArrayList<Video> listaVideos(int idListaReproduccion) throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.obtenerInstancia();
        ArrayList<Video> result = new ArrayList<>();
        String innerJoin = "Select nombre, enlaceVideo From listaVideos_video as LV Inner Join Video as V On V.id = LV.idVideo Where LV.idListaVideos = ?";
        try(Connection connection = connectionManager.abrirConexion()){
            try (PreparedStatement statement = connection.prepareStatement(innerJoin)){
                statement.setInt(1, idListaReproduccion);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()){
                    Video video = new Video();
                    video.setNombre(resultSet.getString("nombre"));
                    video.setArchivo(resultSet.getString("enlaceVideo"));
                    result.add(video);
                }


                return result;

            }
        }
    }


}
