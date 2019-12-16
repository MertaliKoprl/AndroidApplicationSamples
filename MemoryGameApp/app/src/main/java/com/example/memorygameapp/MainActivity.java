package com.example.memorygameapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    TextView pointText;
    TextView timeText;
    ImageView card11;
    ImageView card12;
    ImageView card13;
    ImageView card14;
    ImageView card21;
    ImageView card22;
    ImageView card23;
    ImageView card24;
    ImageView card31;
    ImageView card32;
    ImageView card33;
    ImageView card34;
    ImageView card41;
    ImageView card42;
    ImageView card43;
    ImageView card44;
    ImageView card51;
    ImageView card52;
    ImageView card53;
    ImageView card54;
    TextView TurnNo;
    int image1, image2, image3, image4, image5, image6, image7, image8, image9, image10;
    int firstCard, secondCard;
    int noOfTurn = 0;
    int cardNumber = 1;
    int playerPoint = 0;
    private Integer[] cards = {201, 101, 202, 102,203,103,204, 104,205,105,206, 106,207,107,208,108,209,109,210,110,};
    private int selectedCard1;
    private int selectedCard2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeItems();
        frontOfCardImages();
        Collections.shuffle(Arrays.asList(cards));// SHUFFLESS CARDS
        TurnNo.setText(String.valueOf(noOfTurn));
        pointText.setText("0");
        timeText.setText("10000");
        new CountDownTimer(60000, 1000) {//70000=70 sec.
            public void onTick(long millisUntilFinished) {
                timeText.setText(String.valueOf(millisUntilFinished / 1000));
            }
            public void onFinish() {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setMessage("Game OVER OUT OF TIME ! " + playerPoint + " Points received !")
                            .setCancelable(false)
                            .setPositiveButton("New Game ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    AlertDialog alert = alertDialog.create();
                    alert.show();
                timeText.setText("0");
            }
        }.start();
    }


    public void frontOfCardImages() {
        image1 = R.drawable.one;
        image2 = R.drawable.two;
        image3 = R.drawable.three;
        image4 = R.drawable.four;
        image5 = R.drawable.five;
        image6 = R.drawable.six;
        image7 = R.drawable.seven;
        image8 = R.drawable.eight;
        image9 = R.drawable.nine;
        image10 = R.drawable.ten;

    }


    public void initializeItems() {
        pointText = (TextView) findViewById(R.id.PointsLabel);
        timeText = (TextView) findViewById(R.id.timeText);
        TurnNo = (TextView) findViewById(R.id.turnNo);
        card11 = (ImageView) findViewById(R.id.card11);
        card12 = (ImageView) findViewById(R.id.card12);
        card13 = (ImageView) findViewById(R.id.card13);
        card14 = (ImageView) findViewById(R.id.card14);
        card21 = (ImageView) findViewById(R.id.card21);
        card22 = (ImageView) findViewById(R.id.card22);
        card23 = (ImageView) findViewById(R.id.card23);
        card24 = (ImageView) findViewById(R.id.card24);
        card31 = (ImageView) findViewById(R.id.card31);
        card32 = (ImageView) findViewById(R.id.card32);
        card33 = (ImageView) findViewById(R.id.card33);
        card34 = (ImageView) findViewById(R.id.card34);
        card41 = (ImageView) findViewById(R.id.card41);
        card42 = (ImageView) findViewById(R.id.card42);
        card43 = (ImageView) findViewById(R.id.card43);
        card44 = (ImageView) findViewById(R.id.card44);
        card51 = (ImageView) findViewById(R.id.card51);
        card52 = (ImageView) findViewById(R.id.card52);
        card53 = (ImageView) findViewById(R.id.card53);
        card54 = (ImageView) findViewById(R.id.card54);


    }

    public void revealCard(View view) {
        ImageView clickedCard = (ImageView) view;
        int cardNo = Integer.parseInt((String) view.getTag());//Gets the Tag of the clicked item
        controlCards(clickedCard, cardNo);

    }

    public void controlCards(ImageView card, int cardNo) {
        switch (cards[cardNo]) {
            case 201:
            case 101:
                card.setImageResource(image1);
                break;
            case 202:
            case 102:
                card.setImageResource(image2);
                break;
            case 203:
            case 103:
                card.setImageResource(image3);
                break;
            case 204:
            case 104:
                card.setImageResource(image4);
                break;
            case 205:
            case 105:
                card.setImageResource(image5);
                break;
            case 206:
            case 106:
                card.setImageResource(image6);
                break;
            case 207:
            case 107:
                card.setImageResource(image7);
                break;
            case 208:
            case 108:
                card.setImageResource(image8);
                break;
            case 209:
            case 109:
                card.setImageResource(image9);
                break;
            case 210:
            case 110:
                card.setImageResource(image10);
                break;

        }
        //CHECK WHICH IMAGE IS SELECTED AND SAVE IT TO TEMPORARY VARIABLE
        if (cardNumber == 1) {  //Selection 1
            firstCard = cards[cardNo];
            if(firstCard>200){
                firstCard-=100;
            }
            cardNumber = 2;
            selectedCard1 = cardNo;
            card.setEnabled(false);
        } else if (cardNumber == 2) { //Selection 2
            secondCard = cards[cardNo];
            if(secondCard>200){
                secondCard-=100;
            }
            cardNumber = 1;
            selectedCard2 = cardNo;
        card11.setEnabled(false);
        card12.setEnabled(false);
        card13.setEnabled(false);
        card14.setEnabled(false);
        card21.setEnabled(false);
        card22.setEnabled(false);
        card23.setEnabled(false);
        card24.setEnabled(false);
        card31.setEnabled(false);
        card32.setEnabled(false);
        card33.setEnabled(false);
        card34.setEnabled(false);
        card41.setEnabled(false);
        card42.setEnabled(false);
        card43.setEnabled(false);
        card44.setEnabled(false);
        card51.setEnabled(false);
        card52.setEnabled(false);
        card53.setEnabled(false);
        card54.setEnabled(false);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                calculate();
            }

        }, 750);

    }
    }

    private void calculate() {
        //IF IMAGES ARE EQUAL REMOVE THEM AND ADD POINT

        if (firstCard == secondCard) {//If tags are equal.
            if (selectedCard1 == 0) {
                card11.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 1) {
                card12.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 2) {
                card13.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 3) {
                card14.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 4) {
                card21.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 5) {
                card22.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 6) {
                card23.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 7) {
                card24.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 8) {
                card31.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 9) {
                card32.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 10) {
                card33.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 11) {
                card34.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 12) {
                card41.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 13) {
                card42.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 14) {
                card43.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 15) {
                card44.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 16) {
                card51.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 17) {
                card52.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 18) {
                card53.setVisibility(View.INVISIBLE);

            } else if (selectedCard1 == 19) {
                card54.setVisibility(View.INVISIBLE);

            }

            if (selectedCard2 == 0) {
                card11.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 1) {
                card12.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 2) {
                card13.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 3) {
                card14.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 4) {
                card21.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 5) {
                card22.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 6) {
                card23.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 7) {
                card24.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 8) {
                card31.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 9) {
                card32.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 10) {
                card33.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 11) {
                card34.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 12) {
                card41.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 13) {
                card42.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 14) {
                card43.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 15) {
                card44.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 16) {
                card51.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 17) {
                card52.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 18) {
                card53.setVisibility(View.INVISIBLE);

            } else if (selectedCard2 == 19) {
                card54.setVisibility(View.INVISIBLE);

            }

            //ADD POINTS AND SHOWS IT
            playerPoint += 10;
            pointText.setText(String.valueOf(playerPoint));


        } else { //REVEAL ALL CARDS BACK ! = TO BACK IMAGE
            turnBackPositionCards();
        }

        noOfTurn++;
        TurnNo.setText(String.valueOf(noOfTurn));
        card11.setEnabled(true);
        card12.setEnabled(true);
        card13.setEnabled(true);
        card14.setEnabled(true);
        card21.setEnabled(true);
        card22.setEnabled(true);
        card23.setEnabled(true);
        card24.setEnabled(true);
        card31.setEnabled(true);
        card32.setEnabled(true);
        card33.setEnabled(true);
        card34.setEnabled(true);
        card41.setEnabled(true);
        card42.setEnabled(true);
        card43.setEnabled(true);
        card44.setEnabled(true);
        card51.setEnabled(true);
        card52.setEnabled(true);
        card53.setEnabled(true);
        card54.setEnabled(true);
        checkFinish();

    }
    private void turnBackPositionCards(){
        card11.setImageResource(R.drawable.border);
        card12.setImageResource(R.drawable.border);
        card13.setImageResource(R.drawable.border);
        card14.setImageResource(R.drawable.border);
        card21.setImageResource(R.drawable.border);
        card22.setImageResource(R.drawable.border);
        card23.setImageResource(R.drawable.border);
        card24.setImageResource(R.drawable.border);
        card31.setImageResource(R.drawable.border);
        card32.setImageResource(R.drawable.border);
        card33.setImageResource(R.drawable.border);
        card34.setImageResource(R.drawable.border);
        card41.setImageResource(R.drawable.border);
        card42.setImageResource(R.drawable.border);
        card43.setImageResource(R.drawable.border);
        card44.setImageResource(R.drawable.border);
        card51.setImageResource(R.drawable.border);
        card52.setImageResource(R.drawable.border);
        card53.setImageResource(R.drawable.border);
        card54.setImageResource(R.drawable.border);


    }

    private void checkFinish() {
        if (card11.getVisibility() == View.INVISIBLE &&
                card12.getVisibility() == View.INVISIBLE
                && card13.getVisibility() == View.INVISIBLE
                && card14.getVisibility() == View.INVISIBLE
                && card21.getVisibility() == View.INVISIBLE
                && card22.getVisibility() == View.INVISIBLE
                && card23.getVisibility() == View.INVISIBLE
                && card24.getVisibility() == View.INVISIBLE
                && card31.getVisibility() == View.INVISIBLE
                && card32.getVisibility() == View.INVISIBLE
                && card33.getVisibility() == View.INVISIBLE
                && card34.getVisibility() == View.INVISIBLE
                && card41.getVisibility() == View.INVISIBLE
                && card42.getVisibility() == View.INVISIBLE
                && card43.getVisibility() == View.INVISIBLE
                && card44.getVisibility() == View.INVISIBLE
                && card51.getVisibility() == View.INVISIBLE
                && card52.getVisibility() == View.INVISIBLE
                && card53.getVisibility() == View.INVISIBLE
                && card54.getVisibility() == View.INVISIBLE
        ) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setMessage("Game OVER ! " + playerPoint + " Points received !")
                    .setCancelable(false)
                    .setPositiveButton("New Game ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog alert = alertDialog.create();
            alert.show();

        }


    }


}

