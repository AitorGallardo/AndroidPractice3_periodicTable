package com.example.aitor.androidpractice1_periodictable;

public class ChemicalElement {

    protected String name;
    protected String description;
    protected String symbol;
    protected int atomicNumber;
    protected String atomicMass;
    protected String family;
    protected String electronicConfig;

    public ChemicalElement(String name, String description, String symbol){
        this.name = name;
        this.description = description;
        this.symbol = symbol;

    }


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
