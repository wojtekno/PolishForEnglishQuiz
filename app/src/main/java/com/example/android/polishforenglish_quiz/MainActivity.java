package com.example.android.polishforenglish_quiz;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button submitButton;
    boolean submitButtonFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitButton = findViewById(R.id.submit_button);
    }


    /*
    Evaluate question 1
    if nothitng checked @return false
    @return boolean - is answer good or not

     */
    boolean q1Evaluate() {
        RadioGroup radioGroup = findViewById(R.id.q1_radio_group);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setClickable(false);
        }
        RadioButton userAnswer = findViewById(radioGroup.getCheckedRadioButtonId());
        if (userAnswer == null) {
            return false;
        }
        RadioButton button3 = findViewById(R.id.q1_answer3_radio_button);
        if (button3.getId() != userAnswer.getId()) {
            userAnswer.setBackgroundResource(R.drawable.wrong_highlighted);
        }
        return button3.isChecked();
    }

    /*
    Restore question one to its default look - nothing checked, no "(wrong)" appendix
     */
    void q1Reset() {
        RadioGroup radioGroup = findViewById(R.id.q1_radio_group);
        radioGroup.clearCheck();
        radioGroup.getDrawableState();
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setClickable(true);
            radioGroup.getChildAt(i).setBackgroundColor(View.INVISIBLE);
        }
//        RadioButton button1 = findViewById(R.id.q1_answer1_radio_button);
//        button1.setText(getString(R.string.q1_answer1));
//        RadioButton button2 = findViewById(R.id.q1_answer2_radio_button);
//        button2.setText(getString(R.string.q1_answer2));
//        RadioButton button3 = findViewById(R.id.q1_answer3_radio_button);
//        button3.setText(getString(R.string.q1_answer3));
//        RadioButton button4 = findViewById(R.id.q1_answer4_radio_button);
//        button4.setText(getString(R.string.q1_answer4));
    }

    boolean q2Evaluate() {
        RadioGroup radioGroup = findViewById(R.id.q2_radio_group);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setClickable(false);
        }
        RadioButton userAnswer = findViewById(radioGroup.getCheckedRadioButtonId());
        if (userAnswer == null) {
            return false;
        }
        RadioButton button2 = findViewById(R.id.q2_answer2_radio_button);
        if (button2.getId() != userAnswer.getId()) {
            userAnswer.setBackgroundResource(R.drawable.wrong_highlighted);
        }
        return button2.isChecked();
    }

    void q2Reset() {
        RadioGroup radioGroup = findViewById(R.id.q2_radio_group);
        radioGroup.clearCheck();
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setClickable(true);
            radioGroup.getChildAt(i).setBackgroundColor(View.INVISIBLE);
        }
//        RadioButton button1 = findViewById(R.id.q2_answer1_radio_button);
//        button1.setText(getString(R.string.q2_answer1));
//        RadioButton button2 = findViewById(R.id.q2_answer2_radio_button);
//        button2.setText(getString(R.string.q2_answer2));
//        RadioButton button3 = findViewById(R.id.q2_answer3_radio_button);
//        button3.setText(getString(R.string.q2_answer3));
//        RadioButton button4 = findViewById(R.id.q2_answer4_radio_button);
//        button4.setText(getString(R.string.q2_answer4));
    }

    double q3Evaluate() {
        CheckBox answer1 = findViewById(R.id.q3_answer1_check_box);
        CheckBox answer2 = findViewById(R.id.q3_answer2_check_box);
        CheckBox answer3 = findViewById(R.id.q3_answer3_check_box);
        CheckBox answer4 = findViewById(R.id.q3_answer4_check_box);
        if (answer1.isChecked()) {
            answer1.setBackgroundResource(R.drawable.wrong_highlighted);
        }
        if (answer2.isChecked()) {
            answer2.setBackgroundResource(R.drawable.correct_highlighted);
        }
        if (answer3.isChecked()) {
            answer3.setBackgroundResource(R.drawable.wrong_highlighted);
        }
        if (answer4.isChecked()) {
            answer4.setBackgroundResource(R.drawable.correct_highlighted);
        }

        answer1.setClickable(false);
        answer2.setClickable(false);
        answer3.setClickable(false);
        answer4.setClickable(false);
        // 1 point for two good answers and no wrong answer
        if ((answer2.isChecked() & answer4.isChecked()) & !(answer1.isChecked() & answer3.isChecked())) {
            return 1;
        }
        // 0.5 point if at least one good is checked an no wrong
        else if ((answer2.isChecked() || answer4.isChecked()) & !(answer1.isChecked() || answer3.isChecked())) {
            return 0.5;
        } else {
            return 0;
        }
    }

    void q3Reset() {

        CheckBox answer1 = findViewById(R.id.q3_answer1_check_box);
        CheckBox answer2 = findViewById(R.id.q3_answer2_check_box);
        CheckBox answer3 = findViewById(R.id.q3_answer3_check_box);
        CheckBox answer4 = findViewById(R.id.q3_answer4_check_box);
        answer1.setBackgroundColor(View.INVISIBLE);
        answer2.setBackgroundColor(View.INVISIBLE);
        answer3.setBackgroundColor(View.INVISIBLE);
        answer4.setBackgroundColor(View.INVISIBLE);
        answer1.setClickable(true);
        answer2.setClickable(true);
        answer3.setClickable(true);
        answer4.setClickable(true);
        answer1.setChecked(false);
        answer2.setChecked(false);
        answer3.setChecked(false);
        answer4.setChecked(false);
    }

    boolean q4Evaluate() {
        EditText editTextView = findViewById(R.id.q4_answer_edit_text);
        editTextView.setEnabled(false);
        String userAnswer = editTextView.getText().toString();
        if (userAnswer.equals("123")) {
            editTextView.setBackgroundColor(R.drawable.correct_highlighted);
            return true;
        }
        editTextView.setBackgroundColor(R.drawable.wrong_highlighted);
        return false;
    }

    void q4Reset() {
        EditText editTextView = findViewById(R.id.q4_answer_edit_text);
//        editTextView.setHint(getString(R.string.q4_hint));
        editTextView.setText("");
        editTextView.setEnabled(true);
        editTextView.setBackgroundColor(View.INVISIBLE);
    }

    double q5Evaluate() {
        CheckBox answer1 = findViewById(R.id.q5_answer1_check_box);
        CheckBox answer2 = findViewById(R.id.q5_answer2_check_box);
        CheckBox answer3 = findViewById(R.id.q5_answer3_check_box);
        CheckBox answer4 = findViewById(R.id.q5_answer4_check_box);
        if (answer1.isChecked()) {
            answer1.setBackgroundResource(R.drawable.wrong_highlighted);
        }
        if (answer2.isChecked()) {
            answer2.setBackgroundResource(R.drawable.wrong_highlighted);
        }
        if (answer3.isChecked()) {
            answer3.setBackgroundResource(R.drawable.correct_highlighted);
        }
        if (answer4.isChecked()) {
            answer4.setBackgroundResource(R.drawable.wrong_highlighted);
        }

        answer1.setClickable(false);
        answer2.setClickable(false);
        answer3.setClickable(false);
        answer4.setClickable(false);
        // 1 point when asswer 3 is checked
        if (answer3.isChecked() & !(answer1.isChecked() || answer2.isChecked() || answer4.isChecked())) {
            return 1;
        }
        return 0;
    }


    void q5Reset() {
        CheckBox answer1 = findViewById(R.id.q5_answer1_check_box);
        CheckBox answer2 = findViewById(R.id.q5_answer2_check_box);
        CheckBox answer3 = findViewById(R.id.q5_answer3_check_box);
        CheckBox answer4 = findViewById(R.id.q5_answer4_check_box);
        answer1.setBackgroundColor(View.INVISIBLE);
        answer2.setBackgroundColor(View.INVISIBLE);
        answer3.setBackgroundColor(View.INVISIBLE);
        answer4.setBackgroundColor(View.INVISIBLE);
        answer1.setClickable(true);
        answer2.setClickable(true);
        answer3.setClickable(true);
        answer4.setClickable(true);
        answer1.setChecked(false);
        answer2.setChecked(false);
        answer3.setChecked(false);
        answer4.setChecked(false);
    }

    double q6Evaluate() {
        CheckBox answer1 = findViewById(R.id.q6_answer1_check_box);
        CheckBox answer2 = findViewById(R.id.q6_answer2_check_box);
        CheckBox answer3 = findViewById(R.id.q6_answer3_check_box);
        CheckBox answer4 = findViewById(R.id.q6_answer4_check_box);
        if (answer1.isChecked()) {
            answer1.setBackgroundResource(R.drawable.correct_highlighted);
        }
        if (answer2.isChecked()) {
            answer2.setBackgroundResource(R.drawable.wrong_highlighted);
        }
        if (answer3.isChecked()) {
            answer3.setBackgroundResource(R.drawable.wrong_highlighted);
        }
        if (answer4.isChecked()) {
            answer4.setBackgroundResource(R.drawable.wrong_highlighted);
        }

        answer1.setClickable(false);
        answer2.setClickable(false);
        answer3.setClickable(false);
        answer4.setClickable(false);
        // 1 point when asswer 3 is checked
        if (answer1.isChecked() & !(answer2.isChecked() || answer3.isChecked() || answer4.isChecked())) {
            return 1;
        }
        return 0;
    }

    void q6Reset() {
        CheckBox answer1 = findViewById(R.id.q6_answer1_check_box);
        CheckBox answer2 = findViewById(R.id.q6_answer2_check_box);
        CheckBox answer3 = findViewById(R.id.q6_answer3_check_box);
        CheckBox answer4 = findViewById(R.id.q6_answer4_check_box);
        answer1.setBackgroundColor(View.INVISIBLE);
        answer2.setBackgroundColor(View.INVISIBLE);
        answer3.setBackgroundColor(View.INVISIBLE);
        answer4.setBackgroundColor(View.INVISIBLE);
        answer1.setClickable(true);
        answer2.setClickable(true);
        answer3.setClickable(true);
        answer4.setClickable(true);
        answer1.setChecked(false);
        answer2.setChecked(false);
        answer3.setChecked(false);
        answer4.setChecked(false);
    }

    double q7Evaluate() {
        CheckBox answer1 = findViewById(R.id.q7_answer1_check_box);
        CheckBox answer2 = findViewById(R.id.q7_answer2_check_box);
        CheckBox answer3 = findViewById(R.id.q7_answer3_check_box);
        CheckBox answer4 = findViewById(R.id.q7_answer4_check_box);
        if (answer1.isChecked()) {
            answer1.setBackgroundResource(R.drawable.correct_highlighted);
        }
        if (answer2.isChecked()) {
            answer2.setBackgroundResource(R.drawable.correct_highlighted);
        }
        if (answer3.isChecked()) {
            answer3.setBackgroundResource(R.drawable.wrong_highlighted);
        }
        if (answer4.isChecked()) {
            answer4.setBackgroundResource(R.drawable.correct_highlighted);
        }

        answer1.setClickable(false);
        answer2.setClickable(false);
        answer3.setClickable(false);
        answer4.setClickable(false);
        // 1 point when all three answers are checked and wrong is unchecked
        if ((answer1.isChecked() & answer2.isChecked() & answer4.isChecked()) & !answer4.isChecked()) {
            return 1;
        }
        //when at least one correct is checked and wrong isn't
        else if((answer1.isChecked() || answer2.isChecked() || answer4.isChecked()) & !answer4.isChecked()) {
            return 0.5;
        }
        return 0;
    }

    void q7Reset() {
        CheckBox answer1 = findViewById(R.id.q7_answer1_check_box);
        CheckBox answer2 = findViewById(R.id.q7_answer2_check_box);
        CheckBox answer3 = findViewById(R.id.q7_answer3_check_box);
        CheckBox answer4 = findViewById(R.id.q7_answer4_check_box);
        answer1.setBackgroundColor(View.INVISIBLE);
        answer2.setBackgroundColor(View.INVISIBLE);
        answer3.setBackgroundColor(View.INVISIBLE);
        answer4.setBackgroundColor(View.INVISIBLE);
        answer1.setClickable(true);
        answer2.setClickable(true);
        answer3.setClickable(true);
        answer4.setClickable(true);
        answer1.setChecked(false);
        answer2.setChecked(false);
        answer3.setChecked(false);
        answer4.setChecked(false);
    }

    public void submitQuiz() {
        submitButton.setText(getString(R.string.try_again_button));
        double score = 0;
        if (q1Evaluate()) {
            score += 1;
        }
        if (q2Evaluate()) {
            score += 1;
        }
        score += q3Evaluate();
        if (q4Evaluate()) {
            score += 1;
        }
        score += q5Evaluate();
        score +=q6Evaluate();
        score += q7Evaluate();
        Toast.makeText(this, getString(R.string.show_score, score), Toast.LENGTH_LONG).show();
        submitButtonFlag = true;
    }

    void resetQuiz() {
        ScrollView scrollView = findViewById(R.id.scroll_view);
        ObjectAnimator.ofInt(scrollView, "scrollY", ScrollView.FOCUS_UP).setDuration(1000).start();
        submitButton.setText(getString(R.string.submit_button));
        q1Reset();
        q2Reset();
        q3Reset();
        q4Reset();
        q5Reset();
        q6Reset();
        q7Reset();
        submitButtonFlag = false;
    }

    void submitButtonHandler(View view) {
        Button showAnswerButton = findViewById(R.id.show_answers_button);
        if (!submitButtonFlag) {
            submitQuiz();

            showAnswerButton.setVisibility(View.VISIBLE);
        } else {
            resetQuiz();
            showAnswerButton.setVisibility(View.INVISIBLE);
        }
    }

    void showAnswers(View view) {
        Button showAnswersButton = findViewById(R.id.show_answers_button);
        showAnswersButton.setVisibility(View.INVISIBLE);
        RadioButton q1answer = findViewById(R.id.q1_answer3_radio_button);
        q1answer.setBackgroundResource(R.drawable.correct_highlighted);
        RadioButton q2answer = findViewById(R.id.q2_answer2_radio_button);
        q2answer.setBackgroundResource(R.drawable.correct_highlighted);
        CheckBox q3answer2 = findViewById(R.id.q3_answer2_check_box);
        q3answer2.setBackgroundResource(R.drawable.correct_highlighted);
        CheckBox q3answer4 = findViewById(R.id.q3_answer4_check_box);
        q3answer4.setBackgroundResource(R.drawable.correct_highlighted);
        EditText q4answer = findViewById(R.id.q4_answer_edit_text);
        q4answer.setText(getString(R.string.q4_answer));
        q4answer.setBackgroundResource(R.drawable.correct_highlighted);
        CheckBox q5answer3 = findViewById(R.id.q5_answer3_check_box);
        q5answer3.setBackgroundResource(R.drawable.correct_highlighted);
        CheckBox q6answer1 = findViewById(R.id.q6_answer1_check_box);
        q6answer1.setBackgroundResource(R.drawable.correct_highlighted);
        CheckBox q7answer1 = findViewById(R.id.q7_answer1_check_box);
        q7answer1.setBackgroundResource(R.drawable.correct_highlighted);
        CheckBox q7answer2 = findViewById(R.id.q7_answer2_check_box);
        q7answer2.setBackgroundResource(R.drawable.correct_highlighted);
        CheckBox q7answer4 = findViewById(R.id.q7_answer4_check_box);
        q7answer4.setBackgroundResource(R.drawable.correct_highlighted);


    }
}
