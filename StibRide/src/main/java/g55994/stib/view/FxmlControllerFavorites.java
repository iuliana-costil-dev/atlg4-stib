package g55994.stib.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import g55994.stib.model.RepositoryException;
import g55994.stib.model.dto.FavoriteDto;
import g55994.stib.model.dto.StationDto;
import g55994.stib.presenter.Presenter;
import java.io.IOException;
import javafx.scene.control.Alert;

/**
 *
 * @author Iuliana
 */
public class FxmlControllerFavorites {

    private Presenter presenter;

    @FXML
    private TableView favoritesTable;

    @FXML
    private TableColumn<FavoriteDto, String> columnName;

    @FXML
    private TableColumn<FavoriteDto, StationDto> columnOrigine;

    @FXML
    private TableColumn<FavoriteDto, StationDto> columnDestination;
        
    @FXML
    public void deleteButtonHandler() throws RepositoryException{
        FavoriteDto selectedFavorite = (FavoriteDto) favoritesTable.getSelectionModel().getSelectedItem();
        if (selectedFavorite != null) {
            presenter.removeFromFavorites(selectedFavorite.getKey());
        }
    }
    @FXML
    public void seeButtonHandler() throws RepositoryException, IOException{
        FavoriteDto selectedFavorite = (FavoriteDto) favoritesTable.getSelectionModel().getSelectedItem();
        if (selectedFavorite != null) {
            presenter.closeFavoritesStage();
            presenter.searchPath(selectedFavorite.getSource(), 
                    selectedFavorite.getDestination());
        }

    }
    public FxmlControllerFavorites() {
    }

    void initialize(ObservableList<FavoriteDto> allFavorites) {
        columnName.setCellValueFactory(new PropertyValueFactory<>("key"));
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnName.setOnEditCommit((TableColumn.CellEditEvent<FavoriteDto, String> t)
                -> {
            try {
                presenter.updateFavorite(t.getNewValue(),
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).getSource(),
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).getDestination());
            } catch (RepositoryException ex) {
                showWarning(ex.getMessage());
            }
            t.getTableView().refresh();
        }
        );

        columnOrigine.setCellValueFactory(new PropertyValueFactory<>("source"));
        columnDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        favoritesTable.setItems(allFavorites);
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    void updateFavoritesTable(ObservableList<FavoriteDto> allFavorites) {
        favoritesTable.setItems(allFavorites);
    }
    
    void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
