package g55994.stib.view;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.SearchableComboBox;
import g55994.stib.model.RepositoryException;
import g55994.stib.model.dto.StationDto;
import g55994.stib.model.spp.Node;
import g55994.stib.presenter.Presenter;

/**
 *
 * @author g55994
 */
public class FxmlController {

    @FXML
    private Button searchBtn;

    @FXML
    private Button addButton;

    @FXML
    private TextField trajectoryName;

    @FXML
    private TableView tableStations;

    @FXML
    private SearchableComboBox origineCB;

    @FXML
    private SearchableComboBox destinationCB;

    @FXML
    private TableColumn<Node, String> columnName;

    @FXML
    private TableColumn<Node, List<Integer>> columnLine;


    private ObservableList<Node> pathList;
    private Presenter presenter;

    @FXML
    private void search(ActionEvent event) throws RepositoryException, IOException {
        searchBtn.setDisable(true);
        presenter.searchPath((StationDto)origineCB.getValue(), (StationDto)destinationCB.getValue());
    }

    @FXML
    private void addToFavorites() throws RepositoryException, IOException {
        String name = trajectoryName.getText();
        presenter.addToFavorites(name, (StationDto) origineCB.getValue(), (StationDto) destinationCB.getValue());
        trajectoryName.setText("");
    }

    @FXML
    private void showManageFavorites() throws RepositoryException, IOException {
        presenter.showFavoritesStage();
    }

    public FxmlController() {
    }

    public void initialize(ObservableList<StationDto> stationsList) {
        origineCB.setItems(stationsList);
        origineCB.getSelectionModel().selectFirst();
        destinationCB.setItems(stationsList);
        destinationCB.getSelectionModel().selectFirst();
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnLine.setCellValueFactory(new PropertyValueFactory<>("lines"));

        pathList = FXCollections.observableArrayList();
        tableStations.setItems(pathList);
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void searchDone(List<Node> list) {
        searchBtn.setDisable(false);
        pathList.clear();
        pathList.addAll(list);
    }

    void showWarning(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
