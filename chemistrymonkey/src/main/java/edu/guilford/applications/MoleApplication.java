package edu.guilford.applications;

import edu.guilford.MonkeyLauncher;
import edu.guilford.chemtools.Sample;
import edu.guilford.chemtools.Solution;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * The MoleApplication class provides a user interface for calculating molar mass,
 * moles, molarity, and volume of a solution. It includes input fields for a compound's
 * formula, sample mass, moles, and solution volume, and calculates the relevant quantities
 * based on the user's input.
 */
public class MoleApplication extends BaseApplication {

    private Sample sample;
    private Solution solution;

    /**
     * Constructor for MoleApplication.
     *
     * @param launcher The MonkeyLauncher instance.
     */
    public MoleApplication(MonkeyLauncher launcher) {
        super(launcher);
    }

    /**
     * Sets the location of the menu image for the application.
     *
     * @return The file path to the image.
     */
    @Override
    protected String setMenuImageLocation() {
        return "file:chemistrymonkey/src/main/resources/edu/guilford/MoleApplicationLogo.png";
    }

    /**
     * Constructs the main user interface of the application, including input fields and labels
     * for molar mass and molarity calculations.
     *
     * @return The GridPane layout containing the main user interface.
     */
    @Override
    protected GridPane getMainPane() {
        GridPane mainPane = new GridPane();
        mainPane.setHgap(20);
        mainPane.setVgap(10);
        mainPane.setPadding(new Insets(25));
        mainPane.setAlignment(Pos.CENTER);

        // Define column constraints
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        mainPane.getColumnConstraints().addAll(col1, col2);

        // Fonts for headers and labels
        Font headerFont = Font.font("Arial", FontWeight.BOLD, 16);
        Font labelFont = Font.font("Arial", FontWeight.NORMAL, 14);
        Font molarMassFont = Font.font("Arial", FontWeight.BOLD, 14);

        // Column 1: Molar Mass Calculations
        Label molarMassHeader = new Label("Molar Mass Calculations");
        molarMassHeader.setFont(headerFont);

        Label molarMassLabel = new Label("Molar Mass of Compound (grams per mole): ");
        molarMassLabel.setFont(molarMassFont);

        Label formulaLabel = new Label("Compound Formula");
        formulaLabel.setFont(labelFont);
        TextField formulaField = new TextField();

        Label gramsLabel = new Label("Grams of Compound");
        gramsLabel.setFont(labelFont);
        TextField gramsField = new TextField();

        Label molesLabel = new Label("Moles of Compound");
        molesLabel.setFont(labelFont);
        TextField molesField = new TextField();

        // Column 2: Molarity Calculations
        Label molarityHeader = new Label("Molarity Calculations");
        molarityHeader.setFont(headerFont);

        Label volumeLabel = new Label("Liters of Solution");
        volumeLabel.setFont(labelFont);
        TextField volumeField = new TextField();

        Label molarityLabel = new Label("Molarity of Solution");
        molarityLabel.setFont(labelFont);
        TextField molarityField = new TextField();

        // Clear Button with style
        Button clearButton = new Button("Clear Fields");
        clearButton.setStyle(
                "-fx-font-size: 16px; "
                + "-fx-font-weight: bold; "
                + "-fx-font-family: 'Arial'; "
                + "-fx-text-fill: white; "
                + "-fx-background-color: #4CAF50; "
                + "-fx-background-radius: 20px; "
                + "-fx-padding: 10px 20px; "
                + "-fx-border-color: #388E3C; "
                + "-fx-border-width: 2px; "
                + "-fx-border-radius: 20px;");

        // Hover effects for the button
        clearButton.setOnMouseEntered(e -> {
            clearButton.setStyle(
                    "-fx-font-size: 16px; "
                    + "-fx-font-weight: bold; "
                    + "-fx-font-family: 'Arial'; "
                    + "-fx-text-fill: white; "
                    + "-fx-background-color: #45a049; "
                    + "-fx-background-radius: 20px; "
                    + "-fx-padding: 10px 20px; "
                    + "-fx-border-color: #388E3C; "
                    + "-fx-border-width: 2px; "
                    + "-fx-border-radius: 20px;");
        });

        clearButton.setOnMouseExited(e -> {
            clearButton.setStyle(
                    "-fx-font-size: 16px; "
                    + "-fx-font-weight: bold; "
                    + "-fx-font-family: 'Arial'; "
                    + "-fx-text-fill: white; "
                    + "-fx-background-color: #4CAF50; "
                    + "-fx-background-radius: 20px; "
                    + "-fx-padding: 10px 20px; "
                    + "-fx-border-color: #388E3C; "
                    + "-fx-border-width: 2px; "
                    + "-fx-border-radius: 20px;");
        });

        // Clear button logic
        clearButton.setOnAction(event -> {
            if (sample != null) {
                sample.clear();
                solution.clear();
            }
            formulaField.clear();
            gramsField.clear();
            molesField.clear();
            volumeField.clear();
            molarityField.clear();
            molarMassLabel.setText("Molar Mass of Compound (grams per mole): ");
        });

        // Adding components to the main grid
        mainPane.add(molarMassHeader, 0, 0);
        mainPane.add(molarMassLabel, 0, 1);
        mainPane.add(formulaLabel, 0, 2);
        mainPane.add(formulaField, 0, 3);
        mainPane.add(gramsLabel, 0, 4);
        mainPane.add(gramsField, 0, 5);
        mainPane.add(molesLabel, 0, 6);
        mainPane.add(molesField, 0, 7);

        mainPane.add(molarityHeader, 1, 0);
        mainPane.add(volumeLabel, 1, 1);
        mainPane.add(volumeField, 1, 2);
        mainPane.add(molarityLabel, 1, 3);
        mainPane.add(molarityField, 1, 4);
        mainPane.add(clearButton, 1, 7);

        // Center the button horizontally and vertically in the grid cell
        GridPane.setHalignment(clearButton, HPos.CENTER);  // Horizontally center
        GridPane.setValignment(clearButton, VPos.CENTER);  // Vertically center

        // Action listeners for the input fields
        formulaField.setOnAction(event -> {
            sample = new Sample(formulaField.getText());
            solution = new Solution(sample);
            molarMassLabel.setText("Molar Mass of Compound (grams per mole): " + String.valueOf(sample.getMolarMass()));

            // Clear other fields
            gramsField.clear();
            molesField.clear();
            volumeField.clear();
            molarityField.clear();
        });

        gramsField.setOnAction(event -> {
            try {
                double mass = Double.parseDouble(gramsField.getText());
                sample.setSampleMass(mass);
            } catch (NumberFormatException e) {
                gramsField.setText("Invalid input");
            }

            solution.clear();
            gramsField.setText(String.valueOf(sample.getSampleMass()));
            molesField.setText(String.valueOf(sample.getSampleMoles()));
            volumeField.setText(String.valueOf(solution.getVolume()));
            molarityField.setText(String.valueOf(solution.getMolarity()));
        });

        molesField.setOnAction(event -> {
            try {
                double moles = Double.parseDouble(molesField.getText());
                sample.setSampleMoles(moles);
            } catch (NumberFormatException e) {
                molesField.setText("Invalid input");
            }

            solution.clear();
            gramsField.setText(String.valueOf(sample.getSampleMass()));
            molesField.setText(String.valueOf(sample.getSampleMoles()));
            volumeField.setText(String.valueOf(solution.getVolume()));
            molarityField.setText(String.valueOf(solution.getMolarity()));
        });

        volumeField.setOnAction(event -> {
            try {
                double volume = Double.parseDouble(volumeField.getText());
                solution.setVolume(volume);
            } catch (NumberFormatException e) {
                volumeField.setText("Invalid input");
            }

            gramsField.setText(String.valueOf(sample.getSampleMass()));
            molesField.setText(String.valueOf(sample.getSampleMoles()));
            volumeField.setText(String.valueOf(solution.getVolume()));
            molarityField.setText(String.valueOf(solution.getMolarity()));
        });

        molarityField.setOnAction(event -> {
            try {
                double molarity = Double.parseDouble(molarityField.getText());
                solution.setMolarity(molarity);
            } catch (NumberFormatException e) {
                molarityField.setText("Invalid input");
            }

            gramsField.setText(String.valueOf(sample.getSampleMass()));
            molesField.setText(String.valueOf(sample.getSampleMoles()));
            volumeField.setText(String.valueOf(solution.getVolume()));
            molarityField.setText(String.valueOf(solution.getMolarity()));
        });

        return mainPane;
    }
}