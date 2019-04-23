package com.example.getanswerfromuser;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    private Button mButtonQuit;


    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    AddMove m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView = (TextView)findViewById(R.id.question);
        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice3 = (Button)findViewById(R.id.choice3);
        mButtonChoice4 = (Button)findViewById(R.id.choice4);
        mButtonQuit= (Button)findViewById(R.id.quit);


        updateQuestion();

        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice1.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    moveAdd(mAnswer,true);

                    updateQuestion();
                    //This line of code is options
                    Toast.makeText(MainActivity.this, "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    moveAdd(mButtonChoice1.getText().toString(),false);

                    updateQuestion();
                }
            }
        });


        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice2.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    moveAdd(mAnswer,true);

                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(MainActivity.this, "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    moveAdd(mButtonChoice2.getText().toString(),false);

                    updateQuestion();
                }
            }
        });


        //Start of Button Listener for Button3
        mButtonChoice3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice3.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    moveAdd(mAnswer,true);

                    updateQuestion();

                    Toast.makeText(MainActivity.this, "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    moveAdd(mButtonChoice3.getText().toString(),false);

                        updateQuestion();

                }
            }
        });


       //Start of Button Listener for Button4
        mButtonChoice4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice4.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    moveAdd(mAnswer,true);
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(MainActivity.this, "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    moveAdd(mButtonChoice4.getText().toString(),false);

                    updateQuestion();

                }
            }
        });


    // quit from application
    mButtonQuit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });


    }

    private void updateQuestion() {
        //pass to new question
        List<String> list;
        m = new AddMove();

        if(mQuestionLibrary.getQuestion(mQuestionNumber).isEmpty())
        {
            Intent intent = new Intent();
            //something to pass main menu
        }
        mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
        list = Arrays.asList(getChoices(mQuestionLibrary.getmContent(mQuestionNumber)));


        if(!list.contains(mAnswer)) {
            // if list does not have correct answer randomly, add it in choices
            Random rand = new Random();
            int n = rand.nextInt(4);
            list.set(n,mAnswer);
        }

        mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
        mButtonChoice1.setText(list.get(0));
        mButtonChoice2.setText(list.get(1));
        mButtonChoice3.setText(list.get(2));
        mButtonChoice4.setText(list.get(3));
        mQuestionNumber++;


    }


    private void updateScore(int point) {
        // update user score
        mScoreView.setText("" + mScore);
    }

    private String[] getChoices( String content) {
        //put choices on button randomly
        List<Integer> randomBtn ;
        String [] array = new String[4];

        String text = readFile(content+".txt");
        //open and read txt file according to choice tag
        String str [] = text.split("\n");
        randomBtn=getRandomNumber(4,str.length);
        //get number of choice data randomly for adding button
        for(int i=0;i<4;i++){
        for(String s : str){
            String[] readLine = s.split("\t");

            String label= readLine[0];
            String data = readLine[1];
            if( label.equals(randomBtn.get(i).toString())) {

                array[i]=data;

            }



        }}
        return array;
    }

    private List<Integer> getRandomNumber(int number, int limit){
        // to create different random number list for choices context
        List<Integer> list = new ArrayList<Integer>();
        List<Integer> last = new ArrayList<Integer>();

        for (int i=1; i<limit; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<number; i++) {
            last.add(list.get(i));
        }
        return last;
    }
    private String readFile (String file){
        // read file
        String text ="";
        try {
            InputStream is = getAssets().open(file);
            int size = is.available();
            byte [] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Error reading File", Toast.LENGTH_SHORT).show();
        }
        return text;
    }
    private void moveAdd(String answer, boolean check){
        // add moves to firebase as date, question, user answer, user answer is correct or not
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String date_move = dateFormat.format(date);

        m.create(date_move, mQuestionLibrary.getQuestion(mQuestionNumber-1), answer, check);
    }
}

