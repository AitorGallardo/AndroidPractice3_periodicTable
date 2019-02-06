package com.example.aitor.androidpractice1_periodictable;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Main3GameActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    Bundle test;

    Button answer1, answer2,answer3,answer4;
    TextView questionView, nextQuestion;

    String fileName = "quizGame.json";

    ArrayList<Quiz> quizList = new ArrayList<>();
    ArrayList <Answer> currentAnswers = new ArrayList<>();
    int currentQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_game);
        setTitle("Quiz");
        parseJson(loadJson(fileName));

        questionView = (TextView) findViewById(R.id.quizView);

        nextQuestion = (TextView) findViewById(R.id.nextQuestion);
        nextQuestion.setOnClickListener(this);

        answer1 = (Button) findViewById(R.id.bttnQst1);
        answer1.setOnClickListener(this);
        answer2 = (Button) findViewById(R.id.bttnQst2);
        answer2.setOnClickListener(this);
        answer3 = (Button) findViewById(R.id.bttnQst3);
        answer3.setOnClickListener(this);
        answer4 = (Button) findViewById(R.id.bttnQst4);
        answer4.setOnClickListener(this);

        loadQuestion();


    }

    @Override
    public void onClick(View v) {

        int tag = Integer.parseInt(v.getTag().toString());

        if(tag == 4){
            currentQuestion++;
            if(currentQuestion > 10){
                endGame();
            } else {
                loadQuestion();
            }
        }else {
            boolean value = currentAnswers.get(tag).isValue();
            quizList.get(currentQuestion).setResponse(value);
            displayResults();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu3game, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.arrowBack:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadQuestion(){

       Quiz quiz =  quizList.get(currentQuestion);
        currentAnswers = quizList.get(currentQuestion).getAnswers();
        questionView.setText(quiz.getQuestion());
        answer1.setText(currentAnswers.get(0).getName());
        answer2.setText(currentAnswers.get(1).getName());
        answer3.setText(currentAnswers.get(2).getName());
        answer4.setText(currentAnswers.get(3).getName());

        answer1.setBackgroundColor(Color.GRAY);
        answer2.setBackgroundColor(Color.GRAY);
        answer3.setBackgroundColor(Color.GRAY);
        answer4.setBackgroundColor(Color.GRAY);

        answer1.setEnabled(true);
        answer2.setEnabled(true);
        answer3.setEnabled(true);
        answer4.setEnabled(true);
        // if buttons are disabled, responses cant be shown
        answer1.setTextColor(Color.WHITE);
        answer2.setTextColor(Color.WHITE);
        answer3.setTextColor(Color.WHITE);
        answer4.setTextColor(Color.WHITE);

        // when we arrive to question n10 we end the game
        if(currentQuestion == 10){
            nextQuestion.setText("Finish");
        }
    }
    public void displayResults(){

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setButtonColors();

            }
        }, 100);

    }

    public void setButtonColors(){

        if (currentAnswers.get(0).isValue()) answer1.setBackgroundColor(Color.GREEN);
        else answer1.setBackgroundColor(Color.RED);
        if (currentAnswers.get(1).isValue()) answer2.setBackgroundColor(Color.GREEN);
        else answer2.setBackgroundColor(Color.RED);
        if (currentAnswers.get(2).isValue()) answer3.setBackgroundColor(Color.GREEN);
        else answer3.setBackgroundColor(Color.RED);
        if (currentAnswers.get(3).isValue()) answer4.setBackgroundColor(Color.GREEN);
        else answer4.setBackgroundColor(Color.RED);

        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
        answer4.setEnabled(false);

    }

    public void endGame(){

        int correctAnswers = 0;

        for(int i = 0;i < 10; i++){
            if(quizList.get(i).isResponse()){
                correctAnswers++;
            }
        }
        Toast.makeText(this, "Correct asswers: "+correctAnswers,
                Toast.LENGTH_LONG).show();
        finish();
    }


    public String loadJson(String fileName) {
        String json = null;
        try {

            InputStream is = getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");



        } catch (IOException ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Error loading elements files.",
                    Toast.LENGTH_LONG).show();
            return null;
        }
        return json;

    }

    public void parseJson(String jsonFile){

        try {
            JSONObject obj = new JSONObject(jsonFile);
            JSONArray m_jArry = obj.getJSONArray("quiz");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                JSONArray answersArray = jo_inside.getJSONArray("answers");
                ArrayList<Answer> answers = new ArrayList<>();

                String question = jo_inside.getString("question") != null ? jo_inside.getString("question"): "";
                boolean response = jo_inside.getString("response") != null ? jo_inside.getBoolean("response"): false;

                for (int p = 0; p < answersArray.length(); p++) {
                    JSONObject answerObj = answersArray.getJSONObject(p);
                    String name = answerObj.getString("name") != null ? answerObj.getString("name"): "";
                    boolean value = answerObj.getString("value") != null ? answerObj.getBoolean("value"): false;
                    answers.add(new Answer(name,value));
                }
                quizList.add(new Quiz(question, answers , response));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading elements files.",
                    Toast.LENGTH_LONG).show();
        }
    }


}
