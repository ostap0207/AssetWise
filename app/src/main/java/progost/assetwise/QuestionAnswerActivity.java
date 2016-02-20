package progost.assetwise;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QuestionAnswerActivity extends ActionBarActivity {

    TextToSpeech ttobj;
    List<Question> questions = new ArrayList<>();
    Button yesButton;
    Button noButton;
    int currentQuestionId = 0;
    int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        initButtons();
        initQuestions();
        prepareTextToSpeech();
    }

    private void initButtons() {
        yesButton = (Button)findViewById(R.id.yesButton);
        noButton = (Button)findViewById(R.id.noButton);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result += questions.get(currentQuestionId).answer(Answer.YES);
                currentQuestionId++;
                sayNextQuestion();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result += questions.get(currentQuestionId).answer(Answer.NO);
                currentQuestionId++;
                sayNextQuestion();
            }
        });
    }

    private void initQuestions() {
        questions.add(new Question("How you doing?", 5, -5));
        questions.add(new Question("How you doing2?", 5, -5));
    }

    private void prepareTextToSpeech() {
        ttobj = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    ttobj.setLanguage(Locale.UK);
                    sayNextQuestion();
                }
            }
        });
    }

    private void sayNextQuestion() {
        String question = questions.get(currentQuestionId).getText();
        TextView textview = (TextView) this.findViewById(R.id.lable);
        textview.setText(question);
        ttobj.speak(question, TextToSpeech.QUEUE_FLUSH, null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_question_answer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
