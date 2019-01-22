package com.example.aitor.androidpractice1_periodictable;

import android.os.Parcel;
import android.os.Parcelable;

public class ChemicalElement implements Parcelable {

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


    protected ChemicalElement(Parcel in) {
        name = in.readString();
        description = in.readString();
        symbol = in.readString();
        atomicNumber = in.readInt();
        atomicMass = in.readString();
        family = in.readString();
        electronicConfig = in.readString();
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



    public static final Creator<ChemicalElement> CREATOR = new Creator<ChemicalElement>() {
        @Override
        public ChemicalElement createFromParcel(Parcel in) {
            return new ChemicalElement(in);
        }

        @Override
        public ChemicalElement[] newArray(int size) {
            return new ChemicalElement[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(symbol);
        dest.writeInt(atomicNumber);
        dest.writeString(atomicMass);
        dest.writeString(family);
        dest.writeString(electronicConfig);
    }
}
