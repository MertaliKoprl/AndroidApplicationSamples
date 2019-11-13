package com.example.movietriviatest;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class Subjects extends Activity {


    private CheckBox historyCheckBox;
    private CheckBox actionCheckBox;
    private CheckBox sciFiCheckBox;
    private TextView historyCount;
    private TextView actionCount;
    private TextView sciFiCount;
    private SeekBar historySeekBar;
    private SeekBar actionSeekBar;
    private SeekBar sciFiSeekBar;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        Button restartExam = (Button) findViewById(R.id.startNewExamBtn);
        Button goBack = (Button) findViewById(R.id.goBackBtn);
        historyCheckBox = (CheckBox) findViewById(R.id.historyCheckBox);
        actionCheckBox = (CheckBox) findViewById(R.id.actionCheckBox);
        sciFiCheckBox = (CheckBox) findViewById(R.id.sciFiCheckBox);
        historyCount = (TextView) findViewById(R.id.historyCount);
        actionCount = (TextView) findViewById(R.id.actionCount);
        sciFiCount = (TextView) findViewById(R.id.sciFiCount);
        historySeekBar = (SeekBar) findViewById(R.id.historySeekBar);
        actionSeekBar = (SeekBar) findViewById(R.id.actionSeekBar);
        sciFiSeekBar = (SeekBar) findViewById(R.id.sciFiSeekBar);
        restartExam.setOnClickListener(restartExamClick);
        goBack.setOnClickListener(goBackClick);
        historyCheckBox.setOnCheckedChangeListener(optionsClick);
        actionCheckBox.setOnCheckedChangeListener(optionsClick);
        sciFiCheckBox.setOnCheckedChangeListener(optionsClick);
        historySeekBar.setOnSeekBarChangeListener(optionsValueChanged);
        actionSeekBar.setOnSeekBarChangeListener(optionsValueChanged);
        sciFiSeekBar.setOnSeekBarChangeListener(optionsValueChanged);
        Intent konularIntent = getIntent();
        preferences = (Preferences) konularIntent.getSerializableExtra("preferences");
        historyCheckBox.setChecked(preferences.getHistory());
        actionCheckBox.setChecked(preferences.getAction());
        sciFiCheckBox.setChecked(preferences.getSciFi());
        historySeekBar.setProgress(preferences.getHistoryNumberQuestions());
        actionSeekBar.setProgress(preferences.getActionNumberQuestions());
        sciFiSeekBar.setProgress(preferences.getSciFiNumberQuestions());
        historyCount.setText(String.format("%d", preferences.getHistoryNumberQuestions()));
        actionCount.setText(String.format("%d", preferences.getActionNumberQuestions()));
        sciFiCount.setText(String.format("%d", preferences.getSciFiNumberQuestions()));
    }

    public OnClickListener restartExamClick = new View.OnClickListener() {
        public void onClick(View v){
            ((Subjects) v.getContext()).setResult(RESULT_OK);
            finish();
        }
    };

    public OnClickListener goBackClick = new View.OnClickListener() {
        public void onClick(View v){
            ((Subjects) v.getContext()).setResult(RESULT_CANCELED);
            finish();
        }
    };


    public OnCheckedChangeListener optionsClick = new CompoundButton.OnCheckedChangeListener(){
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
            SharedPreferences basicPreferences = getSharedPreferences("Test", MODE_PRIVATE);
            SharedPreferences.Editor changeBasicPreferences = basicPreferences.edit();
            if (((String)buttonView.getTag()).equalsIgnoreCase("1")){
                System.out.println(isChecked);
                changeBasicPreferences.putBoolean("history", isChecked);
            } else {
                if (((String) buttonView.getTag()).equalsIgnoreCase("2")) {
                    changeBasicPreferences.putBoolean("action", isChecked);
                } else {
                    if (((String) buttonView.getTag()).equalsIgnoreCase("3")) {
                        changeBasicPreferences.putBoolean("sciFi", isChecked);
                    }
                }
            }

            changeBasicPreferences.apply();
        }
    };

    public OnSeekBarChangeListener optionsValueChanged = new SeekBar.OnSeekBarChangeListener(){
        public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
            SharedPreferences basicPreferences = getSharedPreferences("Test", MODE_PRIVATE);
            SharedPreferences.Editor changeBasicPreferences = basicPreferences.edit();
            if (((String)arg0.getTag()).equalsIgnoreCase("1")){
                changeBasicPreferences.putInt("historyNumberQuestions", arg1);
                historyCount.setText(String.format("%d", arg1));
            } else {
                if (((String)arg0.getTag()).equalsIgnoreCase("2")){
                    changeBasicPreferences.putInt("actionNumberQuestions", arg1);
                    actionCount.setText(String.format("%d", arg1));
                } else {
                    if (((String)arg0.getTag()).equalsIgnoreCase("3")){
                        changeBasicPreferences.putInt("sciFiNumberQuestions", arg1);
                        sciFiCount.setText(String.format("%d", arg1));
                    }
                }
            }
            changeBasicPreferences.apply();
        }
        public void onStartTrackingTouch(SeekBar arg0) {
        }
        public void onStopTrackingTouch(SeekBar arg0) {
        }
    };

}


