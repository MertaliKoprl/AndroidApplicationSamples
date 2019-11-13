package com.example.movietriviatest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView userNameField;
    private String userName;
    private Button startBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        startBtn= (Button) findViewById(R.id.startBtn);

        userNameField = (TextView) findViewById(R.id.userNameField);

        startBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                userName=userNameField.getText().toString();
                Intent startApp = new Intent(MainActivity.this,Test.class);
                startApp.putExtra("userName", userName);
                startActivity(startApp);


            }
        });

    }

}

