package edu.guilford.processing;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formula {

    private ArrayList<Element> elementComponents = new ArrayList<>(); // List of all elements
    private int totalCharge = 0; // Total charge of formula

    public Formula(String formulaString) {
        buildFormula(formulaString);

    }

    private void buildFormula(String formulaString) {
        // Split formulaString into individual formulas seperated by the + operator and trim extra spaces
        String[] formulaComponents = formulaString.split(" \\+ ");
        for (int i = 0; i < formulaComponents.length; i++) {
            formulaComponents[i] = formulaComponents[i].trim();
        }

        for (String formulaComponent : formulaComponents) {
            // Pattern to match optional multiplier, chemical symbols with optional subscripts, and optional charge
            Pattern pattern = Pattern.compile("^(\\d+)?([A-Z][a-z]?(?:_\\d+)?)+(?:\\^([+-]?\\d*[+-]?\\d*))?$");
            Matcher matcher = pattern.matcher(formulaComponent);

            if (matcher.matches()) {
                // Extract multiplier (default to 1 if missing)
                int multiplier = (matcher.group(1) != null) ? Integer.parseInt(matcher.group(1)) : 1;

                // Extract charge (default to 0 if missing)
                String chargeStr = matcher.group(3);

                // Check special cases
                int charge;
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
                Matcher elementMatcher = elementPattern.matcher(formulaComponent);

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
                for (int i = 0; i < multiplier; i++) {
                    for (int j = 0; j < elements.size(); j++) {
                        for (int k = 0; k < counts.size(); k++) {
                            elementComponents.add(new Element(elements.get(j)));
                        }
                    }
                }

                // Sum total charge
                totalCharge += multiplier * charge;

            } else {
                // TODO: ERROR HANDLING
                System.out.println("Invalid formula format.");
            }
        }

    }

}
