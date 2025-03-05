package edu.guilford.chemtools;

import java.io.FileReader;

import com.opencsv.CSVReader;

/**
 * The Element class represents a chemical element, storing its atomic symbol,
 * name, atomic number, atomic mass, and valence electrons. It loads this 
 * information from a CSV file containing element data.
 */
public class Element {

    // Element attributes
    private final String atomicSymbol;
    private String elementName;
    private int atomicNumber;
    private double atomicMass;
    private int valenceElectrons;

    // Periodic Table File Location
    private static final String ELEMENTS_FILE = "chemistrymonkey/src/main/java/edu/guilford/chemtools/elementInformation.csv";

    /**
     * Constructor that creates an Element object with the provided atomic symbol.
     * 
     * @param atomicSymbol The atomic symbol of the element (e.g., "H" for Hydrogen).
     */
    public Element(String atomicSymbol) {
        this.atomicSymbol = atomicSymbol;
        buildElement();
    }

    /**
     * Builds the element attributes using the ELEMENTS_FILE. It reads data 
     * from the CSV file to initialize the atomic number, element name, 
     * atomic mass, and valence electrons.
     */
    private void buildElement() {
        try {
            FileReader filereader = new FileReader(ELEMENTS_FILE);
            CSVReader csvReader = new CSVReader(filereader);
            String[] line;
            boolean elementFound = false;

            // Search for the element based on the atomic symbol
            while (!elementFound && (line = csvReader.readNext()) != null) {
                if (line[2].equals(atomicSymbol)) {
                    elementFound = true;

                    // Set element properties
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

    /**
     * Calculates the number of valence electrons based on the atomic number. 
     * This method uses a simple model based on the periodic table.
     */
    private void calculateValenceElectrons() {
        // First shell case (Hydrogen and Helium)
        if (atomicNumber <= 2) {
            valenceElectrons = atomicNumber;
        } else {
            // Remaining shells
            valenceElectrons = (atomicNumber - 2) % 8;
            if (valenceElectrons == 0) {
                valenceElectrons = 8;
            }
        }
    }

    // Getters

    /**
     * Gets the atomic symbol of the element.
     * 
     * @return The atomic symbol (e.g., "H" for Hydrogen).
     */
    public String getAtomicSymbol() {
        return atomicSymbol;
    }

    /**
     * Gets the name of the element.
     * 
     * @return The name of the element (e.g., "Hydrogen").
     */
    public String getElementName() {
        return elementName;
    }

    /**
     * Gets the atomic number of the element.
     * 
     * @return The atomic number of the element.
     */
    public int getAtomicNumber() {
        return atomicNumber;
    }

    /**
     * Gets the atomic mass of the element.
     * 
     * @return The atomic mass of the element in atomic mass units (AMU).
     */
    public double getAtomicMass() {
        return atomicMass;
    }

    /**
     * Gets the number of valence electrons of the element.
     * 
     * @return The number of valence electrons.
     */
    public int getValenceElectrons() {
        return valenceElectrons;
    }

}