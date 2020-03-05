package com.example.cookingrecipeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IngredientDetail extends Activity {

    DatabaseHelper database;
    TextView recipeNameText;
    EditText ingredientPlane;
    String nameRecipe;
    String ingredient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_detail);
        Intent parameters=getIntent();
        nameRecipe=(String)parameters.getSerializableExtra("recipeName");
        ingredient=(String)parameters.getSerializableExtra("ingredients");
        database = new DatabaseHelper(this);
        recipeNameText=findViewById(R.id.recipeNameTextIng);
        ingredientPlane= findViewById(R.id.ingredientPlane);
        recipeNameText.setText(nameRecipe);
        ingredientPlane.setText(ingredient);
    }
    public void updateIngredient(View view) {
        String newIngredient=ingredientPlane.getText().toString();
        if(newIngredient.equals(ingredient)){
            Toast.makeText(this,"Nothing to Update",Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            updateIngredientQuery(newIngredient,ingredient);
        }
    }


    public void deleteIngredient(View view) {
        deleteIngredientQuery(ingredient,nameRecipe);
    }



    public void deleteIngredientQuery(String ingredient,String nameRecipe){
        boolean a= database.deleteIngredient(nameRecipe,ingredient);
        if (a== true) {
            Toast.makeText(getApplicationContext(),"Updated !",Toast.LENGTH_SHORT).show();
            setResult(10);
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void updateIngredientQuery(String newIng,String oldIng){

        boolean a= database.updateIngredient(nameRecipe,newIng,oldIng);
        if (a== true) {
            Toast.makeText(getApplicationContext(),"Updated !",Toast.LENGTH_SHORT).show();
            setResult(10);
        finish();
        }
        else{
            Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
        finish();
        }
    }


}
