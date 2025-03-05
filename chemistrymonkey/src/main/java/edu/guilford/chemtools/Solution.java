package edu.guilford.chemtools;

/**
 * The Solution class represents a chemical solution composed of a specific sample 
 * and its associated volume and molarity. It provides methods for calculating and 
 * adjusting the volume, molarity, and moles of the sample in the solution.
 */
public class Solution {

    private final Sample sample; // Compound in the solution
    private double volume; // Volume of the solution in liters
    private double molarity; // Molarity of the solution in mol/L

    /**
     * Constructor that creates a Solution object for a specific Sample.
     * 
     * @param sample The Sample object representing the compound in the solution
     */
    public Solution(Sample sample) {
        this.sample = sample;
    }

    // Calculated setters

    /**
     * Sets the volume of the solution and calculates the molarity or moles of the sample
     * based on the volume and the sample's moles or molarity.
     * 
     * @param volume The volume of the solution in liters
     */
    public void setVolume(double volume) {
        this.volume = volume;
        if (sample.isEmpty() && molarity > 0) {
            sample.setSampleMoles(volume * molarity); // Calculate moles if molarity is known
        }
        else if (!sample.isEmpty()) {
            molarity = sample.getSampleMoles() / volume; // Calculate molarity if sample is not empty
        }
    }

    /**
     * Sets the molarity of the solution and calculates the volume or moles of the sample
     * based on the molarity and the sample's moles or volume.
     * 
     * @param molarity The molarity of the solution in mol/L
     */
    public void setMolarity(double molarity) {
        this.molarity = molarity;
        if (sample.isEmpty() && volume > 0) {
            sample.setSampleMoles(volume * molarity); // Calculate moles if volume is known
        }
        else if (!sample.isEmpty()) {
            volume = sample.getSampleMoles() / molarity; // Calculate volume if sample is not empty
        }   
    }

    // Getters

    /**
     * Gets the sample compound in the solution.
     * 
     * @return The Sample object representing the compound in the solution
     */
    public Sample getSample() {
        return sample;
    }

    /**
     * Gets the volume of the solution in liters.
     * 
     * @return The volume of the solution in liters
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Gets the molarity of the solution in mol/L.
     * 
     * @return The molarity of the solution in mol/L
     */
    public double getMolarity() {
        return molarity;
    }

    /**
     * Clears the solution by resetting the volume and molarity to zero.
     */
    public void clear() {
        volume = 0;
        molarity = 0;
    }
}
