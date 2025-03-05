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

public abstract class BaseApplication {

    // Application scene components
    protected Scene applicationScene;

    // Monkey launcher for scene switching
    protected MonkeyLauncher launcher;

    // Scaling factors
    private static double menuImageWidthFactor = 0.7;
    private static double menuImageHeightFactor = 0.2;

    // Menu box components
    private HBox applicationMenuBox;
    protected abstract String setMenuImageLocation();

    public BaseApplication(MonkeyLauncher launcher) {
        this.launcher = launcher;
        generateMenuBox();
        generateApplication();
    }

    // Generate applicationMenuBox
    private void generateMenuBox() {
        // Create applicationMenuBox (TODO: ADD BUTTON AND FUNCTIONALITY)

        applicationMenuBox = new HBox();
        applicationMenuBox.setAlignment(Pos.CENTER_LEFT);
        applicationMenuBox.setSpacing(15);

        // Create logo image component
        ImageView imageView;
        try {
            imageView = new ImageView(new Image(setMenuImageLocation()));
            imageView.setFitHeight(MonkeyLauncher.applicationHeight * menuImageHeightFactor); // TODO
            imageView.setFitWidth(MonkeyLauncher.applicationWidth * menuImageWidthFactor); // TODO
            imageView.setPreserveRatio(false);
        } catch (IllegalArgumentException e) {
            System.err.println("Error loading image: " + setMenuImageLocation());
            imageView = new ImageView(); // Placeholder if image fails to load
        }

        // Create launch button
        Button launchButton = new Button("Launch\nApplication");
        launchButton.setPrefWidth(MonkeyLauncher.applicationWidth * (0.9 - menuImageWidthFactor));
        launchButton.setPrefHeight(MonkeyLauncher.applicationHeight * menuImageHeightFactor);

        // Button formatting
                // Customize button appearance
                launchButton.setStyle(
                    "-fx-font-size: 16px; " +           // Font size
                    "-fx-font-weight: bold; " +         // Font weight (bold)
                    "-fx-font-family: 'Arial'; " +      // Font family
                    "-fx-text-fill: white; " +          // Text color
                    "-fx-background-color: #4CAF50; " + // Background color
                    "-fx-background-radius: 20px; " +   // Rounded corners
                    "-fx-padding: 10px 20px; " +        // Padding inside the button
                    "-fx-border-color: #388E3C; " +     // Border color
                    "-fx-border-width: 2px; " +         // Border width
                    "-fx-border-radius: 20px;");        // Border radius for rounded edges
    
            // Optional: Adding hover effects for interactivity
            launchButton.setOnMouseEntered(e -> {
                launchButton.setStyle(
                        "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-family: 'Arial'; " +
                        "-fx-text-fill: white; " +
                        "-fx-background-color: #45a049; " + // Darker shade for hover effect
                        "-fx-background-radius: 20px; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-color: #388E3C; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 20px;");
            });
    
            launchButton.setOnMouseExited(e -> {
                launchButton.setStyle(
                        "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-family: 'Arial'; " +
                        "-fx-text-fill: white; " +
                        "-fx-background-color: #4CAF50; " +
                        "-fx-background-radius: 20px; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-color: #388E3C; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 20px;");
            });

        applicationMenuBox.getChildren().add(imageView);
        applicationMenuBox.getChildren().add(launchButton);

        // Button Functionality
        launchButton.setOnAction(e -> {
            launcher.switchScene(applicationScene);
        });
    }

    // Generate the scene
    private void generateApplication() {
        VBox applicationContainer = new VBox();

        // Create a container for the back button and label
        HBox headerContainer = new HBox();
        headerContainer.setAlignment(Pos.TOP_LEFT);
        headerContainer.setPadding(new Insets(10, 15, 10, 15));
        headerContainer.setSpacing(MonkeyLauncher.applicationWidth * 0.11);
        headerContainer.setStyle("-fx-border-color: black; -fx-border-width: 6px;");

        // Create back button
        Button backButton = new Button("Return\nHome");
        backButton.setStyle(
            "-fx-font-size: 18px; " +
            "-fx-font-weight: bold; " +
            "-fx-font-family: 'Arial'; " +
            "-fx-text-fill: white; " +
            "-fx-background-color: #f44336; " +
            "-fx-background-radius: 20px; " +
            "-fx-padding: 5px 15px; " +
            "-fx-border-color: #d32f2f; " +
            "-fx-border-width: 2px; " +
            "-fx-border-radius: 20px;");
        
        // Optional: Adding hover effects for interactivity
        backButton.setOnMouseEntered(e -> {
            backButton.setStyle(
            "-fx-font-size: 18px; " +
            "-fx-font-weight: bold; " +
            "-fx-font-family: 'Arial'; " +
            "-fx-text-fill: white; " +
            "-fx-background-color: #e53935; " +
            "-fx-background-radius: 20px; " +
            "-fx-padding: 5px 15px; " +
            "-fx-border-color: #d32f2f; " +
            "-fx-border-width: 2px; " +
            "-fx-border-radius: 20px;");
        });

        backButton.setOnMouseExited(e -> {
            backButton.setStyle(
            "-fx-font-size: 18px; " +
            "-fx-font-weight: bold; " +
            "-fx-font-family: 'Arial'; " +
            "-fx-text-fill: white; " +
            "-fx-background-color: #f44336; " +
            "-fx-background-radius: 20px; " +
            "-fx-padding: 5px 15px; " +
            "-fx-border-color: #d32f2f; " +
            "-fx-border-width: 2px; " +
            "-fx-border-radius: 20px;");
        });

        // Back button functionality
        backButton.setOnAction(e -> {
            launcher.goHome();
        });

        // Create logo image component
        ImageView imageView;
        try {
            imageView = new ImageView(new Image(setMenuImageLocation()));
            imageView.setFitHeight(MonkeyLauncher.applicationHeight * menuImageHeightFactor * 0.7); // TODO
            imageView.setFitWidth(MonkeyLauncher.applicationWidth * menuImageWidthFactor * 0.7); // TODO
            imageView.setPreserveRatio(false);
        } catch (IllegalArgumentException e) {
            System.err.println("Error loading image: " + setMenuImageLocation());
            imageView = new ImageView(); // Placeholder if image fails to load
        }

        // Add back button and label to the container
        headerContainer.getChildren().addAll(backButton, imageView);

        // Add header container to the application container
        applicationContainer.getChildren().add(headerContainer);
        applicationContainer.getChildren().add(getMainPane());

        // Set the scene
        applicationScene = new Scene(applicationContainer, MonkeyLauncher.applicationWidth, MonkeyLauncher.applicationHeight);
    }

    // Application mainContainer generation
    protected abstract GridPane getMainPane();

    // Return methods
    public HBox getApplicationMenuBox() {
        return applicationMenuBox;
    }

}
