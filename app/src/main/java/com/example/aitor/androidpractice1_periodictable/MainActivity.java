package com.example.aitor.androidpractice1_periodictable;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<ChemicalElement> elementArrList = new ArrayList<>();

    Intent listIntent;
    Intent gameIntent;

    LinearLayout gallery;
    String jsonFileName = "elementsData.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Periodic Table");

        listIntent = new Intent(this,Main2ListActivity.class);
        gameIntent = new Intent(this,Main3GameActivity.class);

        parseJson(loadJson(jsonFileName));
        inflateLayoutWithImages();



    }

    public void inflateLayoutWithImages(){
        gallery = findViewById(R.id.gallery);
        LayoutInflater inflater = LayoutInflater.from(this);
        Resources res = getResources();

        for(int i=0; i < elementArrList.size(); i=i+2){
            View view = inflater.inflate(R.layout.main_images, gallery, false);

            ImageView imageview1 = view.findViewById(R.id.image1);
            imageview1.setOnClickListener(this);
            ImageView imageview2 = view.findViewById(R.id.image2);
            imageview2.setOnClickListener(this);

            if(elementArrList.get(i) != null){
                final String image1 = elementArrList.get(i).getImage() != null ? elementArrList.get(i).getImage() : "ic_launcher_foreground";
                final String name1 = elementArrList.get(i).getName() != null ? elementArrList.get(i).getName() : "ic_launcher_foreground";
                final int imageId1 = res.getIdentifier(image1 , "drawable",getPackageName());
                imageview1.setImageResource(imageId1);
                imageview1.setTag(name1.toLowerCase());
            } else {
                final String image1 = "ic_launcher_foreground";
                final int imageId1 = res.getIdentifier(image1 , "drawable",getPackageName());
                imageview1.setImageResource(imageId1);
            }

            if(elementArrList.get(i+1) != null){
                final String image2 = elementArrList.get(i+1).getImage() != null ? elementArrList.get(i+1).getImage() : "ic_launcher_foreground";
                final String name2 = elementArrList.get(i+1).getName() != null ? elementArrList.get(i+1).getName() : "ic_launcher_foreground";
                final int imageId2 = res.getIdentifier(image2 , "drawable",getPackageName());
                imageview2.setImageResource(imageId2);
                imageview2.setTag(name2.toLowerCase());
            } else{
                final String image2 = "ic_launcher_foreground";
                final int imageId2 = res.getIdentifier(image2 , "drawable",getPackageName());
                imageview2.setImageResource(imageId2);
            }

            gallery.addView(view);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        showElement(tag);
    
    }

    //region Handle MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.listIcon:
                navigateToList();
                return true;
            case R.id.gameIcon:
                navigateToGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //endregion

    @RequiresApi(api = Build.VERSION_CODES.N)
    void showElement(String tag){
        AlertDialog.Builder innputAlert = new AlertDialog.Builder(this);
        View element_receipt = getLayoutInflater().inflate(R.layout.expanded_info,null);
        Resources res = getResources();

        final TextView nameToDisplay =(TextView)element_receipt.findViewById(R.id.element_name);
        final TextView aMassToDisplay =(TextView)element_receipt.findViewById(R.id.element_aMass);
        final TextView categoryToDisplay =(TextView)element_receipt.findViewById(R.id.element_category);
        final TextView descriptionDisplay =(TextView)element_receipt.findViewById(R.id.element_description);
        final ImageView imageToDisplay = (ImageView)element_receipt.findViewById(R.id.imageViewExpInfo);
        final TextView urlToDisplay =(TextView)element_receipt.findViewById(R.id.urlView);
        final TextView share =(TextView)element_receipt.findViewById(R.id.shareLink);

        elementArrList.stream().forEach(element -> {
            if(tag.equals(element.getName().toLowerCase())){
                final String name = element.getName() != null ? element.getName() : "";
                nameToDisplay.setText(name);
                final Double aMass = element.getAtomicMass() != null ? element.getAtomicMass() : -1.0;
                aMassToDisplay.setText(aMass.toString());
                final String category = element.getCategory() != null ? element.getCategory() : "";
                categoryToDisplay.setText(category);
                final String description = element.getDescription() != null ? element.getDescription() : "";
                descriptionDisplay.setText(description);
                final String url = element.getUrl() != null ? element.getUrl() : "";
                urlToDisplay.setText(url);
                final String image = element.getImage() != null ? element.getImage() : "ic_launcher_foreground";
                // we need id type int of drawable to get the image
                final int imageId = res.getIdentifier(image , "drawable",getPackageName());
                imageToDisplay.setImageResource(imageId);

                return;
            }
        } );

        urlToDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlToDisplay.getText().toString();
                Intent intnt = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intnt);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out this element. "+urlToDisplay.getText().toString());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        innputAlert.setView(element_receipt);
        innputAlert.show();
    } // Pop up element detailed info

    public void navigateToList(){
        listIntent.putParcelableArrayListExtra("Elements", (ArrayList<? extends Parcelable>) elementArrList);
        startActivityForResult(listIntent,1234);
    }

    public void navigateToGame(){
        gameIntent.putExtra("name","hola");
        startActivityForResult(gameIntent,1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String loadJson(String fileName) {
        String json = null;
        try {

            InputStream is = getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Error loading elements files.",
                    Toast.LENGTH_LONG).show();
            return null;
        }
        return json;

    }

    public void parseJson(String jsonFile){

        try {
            JSONObject obj = new JSONObject(jsonFile);
            JSONArray m_jArry = obj.getJSONArray("elements");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);

                String name = jo_inside.getString("name") != null ? jo_inside.getString("name"): "";
                String description = jo_inside.getString("summary") != null ? jo_inside.getString("summary"): "";
                String symbol = jo_inside.getString("symbol") != null ? jo_inside.getString("symbol"): "";
                String image = jo_inside.getString("image") != null ? jo_inside.getString("image"): "";
                String url = jo_inside.getString("source") != null ? jo_inside.getString("source"): "";
                Double aMass = jo_inside.getDouble("atomic_mass") != -1.0 ? jo_inside.getDouble("atomic_mass"): -1.0;
                String category = jo_inside.getString("category") != null ? jo_inside.getString("category"): "";
                // to set first Character to UpperCase
                String catFirstCharToUpper = Character.toUpperCase(category.charAt(0)) + category.substring(1);

                elementArrList.add(new ChemicalElement(name,aMass,catFirstCharToUpper, description, symbol, image, url));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading elements files.",
                    Toast.LENGTH_LONG).show();
        }
    }


}
