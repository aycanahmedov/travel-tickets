package bg.tuvarna.traveltickets.controller.base;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static bg.tuvarna.traveltickets.common.AppConfig.getPrimaryStage;

/**
 * Base class for each controller. Each fxml file should define the id of the {@link Parent} as 'root'
 * and the id of the {@link Button} that's responsible for exiting as 'exitButton'.
 */
public abstract class BaseController implements Initializable {

    double xOffset = 0;
    double yOffset = 0;

    @FXML
    protected Parent root;

    @FXML
    protected Button exitButton;

    @FXML
    protected void onExitButtonClicked(final MouseEvent event) {
        final Window window = ((Node) event.getTarget()).getScene().getWindow();
        window.fireEvent(new WindowEvent(getPrimaryStage(), WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        makeSceneDraggable();
    }

    public void makeSceneDraggable() {
        final Stage primaryStage = getPrimaryStage();

        root.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        root.setOnMouseDragged(e -> {
            primaryStage.setX(e.getScreenX() - xOffset);
            primaryStage.setY(e.getScreenY() - yOffset);
        });
    }

}
