package edu.guilford;

import java.util.ArrayList;

import edu.guilford.applications.BaseApplication;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class MainPane extends VBox {
    private static final String LOGO_IMAGE_FILE = "file:chemistrymonkey/src/main/resources/edu/guilford/CM_LOGO.png";

    // Constructor
    public MainPane(ArrayList<BaseApplication> applicationList) {
        super();

        // Create logo image component
        ImageView imageView;
        try {
            imageView = new ImageView(new Image(LOGO_IMAGE_FILE));
            imageView.setFitWidth(USE_PREF_SIZE);
            imageView.setPreserveRatio(true);
        } catch (IllegalArgumentException e) {
            System.err.println("Error loading image: " + LOGO_IMAGE_FILE);
            imageView = new ImageView(); // Placeholder if image fails to load
        }

        // Create header label component
        Label header = new Label("Application Launcher");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Create topSection container
        VBox topSection = new VBox(10, imageView, header);
        topSection.setStyle("-fx-padding: 10; -fx-alignment: center;");

        // Create application containers
        TilePane tilePane = new TilePane();
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        tilePane.setPrefColumns(1);
        tilePane.setStyle("-fx-padding: 10;");
        for (BaseApplication baseApplication : applicationList) {
            tilePane.getChildren().add(baseApplication.getApplicationMenuBox());
        }

        // Add scroll bar
        ScrollPane scrollPane = new ScrollPane(tilePane);
        scrollPane.setFitToWidth(true);

        // Add components to MainPane
        getChildren().add(topSection);
        getChildren().add(scrollPane);

        // Main Layout
        setStyle("-fx-padding:10;");
    }
}
