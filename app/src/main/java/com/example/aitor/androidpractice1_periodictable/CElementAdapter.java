package com.example.aitor.androidpractice1_periodictable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CElementAdapter extends ArrayAdapter<ChemicalElement> {

    ArrayList<ChemicalElement> elements;

    public CElementAdapter(Context context,ArrayList<ChemicalElement> element) {
        super(context, android.R.layout.activity_list_item, element);
        this.elements = element;
    }

@Override
 public View getView(int position, View convertView, ViewGroup parent){
     LayoutInflater inflater = LayoutInflater.from(getContext());
     View item = inflater.inflate(R.layout.each_element_view, null);

     TextView name = (TextView)item.findViewById(R.id.element_view_name);
     name.setText(this.elements.get(position).getName());

     TextView description = (TextView)item.findViewById(R.id.element_view_description);
     description.setText(this.elements.get(position).getDescription());
     return(item);
 }
}
