package g55994.stib.view;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import g55994.stib.model.ErrorType;
import g55994.stib.model.dto.FavoriteDto;
import g55994.stib.model.dto.StationDto;
import g55994.stib.model.spp.Node;
import g55994.stib.presenter.Presenter;

/**
 *
 * @author Iuliana
 */
public class View {
    private final FxmlController ctrl;
    private FxmlControllerFavorites favoritesCtrl;
    private Stage favoritesStage;

    public View(Stage stage) throws IOException {
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("/fxml/StibView.fxml"));
        VBox root = loader.load();
        ctrl = loader.getController();
        
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("logo.png"))));
        stage.setTitle("STIBRIDE");
        stage.setScene(scene);
        stage.show();
        
        createFavoriteStage(stage);
    }

    private void createFavoriteStage(Stage stage) throws IOException {
        favoritesStage = new Stage();
        favoritesStage.initModality(Modality.APPLICATION_MODAL);
        favoritesStage.initOwner(stage);

        FXMLLoader loaderFavorites = new FXMLLoader(getClass().getResource("/fxml/Favorite.fxml"));
        Pane rootFavorites = loaderFavorites.load();
        Scene sceneFavorites = new Scene(rootFavorites);

        favoritesCtrl = loaderFavorites.getController();
        favoritesStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("logo.png"))));
        favoritesStage.setTitle("Manage favorites");
        favoritesStage.setScene(sceneFavorites);
    }

    public void addPresenter(Presenter presenter) {
        ctrl.setPresenter(presenter);
        favoritesCtrl.setPresenter(presenter);
    }

    public void initialize(ObservableList<StationDto> allStations, ObservableList<FavoriteDto> allFavorites) {
        ctrl.initialize(allStations);
        favoritesCtrl.initialize(allFavorites);
    }

    public void pathFound(List<Node> path) {
        ctrl.searchDone(path);
    }

    public void showFavoritesStage(ObservableList<FavoriteDto> allFavorites) {
       favoritesStage.show();
       favoritesCtrl.updateFavoritesTable(allFavorites);
    }

    public void updateFavorites(ObservableList<FavoriteDto> allFavorites) {
        favoritesCtrl.updateFavoritesTable(allFavorites); 
    } 

    public void showErrorMessage(ErrorType error) {
        if(error != ErrorType.NO_ERROR)
            ctrl.showWarning(error.getMessage());
    }

    public void closeFavoritesStage() {
        favoritesStage.close();
    }
}
