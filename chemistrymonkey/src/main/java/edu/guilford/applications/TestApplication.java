package edu.guilford.applications;

import edu.guilford.MonkeyLauncher;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * The TestApplication class provides a simple graphical user interface (GUI) 
 * that displays a label and an image. It extends the BaseApplication class 
 * to inherit the basic functionality of the MonkeyLauncher application.
 */
public class TestApplication extends BaseApplication {

    /**
     * Constructor for the TestApplication.
     * 
     * @param launcher The MonkeyLauncher instance, used to launch the application.
     */
    public TestApplication(MonkeyLauncher launcher) {
        super(launcher);
    }

    /**
     * Sets the location of the menu image for the application.
     *
     * @return A string representing the file path to the menu image.
     */
    @Override
    protected String setMenuImageLocation() {
        return "file:chemistrymonkey/src/main/resources/edu/guilford/TestApplicationPNG.png";
    }

    /**
     * Constructs the main user interface of the TestApplication, including a label
     * and an image. The label displays the title "TEST APPLICATION", and the image 
     * is loaded from a file and displayed with a fixed width while preserving its aspect ratio.
     *
     * @return The GridPane layout containing the label and image.
     */
    @Override
    protected GridPane getMainPane() {
        // Create a new GridPane layout for the application
        GridPane testPane = new GridPane();
        testPane.setAlignment(Pos.CENTER);
        testPane.setPadding(new Insets(10));
        testPane.setVgap(20); // Adds vertical spacing between label and image

        // Create a label with the text "TEST APPLICATION"
        Label testLabel = new Label("TEST APPLICATION");
        testLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        testLabel.setAlignment(Pos.CENTER);
        GridPane.setHalignment(testLabel, HPos.CENTER); // Centers the label horizontally
        
        // Load an image and create an ImageView to display it
        Image image = new Image("file:chemistrymonkey/src/main/resources/edu/guilford/warningSignPNG.png"); // Replace with actual image path
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(400); // Sets the width of the image (doubles the original size)
        imageView.setPreserveRatio(true); // Preserves the aspect ratio of the image

        // Add the label and image to the layout
        testPane.add(testLabel, 0, 0);
        testPane.add(imageView, 0, 1);

        // Return the GridPane containing the UI elements
        return testPane;
    }

}