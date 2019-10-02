package com.mahmoudsalah.braintest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
  Button goButton,playAgain,button0,button1,button2,button3;
  TextView timeTextView,scoreTextView,sumTextView,resultTextView;
  int a,b,correctbox,wronganswer,score=0,questionNumber=0;
  ArrayList<Integer> answer = new ArrayList<>();
  GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.goButton);
        playAgain = findViewById(R.id.playAgain);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        timeTextView = findViewById(R.id.timeTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.INVISIBLE);
        timeTextView.setVisibility(View.INVISIBLE);
        sumTextView.setVisibility(View.INVISIBLE);
        scoreTextView.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
    }
    public void start(View view){
       resultTextView.setText("");
        goButton.setVisibility(View.INVISIBLE);
        newQuestions();
        countdown();
        gridLayout.setVisibility(View.VISIBLE);
        timeTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
    }
    public void chooseanswer(View view){

        if(Integer.toString(correctbox).equals(view.getTag().toString())){
            resultTextView.setText("Correct");
            score++;
        }else{
            resultTextView.setText("Wrong");
        }
        questionNumber++;
        scoreTextView.setText(score+"/"+questionNumber);
        newQuestions();
    }
    public void playAgain(View view){
        score=0;
        questionNumber=0;
        scoreTextView.setText("0/0");
        resultTextView.setText("");
        newQuestions();
        countdown();
        gridLayout.setVisibility(View.VISIBLE);


    }
    public void newQuestions(){

        Random random = new Random();
        a = random.nextInt(51);
        b = random.nextInt(51);
        sumTextView.setText(a+"+"+b);

        correctbox = random.nextInt(4);
        answer.clear();
        for (int i = 0; i <4 ; i++) {
            if (i==correctbox){
                answer.add(a+b);
            }
            else{
                wronganswer = random.nextInt(101);
                while(wronganswer == a+b){
                wronganswer = random.nextInt(101);
                }
                answer.add(wronganswer);
            }


        }
        button0.setText(answer.get(0).toString());
        button1.setText(answer.get(1).toString());
        button2.setText(answer.get(2).toString());
        button3.setText(answer.get(3).toString());

    }
    public void countdown(){
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
            timeTextView.setText(String.valueOf(l/1000)+"s");

            if (l==150){
                gridLayout.setVisibility(View.INVISIBLE);
                sumTextView.setText("");
            }
            }

            @Override
            public void onFinish() {
                timeTextView.setText("0s");
                gridLayout.setVisibility(View.INVISIBLE);
                playAgain.setVisibility(View.VISIBLE);
                sumTextView.setText("");
                resultTextView.setText("Done!");


            }
        }.start();
    }

}


