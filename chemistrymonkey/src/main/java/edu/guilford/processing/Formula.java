package edu.guilford.processing;

import java.util.ArrayList;

public class Formula {
    
    private ArrayList<Element> elementComponents = new ArrayList<>();
    private ArrayList<String> stringComponents = new ArrayList<>();

    public Formula(String formulaString) {
        generateStringComponents(formulaString);

    }

    private void generateStringComponents(String formulaString) {
        // Split formulaString into individual formulas seperated by the + operator and trim extra spaces
        String[] formulaComponents = formulaString.split(" \\+ ");
        for (int i = 0; i < formulaComponents.length; i++) {
            formulaComponents[i] = formulaComponents[i].trim();
        }

        for (String formulaComponent : formulaComponents) {

        }
        

    }

}
