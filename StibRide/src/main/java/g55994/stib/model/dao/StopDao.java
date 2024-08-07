/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g55994.stib.model.dao;

import g55994.stib.model.dao.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import g55994.stib.model.dto.StopDto;
import g55994.stib.model.RepositoryException;
import g55994.stib.model.dao.Dao;
import g55994.stib.model.dto.StopDto;

/**
 *
 * @author Iuliana
 */
public class StopDao implements Dao<Map.Entry<Integer,Integer>, StopDto> {

    private Connection connexion;

    private StopDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();

    }

    public static StopDao getInstance() throws RepositoryException {
        return StopDaoHolder.getInstance();
    }

    @Override
    public List<StopDto> selectAll() throws RepositoryException {
        String sql = "SELECT id_line, id_station, id_order FROM STOPS";
        List<StopDto> dtos = new ArrayList<>();
        try ( Statement stmt = connexion.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                StopDto dto = new StopDto(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public StopDto select(Map.Entry<Integer,Integer> key ) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "SELECT id_line, id_station, id_order FROM STOPS "
                + "WHERE  id_line = ? and id_station = ?";
        StopDto dto = null;
        try ( PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key.getKey());
            pstmt.setInt(2, key.getValue());
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StopDto(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Record pas unique " + key);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    @Override
    public Map.Entry<Integer, Integer> insert(StopDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Map.Entry<Integer, Integer> key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateName(StopDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class StopDaoHolder {

        private static StopDao getInstance() throws RepositoryException {
            return new StopDao();
        }
    }

}

