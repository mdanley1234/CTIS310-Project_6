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

public class EquationApplication extends BaseApplication {

    private Equation equation = new Equation();

    public EquationApplication(MonkeyLauncher launcher) {
        super(launcher);
    }

    @Override
    protected String setMenuImageLocation() {
        return "file:chemistrymonkey/src/main/resources/edu/guilford/EquationBalancerLogo.png";
    }
        
    @Override
    protected GridPane getMainPane() {
        GridPane mainPane = new GridPane();
        mainPane.setHgap(20);
        mainPane.setVgap(10);
        mainPane.setPadding(new Insets(25));
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setPrefWidth(600);

        ColumnConstraints col1 = new ColumnConstraints(250, 250, Double.MAX_VALUE);
        col1.setHgrow(Priority.ALWAYS);
        ColumnConstraints col2 = new ColumnConstraints(250, 250, Double.MAX_VALUE);
        col2.setHgrow(Priority.ALWAYS);
        mainPane.getColumnConstraints().addAll(col1, col2);

        Font headerFont = Font.font("Arial", FontWeight.BOLD, 16);
        
        Label leftHeader = new Label("Reactants");
        leftHeader.setFont(headerFont);
        leftHeader.setAlignment(Pos.CENTER);
        GridPane.setHalignment(leftHeader, HPos.CENTER);
        
        Label rightHeader = new Label("Products");
        rightHeader.setFont(headerFont);
        rightHeader.setAlignment(Pos.CENTER);
        GridPane.setHalignment(rightHeader, HPos.CENTER);

        VBox leftBox = new VBox(10);
        leftBox.setPrefWidth(250);
        leftBox.setAlignment(Pos.TOP_CENTER);
        
        VBox rightBox = new VBox(10);
        rightBox.setPrefWidth(250);
        rightBox.setAlignment(Pos.TOP_CENTER);

        Button addLeft = new Button("+ Add Reactant");
        Button addRight = new Button("+ Add Product");
        Button solveButton = new Button("Solve Equation");
        solveButton.setMaxWidth(Double.MAX_VALUE);
        
        Label resultLabel = new Label();
        resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        resultLabel.setWrapText(true);
        resultLabel.setMaxWidth(550);
        resultLabel.setAlignment(Pos.CENTER);
        GridPane.setHalignment(resultLabel, HPos.CENTER);
        
        addLeft.setOnAction(e -> addFormulaField(leftBox));
        addRight.setOnAction(e -> addFormulaField(rightBox));
        solveButton.setOnAction(e -> solveEquation(leftBox, rightBox, resultLabel));

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

    private void solveEquation(VBox leftBox, VBox rightBox, Label resultLabel) {

        // Populate the equations object
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

        // Solve
        equation.balanceEquations();
        resultLabel.setText("Balanced Equation: " + equation.toString());
    }

    
}
