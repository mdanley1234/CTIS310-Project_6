package edu.guilford.applications;

import edu.guilford.MonkeyLauncher;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * BaseApplication is an abstract class representing the structure of an application within
 * the Monkey Launcher framework. It handles the scene generation, menu display, and 
 * launching functionality of an individual application.
 */
public abstract class BaseApplication {

    /** The application scene */
    protected Scene applicationScene;

    /** Reference to the MonkeyLauncher for scene switching */
    protected MonkeyLauncher launcher;

    /** Scaling factors for the menu image size */
    private static final double MENU_IMAGE_WIDTH_FACTOR = 0.7;
    private static final double MENU_IMAGE_HEIGHT_FACTOR = 0.27;

    /** The menu box containing the application logo and launch button */
    private HBox applicationMenuBox;

    /**
     * Abstract method to set the location of the menu image for each application.
     * 
     * @return The file path of the menu image.
     */
    protected abstract String setMenuImageLocation();

    /**
     * Constructs a BaseApplication with the given MonkeyLauncher and initializes
     * the menu box and application scene.
     * 
     * @param launcher The MonkeyLauncher used to switch scenes.
     */
    public BaseApplication(MonkeyLauncher launcher) {
        this.launcher = launcher;
        generateMenuBox();
        generateApplication();
    }

    /**
     * Generates the application menu box, including the logo image and the launch button.
     * This method is responsible for setting the layout of the application in the main menu.
     */
    private void generateMenuBox() {
        // Create the application menu box (holds the image and launch button)
        applicationMenuBox = new HBox();
        applicationMenuBox.setAlignment(Pos.CENTER_LEFT);
        applicationMenuBox.setSpacing(15);

        // Create the logo image component
        ImageView imageView = createImageView(setMenuImageLocation(), 
                                              MonkeyLauncher.applicationWidth * MENU_IMAGE_WIDTH_FACTOR,
                                              MonkeyLauncher.applicationHeight * MENU_IMAGE_HEIGHT_FACTOR);

        // Create the launch button
        Button launchButton = createLaunchButton();

        // Add components to the menu box
        applicationMenuBox.getChildren().addAll(imageView, launchButton);

        // Button functionality: Launch the application when clicked
        launchButton.setOnAction(e -> launcher.switchScene(applicationScene));
    }

    /**
     * Creates an ImageView for the application logo.
     * 
     * @param imagePath The path to the image.
     * @param width The desired width of the image.
     * @param height The desired height of the image.
     * @return The created ImageView.
     */
    private ImageView createImageView(String imagePath, double width, double height) {
        ImageView imageView;
        try {
            imageView = new ImageView(new Image(imagePath));
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            imageView.setPreserveRatio(false);
        } catch (IllegalArgumentException e) {
            System.err.println("Error loading image: " + imagePath);
            imageView = new ImageView(); // Placeholder if image fails to load
        }
        return imageView;
    }

    /**
     * Creates the launch button with specific styling and hover effects.
     * 
     * @return The configured Button object.
     */
    private Button createLaunchButton() {
        Button launchButton = new Button("Launch\nApplication");
        launchButton.setPrefWidth(MonkeyLauncher.applicationWidth * (0.9 - MENU_IMAGE_WIDTH_FACTOR));
        launchButton.setPrefHeight(MonkeyLauncher.applicationHeight * MENU_IMAGE_HEIGHT_FACTOR);

        // Styling for the launch button
        launchButton.setStyle("-fx-font-size: 20px; "
                + "-fx-font-weight: bold; "
                + "-fx-font-family: 'Arial'; "
                + "-fx-text-fill: white; "
                + "-fx-background-color: #4CAF50; "
                + "-fx-background-radius: 20px; "
                + "-fx-padding: 10px 20px; "
                + "-fx-border-color: #388E3C; "
                + "-fx-border-width: 2px; "
                + "-fx-border-radius: 20px;");

        // Hover effect for the button
        launchButton.setOnMouseEntered(e -> launchButton.setStyle(
                "-fx-font-size: 20px; "
                + "-fx-font-weight: bold; "
                + "-fx-font-family: 'Arial'; "
                + "-fx-text-fill: white; "
                + "-fx-background-color: #45a049; "
                + "-fx-background-radius: 20px; "
                + "-fx-padding: 10px 20px; "
                + "-fx-border-color: #388E3C; "
                + "-fx-border-width: 2px; "
                + "-fx-border-radius: 20px;"));

        launchButton.setOnMouseExited(e -> launchButton.setStyle(
                "-fx-font-size: 20px; "
                + "-fx-font-weight: bold; "
                + "-fx-font-family: 'Arial'; "
                + "-fx-text-fill: white; "
                + "-fx-background-color: #4CAF50; "
                + "-fx-background-radius: 20px; "
                + "-fx-padding: 10px 20px; "
                + "-fx-border-color: #388E3C; "
                + "-fx-border-width: 2px; "
                + "-fx-border-radius: 20px;"));

        return launchButton;
    }

    /**
     * Generates the main application scene, including the back button and main content.
     * Sets the scene for this application.
     */
    private void generateApplication() {
        VBox applicationContainer = new VBox();

        // Create the header with the back button and logo
        HBox headerContainer = createHeaderContainer();

        // Add components to the application container
        applicationContainer.getChildren().addAll(headerContainer, getMainPane());

        // Set the scene
        applicationScene = new Scene(applicationContainer, MonkeyLauncher.applicationWidth, MonkeyLauncher.applicationHeight);
    }

    /**
     * Creates the header container with a back button and logo image.
     * 
     * @return The HBox containing the header.
     */
    private HBox createHeaderContainer() {
        HBox headerContainer = new HBox();
        headerContainer.setAlignment(Pos.TOP_LEFT);
        headerContainer.setPadding(new Insets(10, 15, 10, 15));
        headerContainer.setSpacing(MonkeyLauncher.applicationWidth * 0.11);
        headerContainer.setStyle("-fx-border-color: black; -fx-border-width: 6px;");

        // Create the back button
        Button backButton = createBackButton();

        // Create logo image for the header
        ImageView imageView = createImageView(setMenuImageLocation(), 
                                              MonkeyLauncher.applicationWidth * MENU_IMAGE_WIDTH_FACTOR * 0.7, 
                                              MonkeyLauncher.applicationHeight * MENU_IMAGE_HEIGHT_FACTOR * 0.7);

        // Add components to the header
        headerContainer.getChildren().addAll(backButton, imageView);

        return headerContainer;
    }

    /**
     * Creates the back button with specific styling and hover effects.
     * 
     * @return The configured Button object.
     */
    private Button createBackButton() {
        Button backButton = new Button("Return\nHome");
        backButton.setStyle("-fx-font-size: 18px; "
                + "-fx-font-weight: bold; "
                + "-fx-font-family: 'Arial'; "
                + "-fx-text-fill: white; "
                + "-fx-background-color: #f44336; "
                + "-fx-background-radius: 20px; "
                + "-fx-padding: 5px 15px; "
                + "-fx-border-color: #d32f2f; "
                + "-fx-border-width: 2px; "
                + "-fx-border-radius: 20px;");

        // Hover effect for the back button
        backButton.setOnMouseEntered(e -> backButton.setStyle(
                "-fx-font-size: 18px; "
                + "-fx-font-weight: bold; "
                + "-fx-font-family: 'Arial'; "
                + "-fx-text-fill: white; "
                + "-fx-background-color: #e53935; "
                + "-fx-background-radius: 20px; "
                + "-fx-padding: 5px 15px; "
                + "-fx-border-color: #d32f2f; "
                + "-fx-border-width: 2px; "
                + "-fx-border-radius: 20px;"));

        backButton.setOnMouseExited(e -> backButton.setStyle(
                "-fx-font-size: 18px; "
                + "-fx-font-weight: bold; "
                + "-fx-font-family: 'Arial'; "
                + "-fx-text-fill: white; "
                + "-fx-background-color: #f44336; "
                + "-fx-background-radius: 20px; "
                + "-fx-padding: 5px 15px; "
                + "-fx-border-color: #d32f2f; "
                + "-fx-border-width: 2px; "
                + "-fx-border-radius: 20px;"));

        // Back button functionality
        backButton.setOnAction(e -> launcher.goHome());

        return backButton;
    }

    /**
     * Abstract method for generating the main content pane of the application.
     * This must be implemented by subclasses to define the specific layout of the app.
     * 
     * @return A GridPane representing the main content of the application.
     */
    protected abstract GridPane getMainPane();

    /**
     * Returns the application menu box, which contains the application logo and launch button.
     * 
     * @return The HBox containing the application menu components.
     */
    public HBox getApplicationMenuBox() {
        return applicationMenuBox;
    }
}
