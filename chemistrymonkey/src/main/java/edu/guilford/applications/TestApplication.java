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

public class TestApplication extends BaseApplication {

    public TestApplication(MonkeyLauncher launcher) {
        super(launcher);
    }

    @Override
    protected String setMenuImageLocation() {
        return "file:chemistrymonkey/src/main/resources/edu/guilford/TestApplicationPNG.png";
    }

    @Override
    protected GridPane getMainPane() {
        GridPane testPane = new GridPane();
        testPane.setAlignment(Pos.CENTER);
        testPane.setPadding(new Insets(10));
        testPane.setVgap(20); // Adds spacing between label and image

        Label testLabel = new Label("TEST APPLICATION");
        testLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        testLabel.setAlignment(Pos.CENTER);
        GridPane.setHalignment(testLabel, HPos.CENTER); // Ensures center alignment
        
        Image image = new Image("file:chemistrymonkey/src/main/resources/edu/guilford/warningSignPNG.png"); // Replace with actual image path
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(400); // Doubles the original size
        imageView.setPreserveRatio(true);
        
        testPane.add(testLabel, 0, 0);
        testPane.add(imageView, 0, 1);
        
        return testPane;
    }

}
