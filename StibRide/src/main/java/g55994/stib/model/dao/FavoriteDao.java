/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g55994.stib.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import g55994.stib.model.RepositoryException;
import g55994.stib.model.dto.FavoriteDto;
import java.util.ArrayList;
import java.util.List;
import g55994.stib.model.dto.StationDto;

/**
 *
 * @author Iuliana
 */
public class FavoriteDao implements Dao<String, FavoriteDto> {
    private final Connection connexion;

    public FavoriteDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }

    public static FavoriteDao getInstance() throws RepositoryException {
        return FavoriteDao.FavoriteDaoHolder.getInstance();
    }

    @Override
    public List<FavoriteDto> selectAll() throws RepositoryException {
        String sql = "SELECT src.name src_name, dest.name dest_name, id_source, "
                + "id_destination, favorites.name favorite_name " +
                "FROM favorites JOIN stations src ON id_source = src.id JOIN "
                + "stations dest ON id_destination = dest.id";
        List<FavoriteDto> favorites = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                FavoriteDto dto = new FavoriteDto(rs.getString("favorite_name"),
                        new StationDto(rs.getInt("id_source"), rs.getString("src_name")),
                        new StationDto(rs.getInt("id_destination"), rs.getString("dest_name")));
                favorites.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return favorites;
    }

    @Override
    public FavoriteDto select(String key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Erreur avec les objets donnés");
        }
        String sql = "SELECT src.name src_name, dest.name dest_name, id_source, "
                + "id_destination, favorites.name favorite_name FROM " +
                "favorites JOIN stations src ON id_source = src.id JOIN stations "
                + "dest ON id_destination = dest.id WHERE favorite_name = ?";
        FavoriteDto favorite = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                favorite = new FavoriteDto(rs.getString("favorite_name"),
                        new StationDto(rs.getInt("id_source"), rs.getString("src_name")),
                        new StationDto(rs.getInt("id_destination"), rs.getString("dest_name")));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Record pas unique " + key);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return favorite;
    }

    @Override
    public String insert(FavoriteDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }
        Integer id = 0;
        String sql = "INSERT INTO favorites(id_source, id_destination, name) "
                + "values(?, ?, ?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, item.getSource().getKey());
            pstmt.setInt(2, item.getDestination().getKey());
            pstmt.setString(3, item.getKey());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return item.getKey();
    }

    @Override
    public void delete(String key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "DELETE FROM favorites WHERE name = ?";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, key);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public void updateName(FavoriteDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }
        String sql = "UPDATE favorites SET "
                + "name = ? where id_source = ? and id_destination = ?";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, item.getKey());
            pstmt.setInt(2, item.getSource().getKey());
            pstmt.setInt(3, item.getDestination().getKey());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    public FavoriteDto select(int origine, int destination) throws RepositoryException {
        if (origine < 0 || destination<0) {
            throw new RepositoryException("Erreur avec les objets donnés");
        }
        String sql = "SELECT src.name src_name, dest.name dest_name, id_source, "
                + "id_destination, favorites.name favorite_name FROM " +
                "favorites JOIN stations src ON id_source = src.id JOIN stations "
                + "dest ON id_destination = dest.id WHERE id_source = ? and id_destination = ?";
        FavoriteDto favorite = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, origine);
            pstmt.setInt(2, destination);
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                favorite = new FavoriteDto(rs.getString("favorite_name"),
                        new StationDto(rs.getInt("id_source"), rs.getString("src_name")),
                        new StationDto(rs.getInt("id_destination"), rs.getString("dest_name")));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Record pas unique " + origine + " " + destination);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return favorite;
    }

    private static class FavoriteDaoHolder {

        private static FavoriteDao getInstance() throws RepositoryException {
            return new FavoriteDao();
        }
    }

}
