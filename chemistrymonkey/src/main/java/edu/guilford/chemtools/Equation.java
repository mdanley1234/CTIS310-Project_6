package edu.guilford.chemtools;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Equation {
    
    private int[] leftElementTotals, rightElementTotals; // Create an array to store element totals for the left and right sides (118 elemenents (ignore index 0))
    private AtomicInteger leftChargeTotal = new AtomicInteger(0); // Formula charge totals
    private AtomicInteger rightChargeTotal = new AtomicInteger(0);
    public static final int MULTIPLIER_MAX = 20; // Max multiplier checked

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

        // Temp array
        ArrayList<Formula> combinedFormulas = new ArrayList<>();
        combinedFormulas.addAll(leftFormulas);
        combinedFormulas.addAll(rightFormulas);

        // Generate values
        int[][] testValues = generateValues(combinedFormulas.size());

        // Balance equation
        for (int i = 0; i < testValues.length; i++) {
            for (int j = 0; j < combinedFormulas.size(); j++) {
                combinedFormulas.get(j).setMultiplier(testValues[i][j]);
            }
            if (isBalanced()) {
                return;
            }
        }
    }

    private int[][] generateValues(int formulaCount) {
        int combinationSets = (int) Math.pow(MULTIPLIER_MAX, formulaCount);
        int[][] testValues = new int[combinationSets][formulaCount]; // Each valueSet contains the subarray of values for each formula to be checked

        for (int i = 0; i < combinationSets; i++) {
            int value = i;
            for (int j = formulaCount - 1; j >= 0; j--) {
                testValues[i][j] = (value % MULTIPLIER_MAX) + 1;
                value /= MULTIPLIER_MAX;
            }
        }

        return testValues;
    }

    public boolean isBalanced() {
        resetCounters();

        // Calculate left side totals
        sumCounters(leftFormulas, leftElementTotals, leftChargeTotal);
        sumCounters(rightFormulas, rightElementTotals, rightChargeTotal);

        // Check charge balance
        if (leftChargeTotal.get() != rightChargeTotal.get()) {
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
    private void sumCounters(ArrayList<Formula> formulas, int[] elementTotals, AtomicInteger chargeTotal) {
        for (Formula formula : formulas) {
            // Calculate element totals
            for (Element element : formula.getElementComponents()) {
                elementTotals[element.getAtomicNumber()] += formula.getMultiplier();
            }

            // Calculate charge totals
            chargeTotal.addAndGet(formula.getCharge() * formula.getMultiplier());
        }
    }

    // Reset Counters
    private void resetCounters() {
        for (int i = 0; i < 119; i++) {
            leftElementTotals[i] = 0;
            rightElementTotals[i] = 0;
        }

        leftChargeTotal.set(0);
        rightChargeTotal.set(0);
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
        sb.append("The equation is ").append(isBalanced() ? "balanced" : "not balanced").append("\n");

        for (Formula formula : leftFormulas) {
            sb.append(formula.getMultiplier()).append(formula.toString());
            if (formula != leftFormulas.get(leftFormulas.size() - 1)) {
                sb.append(" + ");
            }
            else {
                sb.append(" = ");
            }
        }

        for (Formula formula : rightFormulas) {
            sb.append(formula.getMultiplier()).append(formula.toString());
            if (formula != rightFormulas.get(rightFormulas.size() - 1)) {
                sb.append(" + ");
            }
        }

        return sb.toString();
    }

}
