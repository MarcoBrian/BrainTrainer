package com.example.marcobrian.braintrainer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;

import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {



    Button StartButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView result;
    TextView score;
    GridLayout gridLayout;
    int currentScore =0;
    int totalplayed =0;
    int locationofcorrectanswer;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView question;
    TextView timer;
    Button playagainbutton;
    Boolean isgameover=false;
    ConstraintLayout layout;
    MediaPlayer correct_sound;
    MediaPlayer wrong_sound;
    MediaPlayer complete_sound;
    public void newQuestion(){

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        question.setText(String.valueOf(a)+"+"+String.valueOf(b));
        locationofcorrectanswer = rand.nextInt(4);
        answers.clear();
        for(int i= 0 ; i < 4;i++){
            if(i==locationofcorrectanswer){
                answers.add(a+b);
            }else{
                int wronganswer=rand.nextInt(41);
                while(wronganswer==a+b){
                    wronganswer=rand.nextInt(41);
                }
                answers.add(wronganswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
    public void start(View view){
        StartButton.setVisibility(View.GONE);
        playagain(timer);
    }
    public void chooseanswer(View view){
        if(!isgameover) {

            if (String.valueOf(locationofcorrectanswer).equals(view.getTag().toString())) {
                result.setText("Correct");
                currentScore++;
                newQuestion();
                correct_sound.start();
            } else {
                result.setText("Wrong");
                wrong_sound.start();
            }
            totalplayed++;

            score.setText(String.valueOf(currentScore) + "/" + String.valueOf(totalplayed));
        }
    }
    public void playagain(View view){
        layout.setVisibility(View.VISIBLE);
        result.setText("");
        isgameover=false;
        playagainbutton.setVisibility(View.INVISIBLE);
        currentScore=0;
        totalplayed=0;
        score.setText(String.valueOf(currentScore)+"/"+String.valueOf(totalplayed));
        newQuestion();
        CountDownTimer countDownTimer = new CountDownTimer(1000*30,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int timeleft=(int) millisUntilFinished/1000;
                String timesleft= String.valueOf(timeleft);
                timer.setText(timesleft+"s");

            }

            @Override
            public void onFinish() {
                result.setText("Done");
                playagainbutton.setVisibility(View.VISIBLE);
                isgameover=true;
                complete_sound.start();


            }
        }.start();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartButton = (Button) findViewById(R.id.Start);
        question = (TextView) findViewById(R.id.question);
        button0 = findViewById(R.id.ans0);
        button1 = findViewById(R.id.ans1);
        button2 = findViewById(R.id.ans2);
        button3 = findViewById(R.id.ans3);
        timer  = findViewById(R.id.timer);
        result = findViewById(R.id.result);
        score = findViewById(R.id.Score);
        playagainbutton=findViewById(R.id.playagain);
        layout =findViewById(R.id.layout2);
        layout.setVisibility(View.INVISIBLE);
        StartButton.setVisibility(View.VISIBLE);
        correct_sound = MediaPlayer.create(getApplicationContext(),R.raw.correct);
         wrong_sound  = MediaPlayer.create(getApplicationContext(),R.raw.wrong);
         complete_sound  = MediaPlayer.create(getApplicationContext(),R.raw.complete);




    }
}
