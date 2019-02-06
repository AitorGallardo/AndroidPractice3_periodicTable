package com.example.aitor.androidpractice1_periodictable;

import android.os.Parcel;
import android.os.Parcelable;

public class ChemicalElement implements Parcelable { // allows send list of this type of object using intents

    protected String name;
    protected String description;
    protected String symbol;
    protected int atomicNumber;
    protected Double atomicMass;
    protected String family;
    protected String electronicConfig;
    protected String tag;
    protected String category;
    protected String image;
    protected String url;

    public ChemicalElement(String name, Double atomicMass, String category, String description, String symbol, String image, String url){
        this.name = name;
        this.atomicMass = atomicMass;
        this.category = category;
        this.description = description;
        this.symbol = symbol;
        this.image = image;
        this.url = url;

    }


    protected ChemicalElement(Parcel in) {
        name = in.readString();
        description = in.readString();
        symbol = in.readString();
        atomicNumber = in.readInt();
        atomicMass = in.readDouble();
        family = in.readString();
        electronicConfig = in.readString();
        tag = in.readString();
        image = in.readString();
        url = in.readString();
        category = in.readString();
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
    public Double getAtomicMass() {
        return atomicMass;
    }
    public String getFamily() {
        return family;
    }
    public String getElectronicConfig() {
        return electronicConfig;
    }
    public String getTag() {
        return tag;
    }
    public String getImage() {
        return image;
    }


    public String getUrl() {
        return url;
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
        dest.writeDouble(atomicMass);
        dest.writeString(family);
        dest.writeString(electronicConfig);
        dest.writeString(tag);
        dest.writeString(image);
        dest.writeString(url);
        dest.writeString(category);
    }
}
