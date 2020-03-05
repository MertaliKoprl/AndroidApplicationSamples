package com.example.cookingrecipeapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Category extends Activity {

    EditText categoryField;
    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_category);
        categoryField = findViewById(R.id.categoryField);
        database= new DatabaseHelper(this);
    }

    public void addCategory(View view) {
        String CategoryName = categoryField.getText().toString();
        boolean insert= database.addCategory(CategoryName);
        if(insert){
            Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_SHORT);
        }
        else{
            Toast.makeText(getApplicationContext(),"NOT Inserted",Toast.LENGTH_LONG);
        }

        finish();
    }

}
