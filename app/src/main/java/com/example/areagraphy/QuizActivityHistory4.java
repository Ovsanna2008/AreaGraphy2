package com.example.areagraphy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuizActivityHistory4 extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "QuizActivityHistory4";
    TextView totalQuestionsTextview;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;
    Button lastSelectedButton;

    int score = 0;
    int totalQuestion;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    List<QuestionAnswerHistory4> questions = new ArrayList<>();
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_history4);
        ansA = findViewById(R.id.ansA);
        ansB = findViewById(R.id.ansB);
        ansC = findViewById(R.id.ansC);
        ansD = findViewById(R.id.ansD);
        submitBtn = findViewById(R.id.submit_btn);
        totalQuestionsTextview = findViewById(R.id.total_questions);
        questionTextView = findViewById(R.id.question);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance().getReference();

        fetchQuizQuestions();
    }

    private void fetchQuizQuestions() {
        database.child("quizQuestions4").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    QuestionAnswerHistory4 question = snapshot.getValue(QuestionAnswerHistory4.class);
                    questions.add(question);
                }
                totalQuestion = questions.size();
                totalQuestionsTextview.setText("Total questions: " + totalQuestion);
                loadNewQuestion();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    @Override
    public void onClick(View view) {
        int blue_light = ContextCompat.getColor(this, R.color.blue_light);
        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {
            // Check if an answer has been selected
            if (selectedAnswer.isEmpty()) {
                // If no answer is selected, show a message and return
                Toast.makeText(this, "Please select an answer before submitting", Toast.LENGTH_SHORT).show();
            } else {
                checkAnswer();
                loadNewQuestionWithDelay();
            }
        } else {
            selectedAnswer = clickedButton.getText().toString();
            if (clickedButton != submitBtn) {
                changeButtonColor(clickedButton, blue_light);
                if (lastSelectedButton != null && lastSelectedButton != clickedButton) {
                    resetButtonColor(lastSelectedButton);
                }
                lastSelectedButton = clickedButton;
            }
        }
    }

    void checkAnswer() {
        int green = ContextCompat.getColor(this, R.color.green);
        int red = ContextCompat.getColor(this, R.color.red);

        QuestionAnswerHistory4 currentQuestion = questions.get(currentQuestionIndex);

        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            score++;
            changeButtonColor(getSelectedAnswerButton(), green);
        } else {
            changeButtonColor(getSelectedAnswerButton(), red);
            changeButtonColor(getCorrectAnswerButton(), green);
        }

        // Reset selectedAnswer after checking the answer
        selectedAnswer = "";

        // Check if the user has passed the threshold score
        if (score >= 5) {
            startActivity(new Intent(this, StartUp.class));
        }
    }

    void loadNewQuestionWithDelay() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentQuestionIndex++;
                if (currentQuestionIndex < totalQuestion) {
                    loadNewQuestion();
                } else {
                    finishQuiz();
                }
            }
        }, 2000);
    }

    void loadNewQuestion() {
        if (currentQuestionIndex < questions.size()) {
            QuestionAnswerHistory4 question = questions.get(currentQuestionIndex);

            questionTextView.setText(question.getQuestion());
            ansA.setText(question.getOptionA());
            ansB.setText(question.getOptionB());
            ansC.setText(question.getOptionC());
            ansD.setText(question.getOptionD());

            resetButtonColors();
        }
    }

    void finishQuiz() {
        String passStatus = (score > totalQuestion * 0.60) ? "Great!" : "Try once more";
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is " + score + " out of " + totalQuestion)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                .show();
    }

    void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

    void changeButtonColor(Button button, int color) {
        button.setBackgroundColor(color);
    }

    void resetButtonColor(Button button) {
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.purple));
    }

    Button getSelectedAnswerButton() {
        if (selectedAnswer.equals(ansA.getText().toString())) return ansA;
        if (selectedAnswer.equals(ansB.getText().toString())) return ansB;
        if (selectedAnswer.equals(ansC.getText().toString())) return ansC;
        if (selectedAnswer.equals(ansD.getText().toString())) return ansD;
        return null;
    }

    Button getCorrectAnswerButton() {
        QuestionAnswerHistory4 currentQuestion = questions.get(currentQuestionIndex);
        if (currentQuestion.getCorrectAnswer().equals(ansA.getText().toString())) return ansA;
        if (currentQuestion.getCorrectAnswer().equals(ansB.getText().toString())) return ansB;
        if (currentQuestion.getCorrectAnswer().equals(ansC.getText().toString())) return ansC;
        if (currentQuestion.getCorrectAnswer().equals(ansD.getText().toString())) return ansD;
        return null;
    }

    void resetButtonColors() {
        int blue = ContextCompat.getColor(this, R.color.purple);
        ansA.setBackgroundColor(blue);
        ansB.setBackgroundColor(blue);
        ansC.setBackgroundColor(blue);
        ansD.setBackgroundColor(blue);
    }
}
