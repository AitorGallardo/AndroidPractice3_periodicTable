package com.example.aitor.androidpractice1_periodictable;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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

        parseJson(loadJson(fileName));

        questionView = (TextView) findViewById(R.id.quizView);
//        questionView.setOnClickListener(this);
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

        // intent = new Intent(this, MainActivity.class);
        // test = getIntent().getExtras();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nextQuestion:
                loadQuestion();
                Toast.makeText(this, "NEXT PLEASE",
                        Toast.LENGTH_LONG).show();
                break;
//            case R.id.bttnQst1:
//                boolean value = currentAnswers.get(0).isValue();
//                quizList.get(currentQuestion).setResponse(value);
//                if(value){
//                    answer1.setBackgroundColor(Color.GREEN);
//                    answer2.setBackgroundColor(Color.RED);
//                    answer3.setBackgroundColor(Color.RED);
//                    answer4.setBackgroundColor(Color.RED);
//                } else {
//                    answer1.setBackgroundColor(Color.RED);
//                }
//                Toast.makeText(this, "NEXT PLEASE",
//                        Toast.LENGTH_LONG).show();
//                break;
//            case R.id.bttnQst2:
//                loadQuestion();
//                Toast.makeText(this, "NEXT PLEASE",
//                        Toast.LENGTH_LONG).show();
//                break;
//            case R.id.bttnQst3:
//                loadQuestion();
//                Toast.makeText(this, "NEXT PLEASE",
//                        Toast.LENGTH_LONG).show();
//                break;
//            case R.id.bttnQst4:
//                loadQuestion();
//                Toast.makeText(this, "NEXT PLEASE",
//                        Toast.LENGTH_LONG).show();
//                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu3game, menu);
        return true;
    }

    public void loadQuestion(){

       Quiz quiz =  quizList.get(currentQuestion);
        currentAnswers = quizList.get(currentQuestion).getAnswers();
        questionView.setText(quiz.getQuestion());
        answer1.setText(currentAnswers.get(0).getName());
        answer2.setText(currentAnswers.get(1).getName());
        answer3.setText(currentAnswers.get(2).getName());
        answer4.setText(currentAnswers.get(3).getName());

        currentQuestion++;
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
