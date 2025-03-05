package edu.guilford.applications;

import edu.guilford.MonkeyLauncher;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MoleApplication extends BaseApplication {

    public MoleApplication(MonkeyLauncher launcher) {
        super(launcher);
    }

    @Override
    protected String setMenuImageLocation() {
        return "file:chemistrymonkey/src/main/resources/edu/guilford/MoleApplicationLogo.png";
    }
    
    @Override
    protected GridPane getMainPane() {
        GridPane mainPane = new GridPane();
        mainPane.setHgap(20);
        mainPane.setVgap(10);
        mainPane.setPadding(new Insets(25));
        mainPane.setAlignment(Pos.CENTER);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        mainPane.getColumnConstraints().addAll(col1, col2);
        
        // Fonts
        Font headerFont = Font.font("Arial", FontWeight.BOLD, 16);
        Font labelFont = Font.font("Arial", FontWeight.NORMAL, 14);
        
        // Column 1: Molar Mass Calculations
        Label molarMassHeader = new Label("Molar Mass Calculations");
        molarMassHeader.setFont(headerFont);
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
        Label volumeLabel = new Label("Volume of Solution");
        volumeLabel.setFont(labelFont);
        TextField volumeField = new TextField();
        Label molarityLabel = new Label("Molarity of Solution");
        molarityLabel.setFont(labelFont);
        TextField molarityField = new TextField();
        
        // Adding to GridPane
        mainPane.add(molarMassHeader, 0, 0);
        mainPane.add(formulaLabel, 0, 1);
        mainPane.add(formulaField, 0, 2);
        mainPane.add(gramsLabel, 0, 3);
        mainPane.add(gramsField, 0, 4);
        mainPane.add(molesLabel, 0, 5);
        mainPane.add(molesField, 0, 6);
        
        mainPane.add(molarityHeader, 1, 0);
        mainPane.add(volumeLabel, 1, 1);
        mainPane.add(volumeField, 1, 2);
        mainPane.add(molarityLabel, 1, 3);
        mainPane.add(molarityField, 1, 4);
        
        return mainPane;
    }

    
}
