package edu.guilford.chemtools;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Equation class represents a chemical equation, consisting of left and right side formulas.
 * It provides methods to balance the equation and calculate the element and charge totals for both sides.
 */
public class Equation {

    private int[] leftElementTotals, rightElementTotals; // Element totals for the left and right sides (118 elements, index 0 is ignored)
    private AtomicInteger leftChargeTotal = new AtomicInteger(0); // Total charge for the left side
    private AtomicInteger rightChargeTotal = new AtomicInteger(0); // Total charge for the right side
    public static final int MULTIPLIER_MAX = 20; // Max multiplier checked

    // Formula lists for left and right sides of the equation
    private ArrayList<Formula> leftFormulas, rightFormulas;

    /**
     * Constructor that initializes an Equation with provided left and right formulas.
     * 
     * @param leftFormulas List of formulas for the left side of the equation
     * @param rightFormulas List of formulas for the right side of the equation
     */
    public Equation(ArrayList<Formula> leftFormulas, ArrayList<Formula> rightFormulas) {
        this.leftFormulas = leftFormulas;
        this.rightFormulas = rightFormulas;
    }

    /**
     * Default constructor that initializes the left and right formulas lists and element totals arrays.
     */
    public Equation() {
        leftFormulas = new ArrayList<>();
        rightFormulas = new ArrayList<>();
        leftElementTotals = new int[119];
        rightElementTotals = new int[119];
    }

    /**
     * Adds a formula to the left side of the equation.
     * 
     * @param formula Formula to be added to the left side
     */
    public void addLeftFormula(Formula formula) {
        leftFormulas.add(formula);
    }

    /**
     * Adds a formula to the right side of the equation.
     * 
     * @param formula Formula to be added to the right side
     */
    public void addRightFormula(Formula formula) {
        rightFormulas.add(formula);
    }

    /**
     * Automatically balances the equation by adjusting multipliers for the formulas.
     * It tests different combinations of multipliers to find a balanced equation.
     */
    public void balanceEquations() {
        // Check if the equation is already balanced
        if (isBalanced()) {
            return;
        }

        // Prepare for auto-balance algorithm by setting initial multipliers to 1
        for (Formula formula : leftFormulas) {
            formula.setMultiplier(1);
        }
        for (Formula formula : rightFormulas) {
            formula.setMultiplier(1);
        }

        // Combine left and right formulas for testing
        ArrayList<Formula> combinedFormulas = new ArrayList<>();
        combinedFormulas.addAll(leftFormulas);
        combinedFormulas.addAll(rightFormulas);

        // Generate possible multiplier combinations
        int[][] testValues = generateValues(combinedFormulas.size());

        // Try different multiplier combinations
        for (int i = 0; i < testValues.length; i++) {
            for (int j = 0; j < combinedFormulas.size(); j++) {
                combinedFormulas.get(j).setMultiplier(testValues[i][j]);
            }
            if (isBalanced()) {
                return;
            }
        }
    }

    /**
     * Generates all possible combinations of multipliers for the given number of formulas.
     * 
     * @param formulaCount The total number of formulas to generate values for
     * @return A 2D array of integers representing the multiplier combinations to test
     */
    private int[][] generateValues(int formulaCount) {
        int combinationSets = (int) Math.pow(MULTIPLIER_MAX, formulaCount);
        int[][] testValues = new int[combinationSets][formulaCount];

        for (int i = 0; i < combinationSets; i++) {
            int value = i;
            for (int j = formulaCount - 1; j >= 0; j--) {
                testValues[i][j] = (value % MULTIPLIER_MAX) + 1;
                value /= MULTIPLIER_MAX;
            }
        }

        return testValues;
    }

    /**
     * Checks whether the equation is balanced by comparing the element and charge totals on both sides.
     * 
     * @return true if the equation is balanced, false otherwise
     */
    public boolean isBalanced() {
        resetCounters();

        // Calculate element and charge totals for both sides
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

    /**
     * Sums the element and charge totals for the given formulas.
     * 
     * @param formulas List of formulas to process
     * @param elementTotals Array to store element totals
     * @param chargeTotal AtomicInteger to store the total charge
     */
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

    /**
     * Resets the element and charge counters to zero.
     */
    private void resetCounters() {
        for (int i = 0; i < 119; i++) {
            leftElementTotals[i] = 0;
            rightElementTotals[i] = 0;
        }

        leftChargeTotal.set(0);
        rightChargeTotal.set(0);
    }

    /**
     * Clears all formulas from both the left and right sides of the equation.
     */
    public void clear() {
        leftFormulas.clear();
        rightFormulas.clear();
    }

    /**
     * Returns a string representation of the equation, showing the formulas and their multipliers.
     * 
     * @return A string representing the equation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("The equation is ").append(isBalanced() ? "balanced" : "not balanced").append("\n");

        // Left side of the equation
        for (Formula formula : leftFormulas) {
            sb.append(formula.getMultiplier()).append(formula.toString());
            if (formula != leftFormulas.get(leftFormulas.size() - 1)) {
                sb.append(" + ");
            } else {
                sb.append(" = ");
            }
        }

        // Right side of the equation
        for (Formula formula : rightFormulas) {
            sb.append(formula.getMultiplier()).append(formula.toString());
            if (formula != rightFormulas.get(rightFormulas.size() - 1)) {
                sb.append(" + ");
            }
        }

        return sb.toString();
    }

}
