package edu.guilford;

import java.io.IOException;
import java.util.ArrayList;

import edu.guilford.applications.BaseApplication;
import edu.guilford.applications.EquationApplication;
import edu.guilford.applications.MoleApplication;
import edu.guilford.applications.TestApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * The main launcher class for the Chemistry Monkey JavaFX application.
 * Initializes the application window, sets dimensions based on screen size, 
 * and loads the main scene.
 */
public class MonkeyLauncher extends Application {

    private static Scene mainScene;
    
    /** Factor for determining application width relative to screen size. */
    private static final double WIDTH_FACTOR = 0.3; 
    
    /** Factor for determining application height relative to screen size. */
    private static final double HEIGHT_FACTOR = 0.5; 

    /** The calculated application width based on screen size. */
    public static int applicationWidth;

    /** The calculated application height based on screen size. */
    public static int applicationHeight;

    /** The main stage (window) for the application. */
    private Stage stage;

    /**
     * The entry point of the application.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Starts the JavaFX application by setting up the main window.
     * 
     * @param stage The primary stage for this application.
     * @throws IOException If there is an issue loading resources.
     */
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        
        // Set application title and icon
        stage.setTitle("Chemistry Monkey");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/edu/guilford/CM_LOGO.png")));

        // Extract screen size components
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // Set application dimensions
        applicationWidth = (int) (screenWidth * WIDTH_FACTOR);
        applicationHeight = (int) (screenHeight * HEIGHT_FACTOR);

        // Create and set the main scene
        MainPane mainPane = new MainPane(buildApplications());
        mainScene = new Scene(mainPane, applicationWidth, applicationHeight);
        stage.setScene(mainScene);
        stage.show();
    }

    /**
     * Builds a list of chemistry-related applications to be included in the main interface.
     * 
     * @return A list of {@link BaseApplication} instances.
     */
    private ArrayList<BaseApplication> buildApplications() {
        ArrayList<BaseApplication> applicationList = new ArrayList<>();

        applicationList.add(new MoleApplication(this));
        applicationList.add(new EquationApplication(this));
        applicationList.add(new TestApplication(this));

        return applicationList;
    }

    /**
     * Sets the root of the main scene to a new FXML layout.
     * 
     * @param fxml The name of the FXML file (without extension).
     * @throws IOException If the FXML file cannot be loaded.
     */
    static void setRoot(String fxml) throws IOException {
        mainScene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads an FXML file and returns its root node.
     * 
     * @param fxml The name of the FXML file (without extension).
     * @return The root node of the loaded FXML file.
     * @throws IOException If the FXML file cannot be loaded.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MonkeyLauncher.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Switches the application to a different scene.
     * 
     * @param scene The scene to be displayed.
     */
    public void switchScene(Scene scene) {
        stage.setScene(scene);
    }

    /**
     * Returns the application to the main scene.
     */
    public void goHome() {
        stage.setScene(mainScene);
    }
}
