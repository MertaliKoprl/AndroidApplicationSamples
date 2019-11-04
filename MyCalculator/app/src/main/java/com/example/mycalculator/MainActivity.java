package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public boolean isSecond;
    boolean isMore = false;
    private double right;
    private double left;
    private String selectedOperator;
    private boolean numberInput;
    private boolean isNominate = false;
    EditText resultText;
    EditText preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        preview= (EditText) findViewById(R.id.preview);
         resultText = (EditText) findViewById(R.id.Result);
        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button btnPlus = (Button) findViewById(R.id.buttonPlus);
        Button btnMinus = (Button) findViewById(R.id.buttonMinus);
        Button btnMult = (Button) findViewById(R.id.buttonMult);
        Button btnDiv = (Button) findViewById(R.id.buttonDiv);
        Button btnEqual = (Button) findViewById(R.id.buttonEqual);
        Button btnErase = (Button) findViewById(R.id.buttonErase);
        Button buttonRemove = (Button) findViewById(R.id.buttonRemove);
        Button buttonDot = (Button) findViewById(R.id.buttonDot);
        resultText.setEnabled(false);
        preview.setEnabled(false);




    }


    public void buttonClicked(View view) {

        StringBuilder msg = new StringBuilder();
        StringBuilder prw = new StringBuilder();
        Button button= (Button) view;
        String buttonText = button.getText().toString();
        if (buttonText.equals("C")) {
            left = 0;
            isMore = false;
            isNominate = false;
            isSecond = false;
            right = Double.MIN_VALUE;
            selectedOperator = "";

            numberInput = false;
            resultText.setText("0");
            preview.setText("= 0");
            return;
        }
        if (buttonText.matches("R~")) {

            String removeText;
            if (!resultText.getText().toString().equals("0") || !resultText.getText().toString().equals("0.0")) {
                if (Math.abs(Double.parseDouble(resultText.getText().toString())) >= 10 || isNominate) {
                    removeText = resultText.getText().toString();
                    removeText = removeText.substring(0, removeText.length() - 1);

                } else {
                    removeText = "0";
                }

                resultText.setText(removeText);
                if (isSecond) {
                    preview.setText("= " + left + selectedOperator + removeText);

                } else {
                    preview.setText("= " + removeText);


                }
            }

        }

        if (buttonText.matches("[0,1,2,3,4,5,6,7,8,9,.]")) {
            if (!numberInput) {
                numberInput = true;
                resultText.setText("0");
            }
            if (resultText.getText().equals("0.0") || resultText.getText().equals("0")) {
                if (buttonText.matches("[.,]")) {
                    resultText.setText(buttonText);
                    isNominate = true;
                }


            }
            if (buttonText.matches("[.,]") && !isNominate) {
                msg.append(resultText.getText());
                msg.append(buttonText);
                resultText.setText(msg.toString());
                preview.setText("= " + msg.toString());
                isNominate = true;
            } else if (!buttonText.matches("[.,]")) {
                if (!resultText.getText().toString().equals("0")) {
                    msg.append(resultText.getText());
                    msg.append(buttonText);
                    preview.setText("= " + msg.toString() + " ");
                } else {
                    preview.setText("= " + buttonText);
                    msg.append(buttonText);

                }
            }
            if (isSecond) {
                preview.setText("= " + left + selectedOperator + msg.toString());

            }
            if (buttonText.matches("[0,1,2,3,4,5,6,7,8,9]")) {

                resultText.setText(msg.toString());
                right = Double.parseDouble(msg.toString());

            }

            return;
        }

        prw.append("= ");
        if (buttonText.matches("[-,+,*,/,%]")) {

            if (isMore) {
                left = calculated(selectedOperator, left, right);
            } else {
                left = new Double(resultText.getText().toString());
                isMore = true;
            }
            selectedOperator = buttonText;
            prw.append(left + "" + selectedOperator);
            preview.setText(prw.toString());
            if(buttonText.matches("[/,*]")){
                right=1;
            }else{
                right = 0;
            }
            isSecond = true;
            isNominate = false;
            numberInput = false;
            resultText.setText("0");
            return;
        }
        if (buttonText.matches("x²")) {
            selectedOperator = buttonText;
            left = new Double(resultText.getText().toString());

            left = calculated(selectedOperator, left, right);
            resultText.setText(Double.toString(left));
            numberInput = false;
            preview.setText("= " + left + " " + selectedOperator);
            return;
        }

        if (buttonText.equals("=")) {
            isMore = false;
            isSecond = false;
            if (right == Double.MIN_VALUE) {
                if (!resultText.getText().equals("")) {
                    right = (numberInput && right == Double.MIN_VALUE) ? new Double(resultText.getText().toString()) : left;
                    left = calculated(selectedOperator, left, right);
                }
            } else if (right != Double.MIN_VALUE) {
                left = calculated(selectedOperator, left, right);
            }
            resultText.setText(Double.toString(left));
            numberInput = false;
            preview.setText("= " + Double.toString(left));

            return;
        }


    }


    private double calculated(String operator, double left, double right) {
        switch (operator) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                return left / right;
            case "x²":
                return Math.pow(left, 2);
            case "%":
                return (left * right) / 100;
        }
        return left;
    }

}
