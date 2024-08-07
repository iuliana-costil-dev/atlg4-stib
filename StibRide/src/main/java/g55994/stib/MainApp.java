package g55994.stib;



import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import g55994.stib.model.Model;
import g55994.stib.presenter.Presenter;
import g55994.stib.view.View;

/**
 *
 * @author g55994
 */
public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Model model = new Model();
        View view = new View(stage);
        Presenter presenter = new Presenter(model, view);
        
        presenter.initialize();
        view.addPresenter(presenter);
        model.addObserver(presenter);

    }
}
