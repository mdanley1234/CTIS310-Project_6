package edu.guilford.chemtools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Formula class represents a chemical formula consisting of elements, their counts, 
 * charge, multiplier, and molar mass. It parses a string representation of the formula and 
 * calculates the necessary attributes.
 */
public class Formula {

    private ArrayList<Element> elementComponents = new ArrayList<>(); // List of all elements
    private int charge; // Charge of formula without multiplier
    private double molarMass; // Molar mass of formula without multiplier
    private int multiplier; // Formula multiplier
    private String formatString; // Formatted formula string without multiplier

    /**
     * Constructor that creates a Formula object from a string representation of the formula.
     * It parses the formula and calculates the components, charge, and molar mass.
     * 
     * @param formulaString The string representation of the chemical formula
     */
    public Formula(String formulaString) {
        buildFormula(formulaString);
        // Remove the multiplier from the formula string to create formatString
        formatString = formulaString.replaceFirst("^\\d+", "");
    }

    /**
     * Parses the formula string to extract the multiplier, elements, and charge.
     * It also calculates the molar mass based on the element components.
     * 
     * @param formulaString The string representation of the chemical formula
     */
    private void buildFormula(String formulaString) {

        formulaString = formulaString.trim(); // Remove leading/trailing spaces

        // Pattern to match optional multiplier, chemical symbols with optional subscripts, and optional charge
        Pattern pattern = Pattern.compile("^(\\d+)?([A-Z][a-z]?(?:_\\d+)?)+(?:\\^([+-]?\\d*[+-]?\\d*))?$");
        Matcher matcher = pattern.matcher(formulaString);

        if (matcher.matches()) {
            // Extract multiplier (default to 1 if missing)
            multiplier = (matcher.group(1) != null) ? Integer.parseInt(matcher.group(1)) : 1;

            // Extract charge (default to 0 if missing)
            String chargeStr = matcher.group(3);

            // Determine the charge
            if (chargeStr == null) {
                charge = 0;
            } else if (chargeStr.equals("+")) {
                charge = 1;
            } else if (chargeStr.equals("-")) {
                charge = -1;
            } else {
                // Handle reversed charge format
                if (chargeStr.endsWith("+") || chargeStr.endsWith("-")) {
                    chargeStr = chargeStr.charAt(chargeStr.length() - 1) + chargeStr.substring(0, chargeStr.length() - 1);
                }
                charge = Integer.parseInt(chargeStr);
            }

            // Extract elements and their counts from the formula
            List<String> elements = new ArrayList<>();
            List<Integer> counts = new ArrayList<>();

            // Find all element-count pairs using regex
            Pattern elementPattern = Pattern.compile("([A-Z][a-z]?)(?:_(\\d+))?");
            Matcher elementMatcher = elementPattern.matcher(formulaString);

            while (elementMatcher.find()) {
                String element = elementMatcher.group(1); // Element symbol
                int count = (elementMatcher.group(2) != null) ? Integer.parseInt(elementMatcher.group(2)) : 1; // Default count is 1

                elements.add(element);
                counts.add(count);
            }

            // Add elements to elementComponents list based on their counts
            for (int j = 0; j < elements.size(); j++) {
                for (int k = 0; k < counts.get(j); k++) {
                    elementComponents.add(new Element(elements.get(j)));
                }
            }

            // Calculate molar mass based on the element components
            calculateMolarMass();
        } else {
            // Error handling for invalid formula format
            System.out.println("Invalid formula format.");
        }

    }

    /**
     * Calculates the molar mass of the formula by summing the atomic masses of the elements.
     */
    private void calculateMolarMass() {
        molarMass = 0;
        for (Element element : elementComponents) {
            molarMass += element.getAtomicMass();
        }
    }

    // Setters

    /**
     * Sets the multiplier for the formula.
     * 
     * @param k The multiplier value
     */
    public void setMultiplier(int k) {
        multiplier = k;
    }

    // Getters

    /**
     * Gets the list of element components in the formula.
     * 
     * @return List of Element objects representing the elements in the formula
     */
    public ArrayList<Element> getElementComponents() {
        return elementComponents;
    }

    /**
     * Gets the charge of the formula.
     * 
     * @return The charge of the formula
     */
    public int getCharge() {
        return charge;
    }

    /**
     * Gets the molar mass of the formula.
     * 
     * @return The molar mass of the formula
     */
    public double getMolarMass() {
        return molarMass;
    }

    /**
     * Gets the multiplier for the formula.
     * 
     * @return The multiplier value
     */
    public int getMultiplier() {
        return multiplier;
    }

    /**
     * Returns a string representation of the formula without the multiplier.
     * 
     * @return The formatted formula string
     */
    @Override
    public String toString() {
        return formatString;
    }

}
