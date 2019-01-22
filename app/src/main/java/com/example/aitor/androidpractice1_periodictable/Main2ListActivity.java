package com.example.aitor.androidpractice1_periodictable;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2ListActivity extends AppCompatActivity {
    Bundle mainActivityInfo;
    Intent intent;
    ListView listViewOfElements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elements_list);

        intent = new Intent(this, MainActivity.class);
        // mainActivityInfo = getIntent().getExtras();
        ArrayList<ChemicalElement> listOfElements = getIntent().getParcelableArrayListExtra("Elements");


        CElementAdapter adaptador =
                new CElementAdapter(this, listOfElements);

        listViewOfElements = (ListView)findViewById(R.id.simple_list_item);

        if(listViewOfElements != null){
            listViewOfElements.setAdapter(adaptador);
        }
    }

}
