package com.example.movietriviatest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;

public class Test extends Activity {

    static final int RESTART_EXAM = 1;
    int points = 0;
    int pointsGettedBonus = 0;
    private ArrayList<Question> questions;
    private Preferences preferences;
    private int historyNumberQuestions;
    private int actionNumberQuestions;
    private int sciFiNumberQuestions;
    private TextView userNameField;
    private TextView questionCounter;
    private int numberOfCorrectAnswers;
    private int questionNo;
    private Button guessBonusBtn;
    private TextView pointText;
    private TextView remainingTimeText;
    private int correctAnswer;
    private TextView questionArea;
    private Button choiseA;
    private Button choiseB;
    private Button choiseC;
    private Button choiseD;
    private int bonusCounter = 0;
    private int timeVariable = 0;//It will change according to number of questions
    private Button restartBtn;
    private String userNameText;
    private boolean isBonusActivated = false;
    public View.OnClickListener choiceClick = new View.OnClickListener() {
        public void onClick(View v) {
            if (((String) v.getTag()).equalsIgnoreCase(Integer.toString(correctAnswer))) {
                points += 10;
                bonusCounter++;
                pointText.setText(points + " Points");
                numberOfCorrectAnswers++;
            } else {
                if (!isBonusActivated) {
                    if (correctAnswer == 1)
                        Toast.makeText(getApplicationContext(), "Wrong , correct answer was A", Toast.LENGTH_LONG).show();
                    else if (correctAnswer == 2)
                        Toast.makeText(getApplicationContext(), "Wrong , correct answer was B", Toast.LENGTH_LONG).show();
                    else if (correctAnswer == 3)
                        Toast.makeText(getApplicationContext(), "Wrong , correct answer was C", Toast.LENGTH_LONG).show();
                    else if (correctAnswer == 4)
                        Toast.makeText(getApplicationContext(), "Wrong , correct answer was D", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong choise , try One more", Toast.LENGTH_LONG).show();
                    isBonusActivated = false;
                    return;

                }
            }
            questionNo++;
            if (questionNo < historyNumberQuestions + actionNumberQuestions + sciFiNumberQuestions && !remainingTimeText.getText().toString().equals("0")) {
                showCurrentQuestion();
            } else {
                String message = "You answered " + numberOfCorrectAnswers + " questions correctly!";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                choiseA.setEnabled(false);
                choiseB.setEnabled(false);
                choiseC.setEnabled(false);
                choiseD.setEnabled(false);
            }
            if (bonusCounter / 2 == 1) {
                bonusCounter = 0;
                bonusQuestion();
            }
        }
    };
    public View.OnClickListener startExamClick = new View.OnClickListener() {
        public void onClick(View v) {
            startExam();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.tick);
        mp.start();
        guessBonusBtn = (Button) findViewById(R.id.guessBonusBtn);
        restartBtn = (Button) findViewById(R.id.restartBtn);
        questionCounter = (TextView) findViewById(R.id.questionCounter);
        userNameField = (TextView) findViewById(R.id.userNameField);
        questionArea = (TextView) findViewById(R.id.questionArea);
        pointText = (TextView) findViewById(R.id.pointText);
        questionArea.setEnabled(false);
        choiseA = (Button) findViewById(R.id.choiseA);
        choiseB = (Button) findViewById(R.id.choiseB);
        choiseC = (Button) findViewById(R.id.choiseC);
        choiseD = (Button) findViewById(R.id.choiseD);
        choiseA.setOnClickListener(choiceClick);
        choiseB.setOnClickListener(choiceClick);
        choiseC.setOnClickListener(choiceClick);
        choiseD.setOnClickListener(choiceClick);
        remainingTimeText = (TextView) findViewById(R.id.remainingTimeText);
        Intent userNameIntent = getIntent();
        userNameText = (String) userNameIntent.getSerializableExtra("userName");
        userNameField.setText(userNameText);
        restartBtn.setOnClickListener(startExamClick);
        readQuestionFile();
        readPreferences();
        startExam();
        new CountDownTimer(timeVariable, 1000) {//70000=70 sec.
            public void onTick(long millisUntilFinished) {
                remainingTimeText.setText(String.valueOf(millisUntilFinished / 1000));
            }
            public void onFinish() {
                remainingTimeText.setText("0");
            }
        }.start();
    }

    private void readPreferences() {
        SharedPreferences basicPreferences = getSharedPreferences("Test", MODE_PRIVATE);
        preferences = new Preferences(
                basicPreferences.getBoolean("history", true),
                basicPreferences.getBoolean("action", true),
                basicPreferences.getBoolean("sciFi", true),
                basicPreferences.getInt("historyNumberQuestions", 10),
                basicPreferences.getInt("actionNumberQuestions", 10),
                basicPreferences.getInt("sciFiNumberQuestions", 10));

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESTART_EXAM) {
            if (resultCode == RESULT_OK) {
                readPreferences();
                startExam();
            }
        }
        if (resultCode == 123) {
            if (data != null) {
                Bundle extras = data.getExtras();
                pointsGettedBonus = extras.getInt("resultpoints");
                if (pointsGettedBonus != points) {
                    Toast.makeText(getApplicationContext(), "YEEEE , ITS TRUE", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(getApplicationContext(), "Ahh Try next Time", Toast.LENGTH_LONG).show();
                }
                points = pointsGettedBonus;
            }
            pointText.setText(points + " Points");
        }
    }

    public void bonusActivated(View v) {
        isBonusActivated = true;
        guessBonusBtn.setVisibility(View.INVISIBLE);
    }

    private void readQuestionFile() {
        InputStream stream = getResources().openRawResource(R.raw.movie);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-16")));
        Question newQuestion;
        String question;
        questions = new ArrayList<Question>();
        try {
            while ((question = reader.readLine()) != null) {
                String[] questionContent = question.split(";");
                if ((questionContent[0].equalsIgnoreCase("T") || questionContent[0].equalsIgnoreCase("M") || questionContent[0].equalsIgnoreCase("Y"))
                        && (questionContent[6].equalsIgnoreCase("A") || questionContent[6].equalsIgnoreCase("B") || questionContent[6].equalsIgnoreCase("C") || questionContent[6].equalsIgnoreCase("D"))) {
                    newQuestion = new Question(questionContent[0], questionContent[1], questionContent[2], questionContent[3], questionContent[4], questionContent[5], questionContent[6]);
                    questions.add(newQuestion);
                }
            }
        } catch (IOException e) {
        }
    }

    private Question getCurrentQuestion() {// REVIEW !!!
        int whichQuestion, i;
        String questionType;
        if (questionNo < historyNumberQuestions) {
            whichQuestion = questionNo;
            questionType = "T";
        } else {
            if (questionNo < historyNumberQuestions + actionNumberQuestions) {
                whichQuestion = questionNo - historyNumberQuestions;
                questionType = "M";
            } else {
                whichQuestion = questionNo - historyNumberQuestions - actionNumberQuestions;
                questionType = "Y";
            }
        }
        i = 0;
        for (Question question : questions) {
            if (question.getQuestionType().equalsIgnoreCase(questionType)) {
                if (i == whichQuestion) {
                    return question;
                } else {
                    i++;
                }
            }
        }
        return null;
    }

    private void showCurrentQuestion() {
        Question currentQuestion;
        currentQuestion = getCurrentQuestion();
        if (currentQuestion != null) {
            questionArea.setText((questionNo + 1) + ") " + currentQuestion.getQuestion());
            choiseA.setText("A) " + currentQuestion.getChoice1());
            choiseB.setText("B) " + currentQuestion.getChoice2());
            choiseC.setText("C) " + currentQuestion.getChoice3());
            choiseD.setText("D) " + currentQuestion.getChoice4());
            if (currentQuestion.getCorrectAnswer().equalsIgnoreCase("A")) {
                correctAnswer = 1;
            } else {
                if (currentQuestion.getCorrectAnswer().equalsIgnoreCase("B")) {
                    correctAnswer = 2;
                } else {
                    if (currentQuestion.getCorrectAnswer().equalsIgnoreCase("C")) {
                        correctAnswer = 3;
                    } else {
                        correctAnswer = 4;
                    }
                }
            }
        }
        questionCounter.setText(questionNo + 1 + "/" + preferences.getTotalNumberOfQuestions());
        timeVariable = preferences.getTotalNumberOfQuestions() * 60000;//EVERY GUESSTION = MIN;
    }

    private void startExam() {
        isBonusActivated = false;
        guessBonusBtn.setVisibility(View.VISIBLE);
        if (!preferences.getHistory()) {

            historyNumberQuestions = 0;
        } else
            historyNumberQuestions = preferences.getHistoryNumberQuestions();
        if (!preferences.getAction()) {

            actionNumberQuestions = 0;
        } else
            actionNumberQuestions = preferences.getActionNumberQuestions();
        if (!preferences.getSciFi())
            sciFiNumberQuestions = 0;
        else
            sciFiNumberQuestions = preferences.getSciFiNumberQuestions();
        this.points = 0;
        pointText.setText("0 Points");
        questionNo = 0;
        numberOfCorrectAnswers = 0;
        Collections.shuffle(questions);

        showCurrentQuestion();
        choiseA.setEnabled(true);
        choiseB.setEnabled(true);
        choiseC.setEnabled(true);
        choiseD.setEnabled(true);
    }

    public void bonusQuestion() {
        Intent subjectsIntent = new Intent(Test.this, BonusQuet.class);
        subjectsIntent.putExtra("points", points);
        startActivityForResult(subjectsIntent, RESTART_EXAM);

    }

    public void optionsSelected(View view) {
        Intent subjectsIntent = new Intent(Test.this, Subjects.class);
        subjectsIntent.putExtra("preferences", preferences);
        startActivityForResult(subjectsIntent, RESTART_EXAM);


    }


}

