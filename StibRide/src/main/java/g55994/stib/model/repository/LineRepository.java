package g55994.stib.model.repository;

import java.util.List;
import g55994.stib.model.dto.LineDto;
import g55994.stib.model.RepositoryException;
import g55994.stib.model.dao.LineDao;

/**
 *
 * @author Iuliana
 */
public class LineRepository implements Repository<Integer, LineDto> {

    private final LineDao dao;

    public LineRepository() throws RepositoryException {
        dao = LineDao.getInstance();
    }

    LineRepository(LineDao dao) {
        this.dao = dao;
    }


    @Override
    public List<LineDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public LineDto get(Integer key) throws RepositoryException {
        LineDto refreshItem = dao.select(key);
        return refreshItem;
    }
    
    public List<LineDto> selectAllForStation(int station) throws RepositoryException {
        var lines = dao.selectAllForStation(station);
        return lines;
    }

    @Override
    public Integer add(LineDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

