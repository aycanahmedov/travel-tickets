package bg.tuvarna.traveltickets.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;

/**
 * This enumeration holds information about the screens in the application. It provides getter method for screen's
 * scene and takes care for reloading the fxml files on language change.
 */
public enum AppScreens {

    LOGIN("/fxml/login.fxml"),
    HOME("/fxml/home.fxml");

    private static final Logger LOG = LogManager.getLogger(AppScreens.class);

    private final String fxmlPath;

    private Parent parent;
    private Scene scene;

    AppScreens(final String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public Scene getScene() {
        if (scene == null) {
            try {
                final Parent parent = new FXMLLoader(getClass().getResource(fxmlPath), AppConfig.getLangBundle()).load();
                this.parent = parent;
                scene = new Scene(parent);
            }
            catch (IOException exception) {
                LOG.error("Error loading " + this.toString() + " screen: ", exception);
            }
        }
        return scene;
    }

    static void reloadScreens() {
        Arrays.stream(values()).filter(scr -> scr.scene != null).forEach(AppScreens::reload);
    }

    void reload() {
        try {
            final Parent parent = new FXMLLoader(getClass().getResource(fxmlPath), AppConfig.getLangBundle()).load();
            this.parent.getScene().setRoot(parent);
            this.parent = parent;
        }
        catch (IOException exception) {
            LOG.error("Error loading " + this.toString() + " screen: ", exception);
        }
    }

    public void delete() {
        scene = null;
    }
}
