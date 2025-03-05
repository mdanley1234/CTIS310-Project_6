package edu.guilford.chemtools;

public class Solution {

    private final Sample sample; // Compound
    private double volume; // Volume of solution
    private double molarity; // Molarity of solution

    // Generates an empty solution for a certain Formula
    public Solution(Sample sample) {
        this.sample = sample;
    }

    // Calculated setters
    public void setVolume(double volume) {
        this.volume = volume;
        if (sample.isEmpty() && molarity > 0) {
            sample.setSampleMoles(volume * molarity);
        }
        else if (!sample.isEmpty()) {
            molarity = sample.getSampleMoles() / volume;
        }
    }

    public void setMolarity(double molarity) {
        this.molarity = molarity;
        if (sample.isEmpty() && volume > 0) {
            sample.setSampleMoles(volume * molarity);
        }
        else if (!sample.isEmpty()) {
            volume = sample.getSampleMoles() / molarity;
        }   
    }

    // Getters
    public Sample getSample() {
        return sample;
    }

    public double getVolume() {
        return volume;
    }

    public double getMolarity() {
        return molarity;
    }

    // Clear method
    public void clear() {
        volume = 0;
        molarity = 0;
    }
}
