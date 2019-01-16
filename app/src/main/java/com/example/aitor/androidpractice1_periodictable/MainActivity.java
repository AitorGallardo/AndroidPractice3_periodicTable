package com.example.aitor.androidpractice1_periodictable;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
        innputAlert.setTitle("INFO");
        innputAlert.setView(element_receipt);

        innputAlert.show();
    }
}
