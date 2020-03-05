package com.example.cookingrecipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView recipeList;
    Context context;
    DatabaseHelper database;
    ArrayAdapter<String> ListAdapter;
    List<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this.getApplicationContext();
        recipeList = (ListView) findViewById(R.id.recipeList);
         database = new DatabaseHelper(this);
         recipeList.setAdapter( fillList());
       // database.cleanDb();
         recipeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent recipeDetails= new Intent(MainActivity.this, RecipeDetails.class);
                recipeDetails.putExtra("recipeName",items.get(position));
                 startActivityForResult(recipeDetails,10);
             }
         });
    }

    public ArrayAdapter<String> fillList(){
        Cursor cursor = database.getAllRecipe();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(),"NO DATA , CREATE CATEGORY FIRST",Toast.LENGTH_LONG);
            System.out.println("No DATA !!!!");
        } else {
           items = new ArrayList<>();
            items.clear();
            while (cursor.moveToNext()) {
                items.add(cursor.getString(0));
            }
            ListAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
           return ListAdapter;

        }

        return null;
    }

    public void refreshList() {
        recipeList.setAdapter(fillList());
    }


    public void addCategory(View view) {
        Intent categoryIntent = new Intent(MainActivity.this, Category.class);
        startActivity(categoryIntent);
        refreshList();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==10)
        refreshList();

    }

    public void addRecipe(View view) {
        Intent recipeIntent = new Intent(MainActivity.this, Recipe.class);
        startActivityForResult(recipeIntent,10);

    }

    public void addIngredient(View view) {
        Intent ingredientIntent = new Intent(MainActivity.this, Ingredient.class);
        startActivityForResult(ingredientIntent,10);

    }

    public void addInstruction(View view) {
        Intent instructionIntent = new Intent(MainActivity.this, Instruction.class);
        startActivity(instructionIntent);
    }
}
