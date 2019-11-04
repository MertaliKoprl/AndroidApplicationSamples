package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    double weight;
    double height;
    private EditText weightInput;
    private EditText heightInput;
    private EditText resultindex;
    private TextView weightL;
    private TextView heightL;
    private TextView bodyIndex;
    private TextView situation;
    private double bodyMassIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        height = 0.0;
        weight = 0.0;
        weightL = findViewById(R.id.weightL);
        heightL = findViewById(R.id.heightL);
        situation = findViewById(R.id.situation);
        resultindex = findViewById(R.id.resultindex);
        bodyIndex = findViewById(R.id.bodyMassIndex);
        resultindex.setEnabled(false);

        weightInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                weight = Double.parseDouble(s.toString());
                weightL.setText("Weight : " + weight + " kg.");
                showIndex();
            }}

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        heightInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                height = Double.parseDouble(s.toString());
                heightL.setText("Height : " + height + " m.");
                showIndex();}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void showIndex() {
        bodyMassIndex = weight / (height * height);
        resultindex.setText("" + bodyMassIndex);
        if (bodyMassIndex < 20) {
            situation.setText("Zayif");
        } else {
            if (bodyMassIndex < 25) {
                situation.setText("Normal");
            } else {
                if (bodyMassIndex < 30) {
                    situation.setText("Hafif Kilolu");
                } else {
                    if (bodyMassIndex < 35) {
                        situation.setText("Obez");
                    } else {
                        if (bodyMassIndex < 45) {
                            situation.setText("Asiri Obez");
                        } else {
                            if (bodyMassIndex < 50) {
                                situation.setText("Ustun Obez");
                            } else {
                                situation.setText("Olumcul Obez");
                            }
                        }
                    }
                }
            }
        }
    }


}
