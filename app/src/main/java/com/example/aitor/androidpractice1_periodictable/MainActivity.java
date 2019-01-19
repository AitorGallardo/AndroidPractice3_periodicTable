package com.example.aitor.androidpractice1_periodictable;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<ChemicalElement> elementsList = new ArrayList<ChemicalElement>();

    ImageButton sulfurButton;

    Toolbar toolbar; //TESTTTTTTTTTTT

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
/*        toolbar.setLogo(R.drawable.gold_79);
        toolbar.setNavigationIcon(R.drawable.aluminum);*/
        setTitle("LETS GOOOO");

        elementsList.add(new Gold());


        // catch events
        sulfurButton = (ImageButton) findViewById(R.id.imgBtnSulfur);
        sulfurButton.setOnClickListener(this);
    }

    /// FUMADON LETSGOOOOOOOOOOOOOOOOO

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
                showElement();
                break;
        }
    }

    void showElement(){
        AlertDialog.Builder innputAlert = new AlertDialog.Builder(this);
        View element_receipt = getLayoutInflater().inflate(R.layout.expanded_info,null);
        Gold gold = new Gold();
        final TextView name =(TextView)element_receipt.findViewById(R.id.element_name);
        name.setText(gold.getName()); /// PORA AKAAAA SIIIII
        final TextView description =(TextView)element_receipt.findViewById(R.id.element_description);
        description.setText(gold.getDescription()); /// PORA AKAAAA SIIIII
        // innputAlert.setTitle("INFO");
        innputAlert.setView(element_receipt);

        innputAlert.show();
    }
}
