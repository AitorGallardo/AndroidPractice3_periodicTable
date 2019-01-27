package com.example.aitor.androidpractice1_periodictable;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2ListActivity extends AppCompatActivity {

    Intent intent;
    ListView listViewOfElements;
    ArrayList<ChemicalElement> listOfElements;
    CElementAdapter allItemsAdapter;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elements_list);
        setTitle("Periodic Table");

        intent = new Intent(this, MainActivity.class);
        // mainActivityInfo = getIntent().getExtras();
        listOfElements = getIntent().getParcelableArrayListExtra("Elements");


        allItemsAdapter =   new CElementAdapter(this, listOfElements);

        listViewOfElements = (ListView)findViewById(R.id.simple_list_item);


        if(listViewOfElements != null){
            listViewOfElements.setAdapter(allItemsAdapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);

        if (searchItem != null) {
            searchView = (SearchView)searchItem.getActionView();
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
                    search(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (TextUtils.isEmpty(newText)){
                        listViewOfElements.setAdapter(allItemsAdapter);
                        // searchView.setIconified(true);
                        searchView.clearFocus();
                    } else {
                        search(newText);
                    }
                    return false;
                }
            });
            searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean queryTextFocused) {

//                    if (!searchView.isIconified()) {
//                        searchView.setIconified(true);
//                    }

                }
            });
            SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        }
        return super.onCreateOptionsMenu(menu);
    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Main2ListActivity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void search(String query) {

        ArrayList<ChemicalElement> copyOfList = new ArrayList<ChemicalElement>();
        boolean succeededSearch;

         if(query != null){

             String queryToLow = query.toLowerCase();

            for(ChemicalElement element : listOfElements) {
                succeededSearch = false;

                if(element.name.toLowerCase().contains(queryToLow)){
                    succeededSearch = true;
                }
//                if(element.atomicMass.contains(query)){  // MORE OPTIONS FOR SEARCH
//
//                }
                if(succeededSearch){
                    copyOfList.add(element);
                }
            }
        }
        CElementAdapter adaptador =
                new CElementAdapter(this,copyOfList);
        listViewOfElements.setAdapter(adaptador);
    }
}
