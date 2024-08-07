package g55994.stib.model.repository;

import java.util.List;
import java.util.Map;
import g55994.stib.model.dto.StopDto;
import g55994.stib.model.RepositoryException;
import g55994.stib.model.dao.StopDao;

/**
 *
 * @author Iuliana
 */
public class StopRepository implements Repository<Map.Entry<Integer,Integer>, StopDto> {

    private final StopDao dao;

    public StopRepository() throws RepositoryException {
        dao = StopDao.getInstance();
    }

    StopRepository(StopDao dao) {
        this.dao = dao;
    }


    @Override
    public List<StopDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public StopDto get(Map.Entry<Integer,Integer> key) throws RepositoryException {
        StopDto refreshItem = dao.select(key);
        return refreshItem;
    }

    @Override
    public Map.Entry<Integer, Integer> add(StopDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Map.Entry<Integer, Integer> key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
