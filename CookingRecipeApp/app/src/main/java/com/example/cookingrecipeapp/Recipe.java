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

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Recipe extends Activity {

    DatabaseHelper database;
    EditText recipeName;
    Spinner categoryCombo;
    Button addRecipeBtn;
    boolean isEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe);
        addRecipeBtn=findViewById(R.id.addRecipeBtn);
        recipeName = findViewById(R.id.recipeName);
        categoryCombo = findViewById(R.id.categoryCombo);
        database = new DatabaseHelper(this);
        addRecipeBtn.setVisibility(View.INVISIBLE);
        isEmpty=true;
        fillSpinner();
        if(!isEmpty){
            addRecipeBtn.setVisibility(View.VISIBLE);//MEANS THERE IS DATA ON SPINNER
        }
    }

    public void fillSpinner() {
        Cursor cursor = database.getAllCateGories();
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
            categoryCombo.setAdapter(adapter);
        }
    }

    public void addRecipe(View view) {
        String RecipeName= recipeName.getText().toString();
        String CategoryName= categoryCombo.getSelectedItem().toString();
        boolean insert= database.addRecipe(RecipeName,CategoryName);
        if(insert){
            Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_SHORT);
        }
        else{
            Toast.makeText(getApplicationContext(),"NOT Inserted",Toast.LENGTH_LONG);
        }
        setResult(10);
        finish();


    }
}
