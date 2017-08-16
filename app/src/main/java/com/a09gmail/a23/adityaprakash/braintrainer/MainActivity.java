package com.a09gmail.a23.adityaprakash.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView result;
    TextView points;
    TextView sumView;
    TextView timer;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button playAgain;
    RelativeLayout lay;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationAnswer;
    int score = 0;
    int questionNumber = 0;
    int gameState = 0;

    public void playAgain(View v){
        gameState = 0;
        score=0;
        questionNumber=0;
        timer.setText("30s");
        points.setText("0/0");
        result.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long l) {
                timer.setText(Long.toString(l/1000)+"s");
            }
            @Override
            public void onFinish() {
                gameState = 1;
                playAgain.setVisibility(View.VISIBLE);
                timer.setText("0s");
                result.setText("SCORE:"+Integer.toString(score)+"/"+Integer.toString(questionNumber));
            }
        }.start();
    }

    public void generateQuestion(){Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        locationAnswer = rand.nextInt(4);
        answer.clear();

        for (int i=0;i<4;i++){
            if (i==locationAnswer){
                answer.add(a+b);
            }else{
                int incorrect = rand.nextInt(41);
                while(incorrect == a+b)
                {incorrect = rand.nextInt(41);}
                answer.add(incorrect);
            }
        }
        b1.setText(Integer.toString(answer.get(0)));
        b2.setText(Integer.toString(answer.get(1)));
        b3.setText(Integer.toString(answer.get(2)));
        b4.setText(Integer.toString(answer.get(3)));
    }

    public void start(View v){
        startButton.setVisibility(View.INVISIBLE);
        lay.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    public void chooseAnswer(View v){
        if(gameState == 0){
            if (v.getTag().toString().equals(Integer.toString(locationAnswer))){
                result.setText("Correct!");
                score++;
             }
             else{
                result.setText("Wrong!");
            }
            questionNumber++;
            points.setText(Integer.toString(score)+"/"+Integer.toString(questionNumber));

            generateQuestion();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        sumView = (TextView)findViewById(R.id.sumTextView);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        result = (TextView)findViewById(R.id.scoreCard);
        points = (TextView)findViewById(R.id.points);
        timer = (TextView)findViewById(R.id.timerTextView);
        playAgain = (Button)findViewById(R.id.playAgainButton);
        lay = (RelativeLayout)findViewById(R.id.layout);

    }

}
