package g55994.stib.model.repository;

import g55994.stib.model.RepositoryException;
import java.util.List;
import g55994.stib.model.dao.StationDao;
import g55994.stib.model.dto.StationDto;

/**
 *
 * @author g55994
 */
public class StationRepository implements Repository<Integer, StationDto> {

    private final StationDao dao;

    public StationRepository() throws RepositoryException {
        dao = StationDao.getInstance();
    }

    StationRepository(StationDao dao) {
        this.dao = dao;
    }

    @Override
    public List<StationDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public StationDto get(Integer key) throws RepositoryException {
        StationDto refreshItem = dao.select(key);
        return refreshItem;
    }

    public List<StationDto> getFullStation(Integer key) throws RepositoryException {
        return dao.getFullStation(key);
    }

    @Override
    public Integer add(StationDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void remove(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

