package com.example.aitor.androidpractice1_periodictable;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<ChemicalElement> elementArrList = new ArrayList<ChemicalElement>();

    // Img View Buttons
    ImageButton sulfurButton;
    ImageButton potassiumButton;
    ImageButton ironButton;
    ImageButton manganeseButton;
    ImageButton sodiumButton;
    ImageButton magnesiumButton;


Bundle bundle;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Periodic Table");

        // INTENT
        intent = new Intent(this,Main2ListActivity.class);



        // img view events
        sulfurButton = (ImageButton) findViewById(R.id.sulfur);
        sulfurButton.setOnClickListener(this);
        potassiumButton = (ImageButton) findViewById(R.id.potassium);
        potassiumButton.setOnClickListener(this);
        ironButton = (ImageButton) findViewById(R.id.iron);
        ironButton.setOnClickListener(this);
        manganeseButton = (ImageButton) findViewById(R.id.manganese);
        manganeseButton.setOnClickListener(this);
        sodiumButton = (ImageButton) findViewById(R.id.sodium);
        sodiumButton.setOnClickListener(this);
        magnesiumButton = (ImageButton) findViewById(R.id.magnesium);
        magnesiumButton.setOnClickListener(this);


        // parse JSON



        try {
            JSONObject obj = new JSONObject(loadJson());
            JSONArray m_jArry = obj.getJSONArray("elements");
            // ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            // HashMap<String, String> m_li;


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                // Log.d("Details-->", jo_inside.getString("formule"));

                String name = jo_inside.getString("name") != null ? jo_inside.getString("name"): "";
                String description = jo_inside.getString("description") != null ? jo_inside.getString("description"): "";
                String symbol = jo_inside.getString("symbol") != null ? jo_inside.getString("symbol"): "";
                String tag = jo_inside.getString("tag") != null ? jo_inside.getString("tag"): "";

                elementArrList.add(new ChemicalElement(name, description, symbol, tag));

            }


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading elements info.",
                    Toast.LENGTH_LONG).show();
            // Log.d("DetailsSSSSSSS-->",elementArrList);
        }

    }

    // DECLARABA EL MENU

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
/*
    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            findViewById(R.id.default_title).setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }*/

    /// LETS GOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    //

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        showElement(tag);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void showElement(String tag){
        AlertDialog.Builder innputAlert = new AlertDialog.Builder(this);
        View element_receipt = getLayoutInflater().inflate(R.layout.expanded_info,null);

        final TextView name =(TextView)element_receipt.findViewById(R.id.element_name);
        final TextView description =(TextView)element_receipt.findViewById(R.id.element_description);
        elementArrList.stream().forEach(element -> {
            if(tag.equals(element.getTag())){
                name.setText(element.getName());
                description.setText(element.getDescription());
                return;
            }
        } );
        innputAlert.setView(element_receipt);
        innputAlert.show();
    }

    public String loadJson() {
        String json = null;
        try {

            InputStream is = getAssets().open("elementsData.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Error loading elements info.",
                    Toast.LENGTH_LONG).show();
            return null;
        }
        return json;

    }

    public void navigate(){
        intent.putParcelableArrayListExtra("Elements", (ArrayList<? extends Parcelable>) elementArrList);
        startActivityForResult(intent,1234);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1234 && resultCode==RESULT_OK) {
            String res = data.getExtras().getString("result");
        }
    }


}
