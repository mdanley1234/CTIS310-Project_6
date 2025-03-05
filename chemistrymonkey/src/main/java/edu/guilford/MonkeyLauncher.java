package edu.guilford;

import java.io.IOException;
import java.util.ArrayList;

import edu.guilford.applications.BaseApplication;
import edu.guilford.applications.TestApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class MonkeyLauncher extends Application {

    private static Scene mainScene;
    private static final double WIDTH_FACTOR = 0.3; // Application sizing factors
    private static final double HEIGHT_FACTOR = 0.5; // Application sizing factors

    // Application dimensions
    public static int applicationWidth;
    public static int applicationHeight;

    // Stage window
    private Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        // Set stage object
        this.stage = stage;

        // Extract screen size components
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // Set dimension sizing
        applicationWidth = (int) (screenWidth * WIDTH_FACTOR);
        applicationHeight = (int) (screenHeight * HEIGHT_FACTOR);

        MainPane mainPane = new MainPane(buildApplications());
        mainScene = new Scene(mainPane, applicationWidth, applicationHeight);
        stage.setScene(mainScene);
        stage.show();
    }

    // Build Chemistry Applications
    private ArrayList<BaseApplication> buildApplications() {
        ArrayList<BaseApplication> applicationList = new ArrayList<>();

        // TODO: Add applications
        applicationList.add(new TestApplication(this));

        return applicationList;

    }

    static void setRoot(String fxml) throws IOException {
        mainScene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MonkeyLauncher.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    // Scene switching methods
    public void switchScene(Scene scene) {
        stage.setScene(scene);
    }

    public void goHome() {
        stage.setScene(mainScene);
    }

}