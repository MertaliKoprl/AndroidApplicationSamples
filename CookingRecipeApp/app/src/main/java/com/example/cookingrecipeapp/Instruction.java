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

public class Instruction extends Activity {

    Spinner recipeName;
    EditText instruction;
    Button addInstruction;
    DatabaseHelper database;
    boolean isEmpty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_instruction);
        database = new DatabaseHelper(this);
        recipeName= findViewById(R.id.comboBox);
        addInstruction=findViewById(R.id.addinstructionButtoon);
        instruction=findViewById(R.id.instructionField1);
        isEmpty=true;
        fillSpinner();
        if(!isEmpty){
            addInstruction.setVisibility(View.VISIBLE);//MEANS THERE IS DATA ON SPINNER
        }else{
            addInstruction.setVisibility(View.INVISIBLE);//MEANS THERE IS DATA ON SPINNER

        }



    }

    public void fillSpinner(){

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
            recipeName.setAdapter(adapter);
        }
    }

    public void instructionAction(View view) {
        String instruction= this.instruction.getText().toString();
        String recipeName= this.recipeName.getSelectedItem().toString();
        boolean insert= database.addInstruction(recipeName,instruction);
        if(insert){
            Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_SHORT);
        }
        else{
            Toast.makeText(getApplicationContext(),"NOT Inserted",Toast.LENGTH_LONG);
        }

        finish();



    }
}
