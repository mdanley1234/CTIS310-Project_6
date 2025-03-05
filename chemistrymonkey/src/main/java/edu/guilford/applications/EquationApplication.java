package edu.guilford.applications;

import edu.guilford.MonkeyLauncher;
import edu.guilford.chemtools.Equation;
import edu.guilford.chemtools.Formula;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * The EquationApplication class provides a GUI for balancing chemical equations.
 * It extends BaseApplication and allows the user to input chemical formulas for reactants and products,
 * solve the equation, and display the balanced equation.
 */
public class EquationApplication extends BaseApplication {

    private Equation equation = new Equation();

    /**
     * Constructs the EquationApplication with a given launcher.
     *
     * @param launcher the MonkeyLauncher used for scene switching
     */
    public EquationApplication(MonkeyLauncher launcher) {
        super(launcher);
    }

    /**
     * Returns the location of the menu image for the application.
     *
     * @return the image location as a string
     */
    @Override
    protected String setMenuImageLocation() {
        return "file:chemistrymonkey/src/main/resources/edu/guilford/EquationBalancerLogo.png";
    }

    /**
     * Generates the main content for the EquationApplication scene.
     * This includes the input fields for reactants and products, buttons for adding formulas,
     * solving the equation, and displaying the result.
     *
     * @return the GridPane containing the main layout for the application
     */
    @Override
    protected GridPane getMainPane() {
        GridPane mainPane = new GridPane();
        mainPane.setHgap(20);
        mainPane.setVgap(10);
        mainPane.setPadding(new Insets(25));
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setPrefWidth(600);

        // Define column constraints to make the columns flexible
        ColumnConstraints col1 = new ColumnConstraints(250, 250, Double.MAX_VALUE);
        col1.setHgrow(Priority.ALWAYS);
        ColumnConstraints col2 = new ColumnConstraints(250, 250, Double.MAX_VALUE);
        col2.setHgrow(Priority.ALWAYS);
        mainPane.getColumnConstraints().addAll(col1, col2);

        // Create headers for the reactant and product columns
        Font headerFont = Font.font("Arial", FontWeight.BOLD, 16);

        Label leftHeader = new Label("Reactants");
        leftHeader.setFont(headerFont);
        leftHeader.setAlignment(Pos.CENTER);
        GridPane.setHalignment(leftHeader, HPos.CENTER);

        Label rightHeader = new Label("Products");
        rightHeader.setFont(headerFont);
        rightHeader.setAlignment(Pos.CENTER);
        GridPane.setHalignment(rightHeader, HPos.CENTER);

        // Create VBox containers for reactant and product formula fields
        VBox leftBox = new VBox(10);
        leftBox.setPrefWidth(250);
        leftBox.setAlignment(Pos.TOP_CENTER);

        VBox rightBox = new VBox(10);
        rightBox.setPrefWidth(250);
        rightBox.setAlignment(Pos.TOP_CENTER);

        // Create buttons for adding formulas and solving the equation
        Button addLeft = new Button("+ Add Reactant");
        Button addRight = new Button("+ Add Product");
        Button solveButton = new Button("Solve Equation");
        solveButton.setMaxWidth(Double.MAX_VALUE);

        // Create label to display the result
        Label resultLabel = new Label();
        resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        resultLabel.setWrapText(true);
        resultLabel.setMaxWidth(550);
        resultLabel.setAlignment(Pos.CENTER);
        GridPane.setHalignment(resultLabel, HPos.CENTER);

        // Set button actions
        addLeft.setOnAction(e -> addFormulaField(leftBox));
        addRight.setOnAction(e -> addFormulaField(rightBox));
        solveButton.setOnAction(e -> solveEquation(leftBox, rightBox, resultLabel));

        // Add all components to the main layout
        mainPane.add(leftHeader, 0, 0);
        mainPane.add(rightHeader, 1, 0);
        mainPane.add(leftBox, 0, 1);
        mainPane.add(rightBox, 1, 1);
        mainPane.add(addLeft, 0, 2);
        mainPane.add(addRight, 1, 2);
        mainPane.add(solveButton, 0, 3, 2, 1);
        mainPane.add(resultLabel, 0, 4, 2, 1);

        return mainPane;
    }

    /**
     * Adds a new formula input field to the specified VBox.
     * Each formula field has a corresponding "remove" button.
     *
     * @param box the VBox to add the formula field to (either reactants or products)
     */
    private void addFormulaField(VBox box) {
        HBox formulaRow = new HBox(10);
        formulaRow.setAlignment(Pos.CENTER);
        TextField formulaField = new TextField();
        formulaField.setPrefWidth(180);
        Button removeButton = new Button("X");
        removeButton.setOnAction(e -> box.getChildren().remove(formulaRow));
        formulaRow.getChildren().addAll(formulaField, removeButton);
        box.getChildren().add(formulaRow);
    }

    /**
     * Solves the equation by balancing the formulas entered for reactants and products.
     * The equation is solved using the Equation class, and the result is displayed in the result label.
     *
     * @param leftBox the VBox containing the reactant formulas
     * @param rightBox the VBox containing the product formulas
     * @param resultLabel the label to display the result
     */
    private void solveEquation(VBox leftBox, VBox rightBox, Label resultLabel) {

        // Populate the equations object with the formulas from the UI
        equation.clear();
        for (Node node : leftBox.getChildren()) {
            if (node instanceof HBox) {
                HBox hbox = (HBox) node;
                for (Node innerNode : hbox.getChildren()) {
                    if (innerNode instanceof TextField) {
                        TextField textField = (TextField) innerNode;
                        equation.addLeftFormula(new Formula(textField.getText()));
                    }
                }
            }
        }

        for (Node node : rightBox.getChildren()) {
            if (node instanceof HBox) {
                HBox hbox = (HBox) node;
                for (Node innerNode : hbox.getChildren()) {
                    if (innerNode instanceof TextField) {
                        TextField textField = (TextField) innerNode;
                        equation.addRightFormula(new Formula(textField.getText()));
                    }
                }
            }
        }

        // Solve the equation
        equation.balanceEquations();
        resultLabel.setText("(Max Coefficient Checked: " + Equation.MULTIPLIER_MAX + "): " + equation.toString());
    }
}