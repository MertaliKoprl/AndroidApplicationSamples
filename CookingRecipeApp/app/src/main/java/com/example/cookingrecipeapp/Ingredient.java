package com.example.cookingrecipeapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Ingredient extends Activity {
    DatabaseHelper database;
    Spinner comboBox;
    EditText ingrediantName;
Button ingButton;
boolean isEmpty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ingredient);
        comboBox = findViewById(R.id.recipeComboBox);
        ingrediantName = findViewById(R.id.IngredientName);
        database = new DatabaseHelper(this);
        ingButton=findViewById(R.id.ingButton);
        isEmpty=true;
        fillSpinner();
        if(!isEmpty){
            ingButton.setVisibility(View.VISIBLE);//MEANS THERE IS DATA ON SPINNER
        }
    }

    public void fillSpinner() {
        Cursor cursor = database.getAllRecipe();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(),"NO DATA , CREATE CATEGORY FIRST",Toast.LENGTH_LONG);
            System.out.println("No DATA !!!!");
        } else {
            List<String> categories = new ArrayList<>();
            while (cursor.moveToNext()) {
                categories.add(cursor.getString(0));
            }
            isEmpty=false;
            ArrayAdapter<String> adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,categories);
            comboBox.setAdapter(adapter);
        }
    }


    public void IngrediantAction(View view) {//Adds ingrediants
        String recipeName= comboBox.getSelectedItem().toString();
        String IngrediantName= ingrediantName.getText().toString();
        boolean insert=database.addIngrediant(recipeName,IngrediantName);
        if(insert){
            Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_SHORT);
        }
        else{
            Toast.makeText(getApplicationContext(),"NOT Inserted",Toast.LENGTH_LONG);
        }
        finish();
    }
}
