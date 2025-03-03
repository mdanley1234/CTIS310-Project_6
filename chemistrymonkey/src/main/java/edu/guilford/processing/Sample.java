package edu.guilford.processing;

public class Sample extends Formula {

    private double sampleMass = 0;
    private double sampleMoles = 0;

    public Sample(String formulaString) {
        super(formulaString);
    }

    // Calculated setters
    public void setSampleMass(double sampleMass) {
        this.sampleMass = sampleMass;
        sampleMoles = sampleMass / getMolarMass();
    }

    public void setSampleMoles(double sampleMoles) {
        this.sampleMoles = sampleMoles;
        sampleMass = sampleMoles * getMolarMass();
    }

    public boolean isEmpty() {
        return sampleMass == 0;
    }

    // Getters

    public double getSampleMass() {
        return sampleMass;
    }

    public double getSampleMoles() {
        return sampleMoles;
    }

}
