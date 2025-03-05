package edu.guilford.chemtools;

/**
 * The Sample class extends the Formula class to represent a chemical sample. 
 * It calculates the mass and moles of the sample based on the formula and 
 * provides methods for setting and getting the sample mass and moles.
 */
public class Sample extends Formula {

    private double sampleMass = 0; // Mass of the sample in grams
    private double sampleMoles = 0; // Moles of the sample

    /**
     * Constructor that creates a Sample object from a string representation of the formula.
     * It calls the constructor of the parent Formula class to parse the formula.
     * 
     * @param formulaString The string representation of the chemical formula for the sample
     */
    public Sample(String formulaString) {
        super(formulaString);
    }

    // Calculated setters

    /**
     * Sets the mass of the sample and calculates the number of moles based on the molar mass of the formula.
     * 
     * @param sampleMass The mass of the sample in grams
     */
    public void setSampleMass(double sampleMass) {
        this.sampleMass = sampleMass;
        sampleMoles = sampleMass / getMolarMass(); // Calculate moles from mass and molar mass
    }

    /**
     * Sets the number of moles of the sample and calculates the mass based on the molar mass of the formula.
     * 
     * @param sampleMoles The number of moles of the sample
     */
    public void setSampleMoles(double sampleMoles) {
        this.sampleMoles = sampleMoles;
        sampleMass = sampleMoles * getMolarMass(); // Calculate mass from moles and molar mass
    }

    /**
     * Checks if the sample is empty, meaning its mass is zero.
     * 
     * @return true if the sample mass is zero, false otherwise
     */
    public boolean isEmpty() {
        return sampleMass == 0;
    }

    // Getters

    /**
     * Gets the mass of the sample in grams.
     * 
     * @return The mass of the sample in grams
     */
    public double getSampleMass() {
        return sampleMass;
    }

    /**
     * Gets the number of moles of the sample.
     * 
     * @return The number of moles of the sample
     */
    public double getSampleMoles() {
        return sampleMoles;
    }

    /**
     * Clears the sample by resetting the mass and moles to zero.
     */
    public void clear() {
        sampleMass = 0;
        sampleMoles = 0; // Fixed: the second `sampleMass = 0` was redundant
    }
}