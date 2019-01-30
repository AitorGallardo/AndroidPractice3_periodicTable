package com.example.aitor.androidpractice1_periodictable;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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

        listViewOfElements.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                ChemicalElement  selectedItem = (ChemicalElement) parent.getItemAtPosition(position);
                    String name = selectedItem.getName().toLowerCase();
                    showElement(name);
            }
        });
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    void showElement(String tag){
        AlertDialog.Builder innputAlert = new AlertDialog.Builder(this);
        View element_receipt = getLayoutInflater().inflate(R.layout.expanded_info,null);
        Resources res = getResources();

        final TextView nameToDisplay =(TextView)element_receipt.findViewById(R.id.element_name);
        final TextView descriptionDisplay =(TextView)element_receipt.findViewById(R.id.element_description);
        final ImageView imageToDisplay = (ImageView)element_receipt.findViewById(R.id.imageViewExpInfo);

        listOfElements.stream().forEach(element -> {
            if(tag.equals(element.getName().toLowerCase())){
                final String name = element.getName() != null ? element.getName() : "";
                nameToDisplay.setText(name);
                final String description = element.getDescription() != null ? element.getDescription() : "";
                descriptionDisplay.setText(description);
                final String image = element.getImage() != null ? element.getImage() : "ic_launcher_foreground";
                // we need id type int of drawable to get the image
                final int imageId = res.getIdentifier(image , "drawable",getPackageName());
                imageToDisplay.setImageResource(imageId);

                return;
            }
        } );

        innputAlert.setView(element_receipt);
        innputAlert.show();
    } // Pop up element detailed info
}
