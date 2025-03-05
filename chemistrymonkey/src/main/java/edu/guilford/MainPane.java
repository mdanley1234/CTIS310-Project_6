package edu.guilford;

import java.util.ArrayList;

import edu.guilford.applications.BaseApplication;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The MainPane class serves as the primary container for the Chemistry Monkey application.
 * It displays a header with a logo and an application launcher with scrollable tiles.
 */
public class MainPane extends VBox {

    /** File path to the application logo image. */
    private static final String LOGO_IMAGE_FILE = "file:chemistrymonkey/src/main/resources/edu/guilford/CM_LOGO.png";

    /**
     * Constructs the main interface for the application, including a logo, header,
     * and a scrollable list of available applications.
     *
     * @param applicationList The list of applications to be displayed in the launcher.
     */
    public MainPane(ArrayList<BaseApplication> applicationList) {
        super();

        // Create logo image component with shadow effect
        ImageView imageView;
        try {
            imageView = new ImageView(new Image(LOGO_IMAGE_FILE));
            imageView.setFitWidth(USE_PREF_SIZE);
            imageView.setPreserveRatio(true);
            imageView.setEffect(new DropShadow(10, Color.BLACK)); // Add shadow to the logo
        } catch (IllegalArgumentException e) {
            System.err.println("Error loading image: " + LOGO_IMAGE_FILE);
            imageView = new ImageView(); // Placeholder if image fails to load
        }

        // Create header label with a clean, bold look
        Label header = new Label("Launch Application");
        header.setFont(new Font("Arial", 36));
        header.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;"); // Dark text color

        // Create top section with logo and header
        VBox topSection = new VBox(15, imageView, header);
        topSection.setStyle("-fx-padding: 20; -fx-alignment: center;");
        
        // Add shadow to the topSection to create a soft floating effect
        topSection.setEffect(new DropShadow(15, Color.GRAY));

        // Create application container with cleaner spacing
        TilePane tilePane = new TilePane();
        tilePane.setHgap(15);
        tilePane.setVgap(15);
        tilePane.setPrefColumns(1);
        tilePane.setStyle("-fx-padding: 20;");

        // Populate application tiles
        for (BaseApplication baseApplication : applicationList) {
            tilePane.getChildren().add(baseApplication.getApplicationMenuBox());
        }

        // Create a scrollable pane for applications with a subtle border and rounded corners
        ScrollPane scrollPane = new ScrollPane(tilePane);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: #ecf0f1; -fx-border-radius: 10px; -fx-border-color: #bdc3c7;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // Add components to MainPane
        getChildren().addAll(topSection, scrollPane);

        // Apply overall layout styling for cleaner appearance
        setStyle("-fx-padding: 20;");
    }
}
