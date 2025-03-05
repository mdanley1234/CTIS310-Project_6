package edu.guilford.chemtools;

import java.io.FileReader;

import com.opencsv.CSVReader;

public class Element {

    // Element attributes
    private final String atomicSymbol;
    private String elementName;
    private int atomicNumber;
    private double atomicMass;
    private int valenceElectrons;

    // Periodic Table File Location
    private static final String ELEMENTS_FILE = "chemistrymonkey/src/main/java/edu/guilford/chemtools/elementInformation.csv";

    // Pass in element atomic symbol
    public Element(String atomicSymbol) {
        this.atomicSymbol = atomicSymbol;
        buildElement();
    }

    // Build the rest of the element attributes using ELEMENTS_FILE
    private void buildElement() {
        try {
            FileReader filereader = new FileReader(ELEMENTS_FILE);
            CSVReader csvReader = new CSVReader(filereader);
            String[] line;
            boolean elementFound = false;

            // Search for atomic symbol
            while (!elementFound && (line = csvReader.readNext()) != null) {
                if (line[2].equals(atomicSymbol)) {
                    elementFound = true;

                    // Build element
                    atomicNumber = Integer.parseInt(line[0]);
                    elementName = line[1];
                    atomicMass = Double.parseDouble(line[3]);
                    calculateValenceElectrons();
                }
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Calculate valence electrons
    private void calculateValenceElectrons() {
        // First shell case
        if (atomicNumber <= 2) {
            valenceElectrons = atomicNumber;
        }
        // Remaining shell case
        valenceElectrons = (atomicNumber - 2) % 8;
        if (valenceElectrons == 0) {
            valenceElectrons = 8;
        }
    }

    //  Getters
    public String getAtomicSymbol() {
        return atomicSymbol;
    }

    public String getElementName() {
        return elementName;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public double getAtomicMass() {
        return atomicMass;
    }

    public int getValenceElectrons() {
        return valenceElectrons;
    }

    
}
