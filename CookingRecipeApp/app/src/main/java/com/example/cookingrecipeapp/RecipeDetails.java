package com.example.cookingrecipeapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class RecipeDetails extends Activity {

    TextView recipeName;
    ListView ingredientsList;
    ListView instructionList;
    String recipename;
    DatabaseHelper database;
    ArrayAdapter<String> ListAdapter;
    List<String> itemsIng;
    List<String> itemsIns;
    String Category;
    TextView categoryField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_details);
        final Intent recipeDetail = getIntent();
        recipename = (String) recipeDetail.getSerializableExtra("recipeName");
        database = new DatabaseHelper(this);
        Category = getCategory();
        categoryField=findViewById(R.id.categoryText);
        categoryField.setText(Category);
        recipeName = findViewById(R.id.recipeNameLabel);
        recipeName.setText(recipename);
        ingredientsList = findViewById(R.id.ingredientList);
        instructionList = findViewById(R.id.instructionList);
        instructionList.setAdapter(fillListsInstruction());
        ingredientsList.setAdapter(fillListsIngredients());

        ingredientsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent recipeDetails = new Intent(RecipeDetails.this, IngredientDetail.class);
                recipeDetails.putExtra("ingredients", itemsIng.get(position));
                recipeDetails.putExtra("recipeName", recipename);
                startActivityForResult(recipeDetails, 10);
            }
        });
        instructionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String a = itemsIns.get(position);

                Intent recipeDetails = new Intent(RecipeDetails.this, InstructionDetail.class);
                recipeDetails.putExtra("instruction", a);
                recipeDetails.putExtra("recipeName", recipename);
                startActivityForResult(recipeDetails, 10);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10)
            refreshList();

    }

    public void refreshList() {
        ingredientsList.setAdapter(fillListsIngredients());
        instructionList.setAdapter(fillListsInstruction());

    }

    public String getCategory() {
        Cursor c = database.getCategory(recipename);
        String b="";
        while (c.moveToNext()) {
            b=c.getString(0);
        }
        return b;
    }

    public ArrayAdapter<String> fillListsInstruction() {

        Cursor cursor = database.getInstructions(recipename);
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "NO DATA , CREATE CATEGORY FIRST", Toast.LENGTH_LONG);
            System.out.println("No DATA !!!!");
        } else {
            itemsIns = new ArrayList<>();
            itemsIns.clear();
            while (cursor.moveToNext()) {
                itemsIns.add(cursor.getString(0));
            }
            ListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsIns);
            return ListAdapter;
        }
        return null;

    }

    public ArrayAdapter<String> fillListsIngredients() {

        Cursor cursor = database.getIngredients(recipename);
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "NO DATA , CREATE CATEGORY FIRST", Toast.LENGTH_LONG);
            System.out.println("No DATA !!!!");
        } else {
            itemsIng = new ArrayList<>();
            itemsIng.clear();
            while (cursor.moveToNext()) {
                itemsIng.add(cursor.getString(0));
            }
            ListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsIng);
            return ListAdapter;
        }
        return null;
    }


    public void deleteRecipe(View view) {
        database.deleteRecipe(recipename);
        Toast.makeText(this, "Recipe Deleted", Toast.LENGTH_SHORT).show();
        setResult(10);
        finish();
    }
}
