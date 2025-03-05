package edu.guilford.chemtools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formula {

    private ArrayList<Element> elementComponents = new ArrayList<>(); // List of all elements
    private int charge; // Charge of formula without multipler
    private double molarMass; // Molar mass of formula without multiplier
    private int multiplier; // Formula multiplier

    public Formula(String formulaString) {
        buildFormula(formulaString);
    }

    private void buildFormula(String formulaString) {
        
        formulaString = formulaString.trim(); // TODO: REMOVE TRIM PROTECTION

        // Pattern to match optional multiplier, chemical symbols with optional subscripts, and optional charge
        Pattern pattern = Pattern.compile("^(\\d+)?([A-Z][a-z]?(?:_\\d+)?)+(?:\\^([+-]?\\d*[+-]?\\d*))?$");
        Matcher matcher = pattern.matcher(formulaString);

        if (matcher.matches()) {
            // Extract multiplier (default to 1 if missing)
            multiplier = (matcher.group(1) != null) ? Integer.parseInt(matcher.group(1)) : 1;

            // Extract charge (default to 0 if missing)
            String chargeStr = matcher.group(3);

            // Check special cases and calculate charge
            if (chargeStr == null) {
                charge = 0;
            } else if (chargeStr.equals("+")) {
                charge = 1;
            } else if (chargeStr.equals("-")) {
                charge = -1;
            } else {
                // Fix reversed ordering
                if (chargeStr.endsWith("+") || chargeStr.endsWith("-")) {
                    chargeStr = chargeStr.charAt(chargeStr.length() - 1) + chargeStr.substring(0, chargeStr.length() - 1);
                }
                charge = Integer.parseInt(chargeStr);
            }

            // Extract elements and counts dynamically
            List<String> elements = new ArrayList<>();
            List<Integer> counts = new ArrayList<>();

            // Find all element-count pairs using regex
            Pattern elementPattern = Pattern.compile("([A-Z][a-z]?)(?:_(\\d+))?");
            Matcher elementMatcher = elementPattern.matcher(formulaString);

            while (elementMatcher.find()) {
                String element = elementMatcher.group(1); // Extract element symbol
                int count = (elementMatcher.group(2) != null) ? Integer.parseInt(elementMatcher.group(2)) : 1; // Default count is 1

                elements.add(element);
                counts.add(count);
            }


            // Print extracted values TODO: DEBUG REMOVAL
            System.out.println("Multiplier: " + multiplier); // TODO: DEBUG TOOL REMOVAL
            for (int i = 0; i < elements.size(); i++) {
                System.out.println("Element: " + elements.get(i) + ", Count: " + counts.get(i)); // TODO: DEBUG TOOL REMOVAL
            }
            System.out.println("Charge: " + charge); // TODO: DEBUG TOOL REMOVAL


            //  Add elements to array
            for (int j = 0; j < elements.size(); j++) {
                for (int k = 0; k < counts.get(j); k++) {
                    elementComponents.add(new Element(elements.get(j)));
                }
            }

            // Molar mass calculations
            calculateMolarMass();
        } else {
            // TODO: ERROR HANDLING
            System.out.println("Invalid formula format.");
        }

    }

    private void calculateMolarMass() {
        molarMass = 0;
        for (Element element : elementComponents) {
            molarMass += element.getAtomicMass();
        }
    }

    // Setters
    public void setMultiplier(int k) {
        multiplier = k;
    }

    // Getters
    public ArrayList<Element> getElementComponents() {
        return elementComponents;
    }

    public int getCharge() {
        return charge;
    }

    public double getMolarMass() {
        return molarMass;
    }

    public int getMultiplier() {
        return multiplier;
    }

}