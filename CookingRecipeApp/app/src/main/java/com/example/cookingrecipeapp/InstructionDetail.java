package com.example.cookingrecipeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InstructionDetail extends Activity {
    DatabaseHelper database;
    TextView recipeNameText;
    EditText instructionPlane;
    String nameRecipe;
    String instruction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction_detail);
        Intent parameters=getIntent();
        nameRecipe=(String)parameters.getSerializableExtra("recipeName");
        instruction=(String)parameters.getSerializableExtra("instruction");
        instructionPlane= findViewById(R.id.instructionPlane);
        recipeNameText=findViewById(R.id.recipeNameHeader);
        database = new DatabaseHelper(this);
        recipeNameText.setText(nameRecipe);
        instructionPlane.setText(instruction);
    }

    public void saveInstruction(View view) {
        String newInstruction=instructionPlane.getText().toString();
        if(newInstruction.equals(instruction)){
            Toast.makeText(this,"Nothing to Update",Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            updateInstructionQuery(newInstruction,instruction);
        }
    }

    public void deleteInstruction(View view) {
        deleteInstructionQuery(instruction,nameRecipe);
    }


    public void deleteInstructionQuery(String instruction,String nameRecipe){
        boolean a= database.deleteInstruction(nameRecipe,instruction);
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

    public void updateInstructionQuery(String newIns,String oldIns){

        boolean a= database.updateInstruction(nameRecipe,newIns,oldIns);
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
