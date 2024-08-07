package g55994.stib.presenter;

import java.io.IOException;
import g55994.stib.model.Model;
import g55994.stib.model.RepositoryException;
import g55994.stib.model.dto.StationDto;
import g55994.stib.utils.Observable;
import g55994.stib.utils.Observer;
import g55994.stib.view.View;

/**
 *
 * @author g55994
 */
public class Presenter implements Observer {

    private Model model;
    private View view;

    public Presenter(Model model, View view) throws RepositoryException, IOException {
        this.model = model;
        this.view = view;
        model.addObserver(this);
        //this.view.populateComboList(names);
    }

    public void initialize() {
        view.initialize(model.getStationsList(), model.getAllFavorites());
    }

    @Override
    public void update(Observable observable) {
        view.pathFound(model.getPath());
        view.updateFavorites(model.getAllFavorites());
        view.showErrorMessage(model.getError());
    }

    public void searchPath(StationDto source, StationDto destination) throws RepositoryException, IOException {
        model.searchPath(source, destination);
    }

    public void addToFavorites(String name, StationDto origine, StationDto destination) throws RepositoryException {
        model.addFavorite(name, origine, destination);
    }
    
    public void removeFromFavorites(String name) throws RepositoryException {
        model.removeFavorite(name);
    }

    public void showFavoritesStage() {
        view.showFavoritesStage(model.getAllFavorites());
    }

    public void closeFavoritesStage() {
        view.closeFavoritesStage();
    }

    public void updateFavorite(String newName, StationDto source, StationDto destination) throws RepositoryException {
        model.updateFavorite(newName, source, destination);
    }
}
