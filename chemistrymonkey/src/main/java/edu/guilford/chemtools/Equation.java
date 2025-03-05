package edu.guilford.chemtools;

import java.util.ArrayList;

public class Equation {
    
    private int[] leftElementTotals, rightElementTotals; // Create an array to store element totals for the left and right sides (118 elemenents (ignore index 0))
    private int leftChargeTotal, rightChargeTotal; // Formula charge totals

    // Formula lists
    private ArrayList<Formula> leftFormulas, rightFormulas; 

    public Equation(ArrayList<Formula> leftFormulas, ArrayList<Formula> rightFormulas) {
        this.leftFormulas = leftFormulas;
        this.rightFormulas = rightFormulas;
    }

    public Equation() {
        leftFormulas = new ArrayList<Formula>();
        rightFormulas = new ArrayList<Formula>();

        leftElementTotals = new int[119];
        rightElementTotals = new int[119];
    }

    // Add formulas
    public void addLeftFormula(Formula formula) {
        leftFormulas.add(formula);
    }
    public void addRightFormula(Formula formula) {
        rightFormulas.add(formula);
    }

    // Method to automatically balance the equations
    public void balanceEquations() {
        // Check case
        if (isBalanced()) {
            return;
        }

        // Prepare for auto balance algorithm
        for (Formula formula : leftFormulas) {
            formula.setMultiplier(1);
        }
        for (Formula formula : rightFormulas) {
            formula.setMultiplier(1);
        }

        int maxAttempts = 1000; // Limit to prevent infinite loops
        int attempt = 0;

        while (!isBalanced() && attempt < maxAttempts) {
            attempt++;
            
            // Generate and test multipliers
            for (int l1 = 1; l1 <= attempt; l1++) {
                leftFormulas.get(0).setMultiplier(l1);
                for (int l2 = 1; l2 <= attempt; l2++) {
                    if (leftFormulas.size() > 1) leftFormulas.get(1).setMultiplier(l2);
                    for (int r1 = 1; r1 <= attempt; r1++) {
                        rightFormulas.get(0).setMultiplier(r1);
                        for (int r2 = 1; r2 <= attempt; r2++) {
                            if (rightFormulas.size() > 1) rightFormulas.get(1).setMultiplier(r2);
                        }
                    }
                }
            }
        }
    }

    public boolean isBalanced() {
        resetCounters();

        // Calculate left side totals
        sumCounters(leftFormulas, leftElementTotals, leftChargeTotal);
        sumCounters(rightFormulas, rightElementTotals, rightChargeTotal);

        // Check charge balance
        if (leftChargeTotal != rightChargeTotal) {
            return false;
        }

        // Check element balance
        for (int i = 0; i < 119; i++) {
            if (leftElementTotals[i] != rightElementTotals[i]) {
                return false;
            }
        }

        return true;
    }

    // Calculate element and charge totals
    private void sumCounters(ArrayList<Formula> formulas, int[] elementTotals, int chargeTotal) {
        for (Formula formula : formulas) {
            // Calculate element totals
            for (Element element : formula.getElementComponents()) {
                elementTotals[element.getAtomicNumber()] += formula.getMultiplier();
            }

            // Calculate charge totals
            chargeTotal += formula.getCharge() * formula.getMultiplier();
        }
    }

    // Reset Counters
    private void resetCounters() {
        for (int i = 0; i < 119; i++) {
            leftElementTotals[i] = 0;
            rightElementTotals[i] = 0;
        }

        leftChargeTotal = 0;
        rightChargeTotal = 0;
    }

    // Clear method
    public void clear() {
        leftFormulas.clear();
        rightFormulas.clear();
    }

    // To String
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Equation is ").append(isBalanced() ? "balanced" : "not balanced").append("\n");

        sb.append("Left side:\n");
        for (Formula formula : leftFormulas) {
            sb.append(formula.getMultiplier()).append(formula.toString()).append(")\n");
        }

        sb.append("Right side:\n");
        for (Formula formula : rightFormulas) {
            sb.append(formula.getMultiplier()).append(formula.toString()).append(")\n");
        }

        return sb.toString();
    }

}
