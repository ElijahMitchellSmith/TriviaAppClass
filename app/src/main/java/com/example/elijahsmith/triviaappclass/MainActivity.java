package com.example.elijahsmith.triviaappclass;

import android.content.DialogInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements QuestionCreatorFragment.Callback, QuizFragment.QuizCallback {
    private QuestionCreatorFragment questionCreatorFragment;
    private QuizFragment quizFragment;
    private List<Question> questionList;
    public static final String QUESTIONS_LIST = "questions_list";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        questionList = new ArrayList<>();

    }

    @OnClick(R.id.add_question_button)
    protected void addQuestionClicked(View view) {

        questionCreatorFragment = QuestionCreatorFragment.newInstance();
        questionCreatorFragment.attachParent(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, questionCreatorFragment).commit();


    }


    @Override
    public void saveQuestion(Question question) {
        questionList.add(question);
        Toast.makeText(this, "Question Added", Toast.LENGTH_LONG).show();
        getSupportFragmentManager().beginTransaction().remove(questionCreatorFragment).commit();


    }

    @OnClick(R.id.take_quiz_button)
    protected void takeQuizClicked() {
        if (questionList.isEmpty()) {
            Toast.makeText(this, "You need to add questions.", Toast.LENGTH_SHORT).show();
        } else {
            quizFragment = QuizFragment.newInstance();
            quizFragment.attachView(this);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, quizFragment).commit();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(QUESTIONS_LIST, (ArrayList<? extends Parcelable>) questionList);
            quizFragment.setArguments(bundle);

        }
    }

    @Override
    public void quizFinished(int correctAnswers) {
        getSupportFragmentManager().beginTransaction().remove(quizFragment).commit();
        AlertDialog.Builder correctDialog = new AlertDialog.Builder(this);
        correctDialog.setMessage(getString(R.string.correct_questions, correctAnswers));
        correctDialog.create();
        correctDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        AlertDialog dialog = correctDialog.create();
        dialog.show();

    }

    @OnClick(R.id.delete_quiz_button)
    protected void deleteQuizClicked() {
        if (questionList.isEmpty()) {
            Toast.makeText(this, "There are no questions to delete!", Toast.LENGTH_SHORT).show();

        } else {
            final AlertDialog.Builder deleteDialog = new AlertDialog.Builder(this);
            deleteDialog.setMessage("Are you sure you would like to delete the quiz?").setNegativeButton(R.string.deletion_yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    questionList.clear();
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, R.string.quiz_deleted, Toast.LENGTH_LONG).show();
                }
            }).setPositiveButton("No!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog newDialog = deleteDialog.create();
            newDialog.show();

        }
    }
}