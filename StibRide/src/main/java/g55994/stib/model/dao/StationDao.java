/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g55994.stib.model.dao;

import g55994.stib.model.dao.DBManager;
import g55994.stib.model.dao.Dao;
import g55994.stib.model.RepositoryException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import g55994.stib.model.dto.StationDto;

/**
 *
 * @author g55994
 */
public class StationDao implements Dao<Integer, StationDto> {

    private Connection connexion;

    private StationDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();

    }

    public static StationDao getInstance() throws RepositoryException {
        return StationDaoHolder.getInstance();
    }

    @Override
    public List<StationDto> selectAll() throws RepositoryException {
        String sql = "SELECT id,name FROM STATIONS";
        List<StationDto> dtos = new ArrayList<>();
        try ( Statement stmt = connexion.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                StationDto dto = new StationDto(rs.getInt(1), rs.getString(2));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public StationDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "SELECT id,name FROM STATIONS WHERE  id = ?";
        StationDto dto = null;
        try ( PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StationDto(rs.getInt(1), rs.getString(2));
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

    public List<StationDto> getFullStation(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }

        String sqlLineOrder = "SELECT id_line, id_order FROM STOPS WHERE id_station = ?";

        //line, order
        List<Integer> orderInLine = new ArrayList<>();
        try ( PreparedStatement pstmt = connexion.prepareStatement(sqlLineOrder)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                orderInLine.add(rs.getInt(1));
                orderInLine.add(rs.getInt(2));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

        List<StationDto> adj = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < (orderInLine.size() - 1); i = i + 2) {
            String sql = "SELECT id, name FROM STATIONS JOIN STOPS ON id = id_station "
                    + " WHERE id_line = ? and (id_order = ? + 1 or id_order = ? - 1)";

            StationDto dto = null;

            try ( PreparedStatement pstmt = connexion.prepareStatement(sql)) {
                pstmt.setInt(1, orderInLine.get(i));
                pstmt.setInt(2, orderInLine.get(i + 1));
                pstmt.setInt(3, orderInLine.get(i + 1));
                ResultSet rs = pstmt.executeQuery();

                String name = "";
                while (rs.next()) {
                    count++;
                    int keySQL = rs.getInt(1);
                    name = rs.getString(2);
                    StationDto station = adj.stream()
                            .filter(st -> st.getKey() == keySQL)
                            .findAny()
                            .orElse(null);
                    if(station==null)
                        adj.add(new StationDto(keySQL, name));
                }
            } catch (SQLException e) {
                throw new RepositoryException(e);
            }
        }
        if (count == 0) {
            return null;
        }
        return adj;
    }

    @Override
    public Integer insert(StationDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateName(StationDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class StationDaoHolder {

        private static StationDao getInstance() throws RepositoryException {
            return new StationDao();
        }
    }

}
