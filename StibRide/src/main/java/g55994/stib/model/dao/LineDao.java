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
import java.util.ArrayList;
import java.util.List;
import g55994.stib.model.dto.LineDto;
import g55994.stib.model.RepositoryException;
import g55994.stib.model.dao.Dao;
import g55994.stib.model.dto.LineDto;

/**
 *
 * @author Iuliana
 */
public class LineDao implements Dao<Integer, LineDto> {

    private Connection connexion;

    private LineDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();

    }

    public static LineDao getInstance() throws RepositoryException {
        return LineDaoHolder.getInstance();
    }

    @Override
    public List<LineDto> selectAll() throws RepositoryException {
        String sql = "SELECT id FROM LINES";
        List<LineDto> dtos = new ArrayList<>();
        try ( Statement stmt = connexion.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                LineDto dto = new LineDto(rs.getInt(1));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }
    
    public List<LineDto> selectAllForStation(int station) throws RepositoryException {
//        String sqlLineOrder = "SELECT id_line, id_order FROM STOPS WHERE id_station = ?";
//
//        //line, order
//        List<Integer> orderInLine = new ArrayList<>();
//        try ( PreparedStatement pstmt = connexion.prepareStatement(sqlLineOrder)) {
//            pstmt.setInt(1, key);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                orderInLine.add(rs.getInt(1));
//                orderInLine.add(rs.getInt(2));
//            }
//        } catch (SQLException e) {
//            throw new RepositoryException(e);
//        }

        
        String sql = "SELECT id FROM LINES join stops on id_line = id and id_station=?";
        List<LineDto> dtos = new ArrayList<>();
        try ( PreparedStatement pstmt = connexion.prepareStatement(sql);  ) {
            pstmt.setInt(1, station); 
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                LineDto dto = new LineDto(rs.getInt(1));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }


    @Override
    public LineDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "SELECT id FROM LINES WHERE  id = ?";
        LineDto dto = null;
        try ( PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new LineDto(rs.getInt(1));
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
    public Integer insert(LineDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateName(LineDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class LineDaoHolder {

        private static LineDao getInstance() throws RepositoryException {
            return new LineDao();
        }
    }
}
