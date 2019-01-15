package com.example.aitor.androidpractice1_periodictable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ChemicalElement> elementsList = new ArrayList<ChemicalElement>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elementsList.add(new Gold());
    }


}
