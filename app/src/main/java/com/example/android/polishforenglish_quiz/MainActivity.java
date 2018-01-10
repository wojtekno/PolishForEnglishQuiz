package com.example.android.polishforenglish_quiz;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    CheckBox[] checkBoxAnswers = new CheckBox[4];
    String[][] answers = new String[7][];
    int[][] viewIDs = new int[7][];
    boolean[][] correctAnswers = new boolean[7][];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitButton = findViewById(R.id.submit_button);
        assignStringValus();
        assignViewIDs();
    }

    void assignStringValus() {
        answers[0] = getResources().getStringArray(R.array.q1_array);
        answers[1] = getResources().getStringArray(R.array.q2_array);
        Log.v("Main", "ans1,2" + answers[1][1]);
//        answers[2] = getResources().getStringArray(R.array.q3_array);
    }

    void assignViewIDs() {

        int[] q1IDs = {R.id.q1_answer1_radio_button, R.id.q1_answer2_radio_button, R.id.q1_answer3_radio_button, R.id.q1_answer4_radio_button};
        boolean[] correctAnswersQ1 = {false, false, true, false};
        int[] q2IDs = {R.id.q2_answer1_radio_button, R.id.q2_answer2_radio_button, R.id.q2_answer3_radio_button, R.id.q2_answer4_radio_button};
        boolean[] correctAnswersQ2 = {false, true, false, false};
        int[] q3IDs = {R.id.q3_answer1_check_box, R.id.q3_answer2_check_box, R.id.q3_answer3_check_box, R.id.q3_answer4_check_box};
        boolean[] correctAnswersQ3 = {false, true, false, true};
        int[] q4IDs = {R.id.q4_answer_edit_text};
        boolean[] correctAnswersQ4 = new boolean[0];
        int[] q5IDs = {R.id.q5_answer1_check_box, R.id.q5_answer2_check_box, R.id.q5_answer3_check_box, R.id.q5_answer4_check_box};
        boolean[] correctAnswersQ5 = {false, false, true, false};
        int[] q6IDs = {R.id.q6_answer1_check_box, R.id.q6_answer2_check_box, R.id.q6_answer3_check_box, R.id.q6_answer4_check_box};
        boolean[] correctAnswersQ6 = {true, false, false, false};
        int[] q7IDs = {R.id.q7_answer1_check_box, R.id.q7_answer2_check_box, R.id.q7_answer3_check_box, R.id.q7_answer4_check_box};
        boolean[] correctAnswersQ7 = {true, true, false, true};
        viewIDs[0] = q1IDs;
        viewIDs[1] = q2IDs;
        viewIDs[2] = q3IDs;
        viewIDs[3] = q4IDs;
        viewIDs[4] = q5IDs;
        viewIDs[5] = q6IDs;
        viewIDs[6] = q7IDs;
        correctAnswers[0] = correctAnswersQ1;
        correctAnswers[1] = correctAnswersQ2;
        correctAnswers[2] = correctAnswersQ3;
        correctAnswers[3] = correctAnswersQ4;
        correctAnswers[4] = correctAnswersQ5;
        correctAnswers[5] = correctAnswersQ6;
        correctAnswers[6] = correctAnswersQ7;
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

        for (int i = 0; i < correctAnswers[0].length; i++) {
            if (correctAnswers[0][i]) {
                if (radioGroup.getCheckedRadioButtonId() != viewIDs[0][i]) {
                    userAnswer.setBackgroundResource(R.drawable.wrong_highlighted);
                    return false;
                } else {
                    userAnswer.setBackgroundResource(R.drawable.correct_highlighted);
                    return true;
                }
            }
        }
        return false;
    }


    /*
    Restore question one to its default look - nothing checked, no "(wrong)" appendix
     */
    void q1Reset() {
        RadioGroup radioGroup = findViewById(R.id.q1_radio_group);
        radioGroup.clearCheck();
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setClickable(true);
            radioGroup.getChildAt(i).setBackgroundColor(View.INVISIBLE);
        }
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
        for (int i = 0; i < correctAnswers[1].length; i++) {
            if (correctAnswers[1][i]) {
                if (radioGroup.getCheckedRadioButtonId() != viewIDs[1][i]) {
                    userAnswer.setBackgroundResource(R.drawable.wrong_highlighted);
                    return false;
                } else {
                    userAnswer.setBackgroundResource(R.drawable.correct_highlighted);
                    return true;
                }
            }
        }
        return false;
    }

    void q2Reset() {
        RadioGroup radioGroup = findViewById(R.id.q2_radio_group);
        radioGroup.clearCheck();
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setClickable(true);
            radioGroup.getChildAt(i).setBackgroundColor(View.INVISIBLE);
        }
    }

    double q3Evaluate() {

        for (int i = 0; i < checkBoxAnswers.length; i++) {
            checkBoxAnswers[i] = findViewById(viewIDs[2][i]);
            checkBoxAnswers[i].setClickable(false);
            if (checkBoxAnswers[i].isChecked()) {
                if (correctAnswers[2][i]) {
                    checkBoxAnswers[i].setBackgroundResource(R.drawable.correct_highlighted);
                } else {
                    checkBoxAnswers[i].setBackgroundResource(R.drawable.wrong_highlighted);
                }
            }
        }

        if ((checkBoxAnswers[1].isChecked() & checkBoxAnswers[3].isChecked()) & !(checkBoxAnswers[0].isChecked() & checkBoxAnswers[2].isChecked())) {
            return 1;
        }
        // 0.5 point if at least one good is checked an no wrong
        else if ((checkBoxAnswers[1].isChecked() || checkBoxAnswers[3].isChecked()) & !(checkBoxAnswers[0].isChecked() || checkBoxAnswers[2].isChecked())) {
            return 0.5;
        } else {
            return 0;
        }
    }

    void q3Reset() {
//
//        for (int i = 0; i < checkBoxAnswers.length; i++) {
//            checkBoxAnswers[i] = findViewById(viewIDs[2][i]);
//            checkBoxAnswers[i].setBackgroundColor(View.INVISIBLE);
//            checkBoxAnswers[i].setClickable(true);
//            checkBoxAnswers[i].setChecked(false);
//        }
        int i = 0;
        for (CheckBox answer : checkBoxAnswers) {
            answer = findViewById(viewIDs[2][i]);
            answer.setBackgroundColor(View.INVISIBLE);
            answer.setClickable(true);
            answer.setChecked(false);
            i++;
        }
    }
//TODO: apply automation from q3Evaluate
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

        for (int i = 0; i < checkBoxAnswers.length; i++) {
            checkBoxAnswers[i] = findViewById(viewIDs[4][i]);
            checkBoxAnswers[i].setClickable(false);
        }
//        checkBoxAnswers[0] = findViewById(R.id.q5_answer1_check_box);
//        checkBoxAnswers[1] = findViewById(R.id.q5_answer2_check_box);
//        checkBoxAnswers[2] = findViewById(R.id.q5_answer3_check_box);
//        checkBoxAnswers[3] = findViewById(R.id.q5_answer4_check_box);

        if (checkBoxAnswers[0].isChecked()) {
            checkBoxAnswers[0].setBackgroundResource(R.drawable.wrong_highlighted);
        }
        if (checkBoxAnswers[1].isChecked()) {
            checkBoxAnswers[1].setBackgroundResource(R.drawable.wrong_highlighted);
        }
        if (checkBoxAnswers[2].isChecked()) {
            checkBoxAnswers[2].setBackgroundResource(R.drawable.correct_highlighted);
        }
        if (checkBoxAnswers[3].isChecked()) {
            checkBoxAnswers[3].setBackgroundResource(R.drawable.wrong_highlighted);
        }


        // 1 point when asswer 3 is checked
        if (checkBoxAnswers[2].isChecked() & !(checkBoxAnswers[0].isChecked() || checkBoxAnswers[1].isChecked() || checkBoxAnswers[3].isChecked())) {
            return 1;
        }
        return 0;
    }


    void q5Reset() {

        for (int i = 0; i < checkBoxAnswers.length; i++) {
            checkBoxAnswers[i] = findViewById(viewIDs[4][i]);
            checkBoxAnswers[i].setBackgroundColor(View.INVISIBLE);
            checkBoxAnswers[i].setClickable(true);
            checkBoxAnswers[i].setChecked(false);
        }


//        CheckBox answer1 = findViewById(R.id.q5_answer1_check_box);
//        CheckBox answer2 = findViewById(R.id.q5_answer2_check_box);
//        CheckBox answer3 = findViewById(R.id.q5_answer3_check_box);
//        CheckBox answer4 = findViewById(R.id.q5_answer4_check_box);
//        answer1.setBackgroundColor(View.INVISIBLE);
//        answer2.setBackgroundColor(View.INVISIBLE);
//        answer3.setBackgroundColor(View.INVISIBLE);
//        answer4.setBackgroundColor(View.INVISIBLE);
//        answer1.setClickable(true);
//        answer2.setClickable(true);
//        answer3.setClickable(true);
//        answer4.setClickable(true);
//        answer1.setChecked(false);
//        answer2.setChecked(false);
//        answer3.setChecked(false);
//        answer4.setChecked(false);
    }

    double q6Evaluate() {

        for (int i = 0; i < checkBoxAnswers.length; i++) {
            checkBoxAnswers[i] = findViewById(viewIDs[5][i]);
            checkBoxAnswers[i].setClickable(false);
        }
//
//        checkBoxAnswers[0] = findViewById(R.id.q6_answer1_check_box);
//        checkBoxAnswers[1] = findViewById(R.id.q6_answer2_check_box);
//        checkBoxAnswers[2] = findViewById(R.id.q6_answer3_check_box);
//        checkBoxAnswers[3] = findViewById(R.id.q6_answer4_check_box);

        if (checkBoxAnswers[0].isChecked()) {
            checkBoxAnswers[0].setBackgroundResource(R.drawable.correct_highlighted);
        }
        if (checkBoxAnswers[1].isChecked()) {
            checkBoxAnswers[1].setBackgroundResource(R.drawable.wrong_highlighted);
        }
        if (checkBoxAnswers[2].isChecked()) {
            checkBoxAnswers[2].setBackgroundResource(R.drawable.wrong_highlighted);
        }
        if (checkBoxAnswers[3].isChecked()) {
            checkBoxAnswers[3].setBackgroundResource(R.drawable.wrong_highlighted);
        }

        // 1 point when asswer 3 is checked
        if (checkBoxAnswers[0].isChecked() & !(checkBoxAnswers[1].isChecked() || checkBoxAnswers[2].isChecked() || checkBoxAnswers[3].isChecked())) {
            return 1;
        }
        return 0;
    }

    void q6Reset() {
//        CheckBox answer1 = findViewById(R.id.q6_answer1_check_box);
//        CheckBox answer2 = findViewById(R.id.q6_answer2_check_box);
//        CheckBox answer3 = findViewById(R.id.q6_answer3_check_box);
//        CheckBox answer4 = findViewById(R.id.q6_answer4_check_box);
//        answer1.setBackgroundColor(View.INVISIBLE);
//        answer2.setBackgroundColor(View.INVISIBLE);
//        answer3.setBackgroundColor(View.INVISIBLE);
//        answer4.setBackgroundColor(View.INVISIBLE);
//        answer1.setClickable(true);
//        answer2.setClickable(true);
//        answer3.setClickable(true);
//        answer4.setClickable(true);
//        answer1.setChecked(false);
//        answer2.setChecked(false);
//        answer3.setChecked(false);
//        answer4.setChecked(false);
        for (int i = 0; i < checkBoxAnswers.length; i++) {
            checkBoxAnswers[i] = findViewById(viewIDs[5][i]);
            checkBoxAnswers[i].setBackgroundColor(View.INVISIBLE);
            checkBoxAnswers[i].setClickable(true);
            checkBoxAnswers[i].setChecked(false);
        }

    }

    double q7Evaluate() {

        for (int i = 0; i < checkBoxAnswers.length; i++) {
            checkBoxAnswers[i] = findViewById(viewIDs[6][i]);
            checkBoxAnswers[i].setClickable(true);
        }
//        checkBoxAnswers[0] = findViewById(R.id.q7_answer1_check_box);
//        checkBoxAnswers[1] = findViewById(R.id.q7_answer2_check_box);
//        checkBoxAnswers[2] = findViewById(R.id.q7_answer3_check_box);
//        checkBoxAnswers[3] = findViewById(R.id.q7_answer4_check_box);

        if (checkBoxAnswers[0].isChecked()) {
            checkBoxAnswers[0].setBackgroundResource(R.drawable.correct_highlighted);
        }
        if (checkBoxAnswers[1].isChecked()) {
            checkBoxAnswers[1].setBackgroundResource(R.drawable.correct_highlighted);
        }
        if (checkBoxAnswers[2].isChecked()) {
            checkBoxAnswers[2].setBackgroundResource(R.drawable.wrong_highlighted);
        }
        if (checkBoxAnswers[3].isChecked()) {
            checkBoxAnswers[3].setBackgroundResource(R.drawable.correct_highlighted);
        }

        // 1 point when all three answers are checked and wrong is unchecked
        if ((checkBoxAnswers[0].isChecked() & checkBoxAnswers[1].isChecked() & checkBoxAnswers[3].isChecked()) & !checkBoxAnswers[2].isChecked()) {
            return 1;
        }
        //when at least one correct is checked and wrong isn't
        else if ((checkBoxAnswers[0].isChecked() || checkBoxAnswers[1].isChecked() || checkBoxAnswers[3].isChecked()) & !checkBoxAnswers[2].isChecked()) {
            return 0.5;
        }
        return 0;
    }

    void q7Reset() {
//        CheckBox answer1 = findViewById(R.id.q7_answer1_check_box);
//        CheckBox answer2 = findViewById(R.id.q7_answer2_check_box);
//        CheckBox answer3 = findViewById(R.id.q7_answer3_check_box);
//        CheckBox answer4 = findViewById(R.id.q7_answer4_check_box);
//        answer1.setBackgroundColor(View.INVISIBLE);
//        answer2.setBackgroundColor(View.INVISIBLE);
//        answer3.setBackgroundColor(View.INVISIBLE);
//        answer4.setBackgroundColor(View.INVISIBLE);
//        answer1.setClickable(true);
//        answer2.setClickable(true);
//        answer3.setClickable(true);
//        answer4.setClickable(true);
//        answer1.setChecked(false);
//        answer2.setChecked(false);
//        answer3.setChecked(false);
//        answer4.setChecked(false);

        for (int i = 0; i < checkBoxAnswers.length; i++) {
            checkBoxAnswers[i] = findViewById(viewIDs[6][i]);
            checkBoxAnswers[i].setBackgroundColor(View.INVISIBLE);
            checkBoxAnswers[i].setClickable(true);
            checkBoxAnswers[i].setChecked(false);
        }
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
        score += q6Evaluate();
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
