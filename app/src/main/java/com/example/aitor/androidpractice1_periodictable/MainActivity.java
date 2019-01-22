package com.example.aitor.androidpractice1_periodictable;

import android.app.SearchManager;
import android.content.DialogInterface;
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

    ImageButton sulfurButton;

    ListView listViewOfElements;

    Toolbar toolbar; //TESTTTTTTTTTTT

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       // toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
/*        toolbar.setLogo(R.drawable.gold_79);
        toolbar.setNavigationIcon(R.drawable.aluminum);*/
        setTitle("Periodic Table");



        // catch events
        sulfurButton = (ImageButton) findViewById(R.id.imgBtnSulfur);
        sulfurButton.setOnClickListener(this);

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

                elementArrList.add(new ChemicalElement(name, description, symbol));

            }


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading elements info.",
                    Toast.LENGTH_LONG).show();
            // Log.d("DetailsSSSSSSS-->",elementArrList);
        }
        // parse JSON

        // INICIALIZAR EL ARRAYADAPTER

            CElementAdapter adaptador =
                    new CElementAdapter(this, elementArrList);

            listViewOfElements = (ListView)findViewById(R.id.simple_list_item);

            if(listViewOfElements != null){
                listViewOfElements.setAdapter(adaptador);
            }


        //


    }

    /// FUMADON LETSGOOOOOOOOOOOOOOOOO ( TEMA SearCHBAR)

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);

        if (searchItem != null) {
            SearchView searchView = (SearchView)searchItem.getActionView();
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    //some operation
                    return false;
                }
            });
            searchView.setOnSearchClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //some operation
                }
            });
            EditText searchPlate = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
            searchPlate.setHint("Search");
            View searchPlateView = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
            searchPlateView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            // use this method for search process
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // use this method when query submitted
                    // Toast.makeText(context, query, Toast.LENGTH_SHORT).show();  PEAEEEEEEEE
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    // use this method for auto complete search process
                    return false;
                }
            });
            SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        }
        // BUTTON TO GO TO LISTS



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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBtnSulfur:
                // showElement();
                break;
        }
    }

//    void showElement(){           /// MOSTRAR MAS DETAILS ESTARIA AHI EL ASUNTO
//        AlertDialog.Builder innputAlert = new AlertDialog.Builder(this);
//        View element_receipt = getLayoutInflater().inflate(R.layout.expanded_info,null);
//        Gold gold = new Gold();
//        final TextView name =(TextView)element_receipt.findViewById(R.id.element_name);
//        name.setText(gold.getName()); /// PORA AKAAAA SIIIII
//        final TextView description =(TextView)element_receipt.findViewById(R.id.element_description);
//        description.setText(gold.getDescription()); /// PORA AKAAAA SIIIII
//        // innputAlert.setTitle("INFO");
//        innputAlert.setView(element_receipt);
//
//        innputAlert.show();
//    }

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


}
