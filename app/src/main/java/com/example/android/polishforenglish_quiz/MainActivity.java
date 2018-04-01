package com.example.android.polishforenglish_quiz;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button submitButton;
    boolean isSubmitButtonClicked = false;
    Button showCorrectAnswersButton;
    boolean isShowCorrectAnswersButtonClicked = false;
    //    boolean isEditTextFocused = false;
    private final int NUMBER_OF_QUESTIONS = 7;
    int[][] viewIDsArray = new int[NUMBER_OF_QUESTIONS][];
    boolean[][] correctAnswersArray = new boolean[NUMBER_OF_QUESTIONS][];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getCurrentFocus();
        submitButton = findViewById(R.id.submit_button);
        showCorrectAnswersButton = findViewById(R.id.show_answers_button);
        assignViewIDs();
        assignCorrectAnswers();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isSubmitButtonClicked", isSubmitButtonClicked);
        outState.putBoolean("isCorrectAnswerButtonClicked", isShowCorrectAnswersButtonClicked);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isSubmitButtonClicked = savedInstanceState.getBoolean("isSubmitButtonClicked");
        isShowCorrectAnswersButtonClicked = savedInstanceState.getBoolean("isCorrectAnswerButtonClicked");
//        isEditTextFocused = savedInstanceState.getBoolean("isEditTextFocused");
        Log.v("MainActivity", "buttonflah: " + isSubmitButtonClicked);
        Log.v(this.toString(), "showccbu:" + isShowCorrectAnswersButtonClicked);
//        Log.v("main", "is it focused?: " + isEditTextFocused);

        if (isSubmitButtonClicked) {
            submitQuiz();
//            submitButton.setText(getString(R.string.try_again_button));
//            evaluateAllQuestions();
////            Toast.makeText(this, getString(R.string.show_score, evaluateAllQuestions()), Toast.LENGTH_LONG).show();
//            isSubmitButtonClicked = true;
//            showCorrectAnswersButton.setVisibility(View.VISIBLE);
        }
        if (isShowCorrectAnswersButtonClicked) {
            showCorrectAnswers(null);
        }
        clearEditTextFocus();


    }

    /*assign needed Views from activity_main.xml to the viewIDsArray
            * */
    void assignViewIDs() {
        viewIDsArray[0] = new int[]{R.id.q1_answer1_radio_button, R.id.q1_answer2_radio_button, R.id.q1_answer3_radio_button, R.id.q1_answer4_radio_button};
        viewIDsArray[1] = new int[]{R.id.q2_answer1_radio_button, R.id.q2_answer2_radio_button, R.id.q2_answer3_radio_button, R.id.q2_answer4_radio_button};
        viewIDsArray[2] = new int[]{R.id.q3_answer1_check_box, R.id.q3_answer2_check_box, R.id.q3_answer3_check_box, R.id.q3_answer4_check_box};
        viewIDsArray[3] = new int[]{R.id.q4_answer_edit_text};
        viewIDsArray[4] = new int[]{R.id.q5_answer1_radio_button, R.id.q5_answer2_radio_button, R.id.q5_answer3_radio_button, R.id.q5_answer4_radio_button};
        viewIDsArray[5] = new int[]{R.id.q6_answer1_radio_button, R.id.q6_answer2_radio_button, R.id.q6_answer3_radio_button, R.id.q6_answer4_radio_button};
        viewIDsArray[6] = new int[]{R.id.q7_answer1_check_box, R.id.q7_answer2_check_box, R.id.q7_answer3_check_box, R.id.q7_answer4_check_box};
    }

    /*assign solutions(good answers) of every question to an array
    * true - good answer
    * false - wrong answer
    * caution! question 4 is an EditText question so there is no value assigned*/
    void assignCorrectAnswers() {
        correctAnswersArray[0] = new boolean[]{false, false, true, false};
        correctAnswersArray[1] = new boolean[]{false, true, false, false};
        correctAnswersArray[2] = new boolean[]{false, true, false, true};
        correctAnswersArray[3] = new boolean[0];
        correctAnswersArray[4] = new boolean[]{false, false, true, false};
        correctAnswersArray[5] = new boolean[]{true, false, false, false};
        correctAnswersArray[6] = new boolean[]{true, true, false, true};
    }

    /*handle the submitButton
    * */
    public void submitButtonHandler(View view) {
        if (!isSubmitButtonClicked) {
            submitQuiz();
        } else {
            resetQuiz();
        }
    }

    /*submit the quiz
     * evaluates the quiz and gives a final score
     * changes isSubmitButtonClicked to true -> so the submitButton can invoke resetQuiz() method
     * */
    void submitQuiz() {
        scrollToTop();
        submitButton.setText(getString(R.string.try_again_button));
        double score = evaluateAllQuestions();
        if (!isSubmitButtonClicked) {
            showFinalScore(score);
        }
        isSubmitButtonClicked = true;
        if (score != NUMBER_OF_QUESTIONS) {
            showCorrectAnswersButton.setVisibility(View.VISIBLE);
        }
        clearEditTextFocus();
    }

    /*reset the quiz to its original view
    * set isSubmitButtonClicked to false so the submitButton can invoke submitQuiz() method
    * */
    void resetQuiz() {
        scrollToTop();
        submitButton.setText(getString(R.string.submit_button));
        resetAllAnswers();
        isSubmitButtonClicked = false;
        isShowCorrectAnswersButtonClicked = false;
        showCorrectAnswersButton.setVisibility(View.GONE);
    }

    /*show to the user correct answers*/
    void showCorrectAnswers(View view) {
        scrollToTop();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < correctAnswersArray[i].length; j++) {
                if (correctAnswersArray[i][j]) {
                    View answer = findViewById(viewIDsArray[i][j]);
                    answer.setBackgroundResource(R.drawable.correct_highlighted);
                }
            }
        }
        EditText q4answer = findViewById(R.id.q4_answer_edit_text);
        q4answer.setText(getString(R.string.q4_answer));
        q4answer.setBackgroundResource(R.drawable.correct_highlighted);
        showCorrectAnswersButton.setVisibility(View.GONE);
        isShowCorrectAnswersButtonClicked = true;

    }

    /*evaluate total score of user's trial*/
    double evaluateAllQuestions() {
        double totalScore = 0;
        for (int i = 1; i <= NUMBER_OF_QUESTIONS; i++) {
            if (i != 4) {
                totalScore += evaluateQuestion(i);
            }
        }
        totalScore += q4Evaluate();
        return totalScore;
    }

    /*evaluate question 4 - EditText question
    * different evaluation needed
    * @param return - 1 if good answer/ 0 if wrong
    * */
    int q4Evaluate() {
        EditText editTextView = findViewById(R.id.q4_answer_edit_text);
        Log.v("main", "class of editext:  " + editTextView.getClass());
        editTextView.setEnabled(false);
//        editTextView.clearFocus();
        String userAnswer = editTextView.getText().toString();
        if (userAnswer.equals("123")) {
            editTextView.setBackgroundResource(R.drawable.correct_highlighted);
            return 1;
        } else if (userAnswer.equals("")) {
            return 0;
        } else {
            editTextView.setBackgroundResource(R.drawable.wrong_highlighted);
            return 0;
        }
    }

    /*clear the EditTextView's focus
    * */
    void clearEditTextFocus() {
        EditText editTextView = findViewById(R.id.q4_answer_edit_text);
        editTextView.clearFocus();
    }

    /*evaluate score for a question
   * @param questionNumber - number of a question
   * @param return - score (0; 0.5; 1)
   * 0 if wrong
   * 1 if good
   * */
    double evaluateQuestion(int questionNumber) {
        // question for is an EditText question - this evaluation doesn't apply here
        if (questionNumber != 4) {

            /* assign buttons to good and bad answers
            * highlight good and wrong user's answers
            * set all the buttons setClickable(false)
            **/
            CompoundButton[] goodAnswers = new CompoundButton[3];
            CompoundButton[] wrongAnswers = new CompoundButton[3];
            int i = 0;
            int j = 0;
            for (int k = 0; k < correctAnswersArray[questionNumber - 1].length; k++) {
                if (correctAnswersArray[questionNumber - 1][k]) {
                    goodAnswers[i] = findViewById(viewIDsArray[questionNumber - 1][k]);
                    goodAnswers[i].setClickable(false);
                    if (goodAnswers[i].isChecked()) {
                        goodAnswers[i].setBackgroundResource(R.drawable.correct_highlighted);
                    }
                    i++;
                } else {
                    wrongAnswers[j] = findViewById(viewIDsArray[questionNumber - 1][k]);
                    wrongAnswers[j].setClickable(false);
                    if (wrongAnswers[j].isChecked()) {
                        wrongAnswers[j].setBackgroundResource(R.drawable.wrong_highlighted);
                    }
                    j++;
                }
            }
            /*If question factor is 1 :
            * 1 point if good is checked and no wrong checked
            * else 0 points
            */
            if (countQuestionFactor(questionNumber) == 1) {
                if (goodAnswers[0].isChecked() & !(wrongAnswers[0].isChecked() || wrongAnswers[1].isChecked() || wrongAnswers[2].isChecked())) {
                    return 1;
                }
                return 0;
            }
            /*If question factor is 2 :
            * 1 point if all the good are checked and no wrong checked
            * 0.5 point if at least one good checked and no wrong checked
            * else 0 points
            * */
            else if (countQuestionFactor(questionNumber) == 2) {
                if (goodAnswers[0].isChecked() & goodAnswers[1].isChecked() & !(wrongAnswers[0].isChecked() || wrongAnswers[1].isChecked())) {
                    return 1;
                } else if ((goodAnswers[0].isChecked() || goodAnswers[1].isChecked()) & !(wrongAnswers[0].isChecked() || wrongAnswers[1].isChecked())) {
                    return 0.5;
                } else return 0;
            }
            /*If question factor is 3 :
            * 1 point if all the good answers are checked and no wrong checked
            * 0.5 point if at least one good checked and no worng checked
            * else 0 points
            * */
            else if (countQuestionFactor(questionNumber) == 3) {
                if ((goodAnswers[0].isChecked() & goodAnswers[1].isChecked() & goodAnswers[2].isChecked()) & !wrongAnswers[0].isChecked()) {
                    return 1;
                } else if ((goodAnswers[0].isChecked() || goodAnswers[1].isChecked() || goodAnswers[2].isChecked()) & !wrongAnswers[0].isChecked()) {
                    return 0.5;
                } else return 0;
            }
            return 0;
        }
        return 0;
    }

    /*count how many good solutions (how many true values) each question has
    * @param questionNumber - number of a question
    * @param return - questionFactor - number of good solutions
    * */
    int countQuestionFactor(int questionNumber) {
        int questionFactor = 0;
        for (boolean item : correctAnswersArray[questionNumber - 1]) {
            if (item) {
                questionFactor++;
            }
        }
        return questionFactor;
    }

    /*show a final message with the user's score
    * */
    void showFinalScore(double score) {
        if (score != NUMBER_OF_QUESTIONS) {
            Toast.makeText(this, getString(R.string.show_score_not_max, score), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getString(R.string.show_score_max, score), Toast.LENGTH_LONG).show();
        }
    }

    /*restore activity_main.xml to its original view*/
    void resetAllAnswers() {
        for (int[] idArray : viewIDsArray) {
            for (int id : idArray) {
                if (idArray != viewIDsArray[3]) {
                    CompoundButton answer = findViewById(id);
                    answer.setBackgroundColor(View.INVISIBLE);
                    answer.setClickable(true);
                    answer.setChecked(false);
                } else {
                    EditText answer = findViewById(id);
                    answer.setText("");
                    answer.setBackgroundColor(View.INVISIBLE);
                    answer.setEnabled(true);
                }
            }
        }
    }

    /*scroll activity_main.xml to the top*/
    void scrollToTop() {
        ScrollView scrollView = findViewById(R.id.scroll_view);
        ObjectAnimator.ofInt(scrollView, "scrollY", ScrollView.FOCUS_UP).setDuration(1000).start();
    }


}
