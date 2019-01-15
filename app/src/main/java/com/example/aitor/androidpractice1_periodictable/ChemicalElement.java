package com.example.aitor.androidpractice1_periodictable;

public abstract class ChemicalElement {

    protected String name;
    protected String description;
    protected String symbol;
    protected int atomicNumber;
    protected String atomicMass;
    protected String family;
    protected String electronicConfig;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }
    public String getAtomicMass() {
        return atomicMass;
    }

    public String getFamily() {
        return family;
    }

    public String getElectronicConfig() {
        return electronicConfig;
    }

}
