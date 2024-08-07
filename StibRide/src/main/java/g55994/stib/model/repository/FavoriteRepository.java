package g55994.stib.model.repository;

import java.util.List;
import g55994.stib.model.RepositoryException;
import g55994.stib.model.dao.FavoriteDao;
import g55994.stib.model.dto.FavoriteDto;

/**
 *
 * @author Iuliana
 */
public class FavoriteRepository implements Repository<String, FavoriteDto> {

    private final FavoriteDao dao;

    public FavoriteRepository() throws RepositoryException {
        dao = FavoriteDao.getInstance();
    }

    public FavoriteRepository(FavoriteDao dao) {
        this.dao = dao;
    }

    @Override
    public String add(FavoriteDto item) throws RepositoryException {
        if (item.getKey().equals("")) {
            throw new RepositoryException("The name cannot be empty");
        }
        return dao.insert(item);
    }

    public void updateName(FavoriteDto item) throws RepositoryException {
        if (item.getKey().equals("")) {
            throw new RepositoryException("The name cannot be empty");
        }
        dao.updateName(item);
    }

    @Override
    public void remove(String key) throws RepositoryException {
        dao.delete(key);
    }

    @Override
    public List<FavoriteDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public FavoriteDto get(String key) throws RepositoryException {
        return dao.select(key);
    }
    
    public FavoriteDto getByStations(int origine, int destination) throws RepositoryException{
        return dao.select(origine,destination);
    }
}