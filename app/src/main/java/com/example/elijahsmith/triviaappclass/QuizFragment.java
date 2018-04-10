package com.example.elijahsmith.triviaappclass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizFragment extends Fragment {

    @BindView(R.id.textView)
    protected TextView textView;
    @BindView(R.id.answerone_button)
    protected Button answerOne;
    @BindView(R.id.answertwo_button)
    protected Button answerTwo;
    @BindView(R.id.answer_three_button)
    protected Button answerThree;
    @BindView(R.id.answer_four_button)
    protected Button answerFour;
    private QuizCallback quizCallback;
    private List<Question> questionsList;
    public static final String QUESTIONS_LIST = "questions_list";
    private Question question;
    private int questionListPosition = 0;
    private int correctAnswers = 0;

    public static QuizFragment newInstance() {

        Bundle args = new Bundle();

        QuizFragment fragment = new QuizFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void attachParent(MainActivity mainActivity) {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    public void attachView(QuizCallback quizCallback) {
        this.quizCallback = quizCallback;
    }

    public interface QuizCallback {

        void quizFinished(int correctAnswers);
    }

    private void populateQuizContent() {

        question = questionsList.get(questionListPosition);
        textView.setText(question.getTitle());

        List<Button> buttonList = new ArrayList<>();
        buttonList.add(answerOne);
        buttonList.add(answerTwo);
        buttonList.add(answerThree);
        buttonList.add(answerFour);

        List<String> possibleAnswerList = new ArrayList<>();
        possibleAnswerList.add(question.getCorrectAnswer());
        possibleAnswerList.add(question.getWronganswer());
        possibleAnswerList.add(question.getWronganswer2());
        possibleAnswerList.add(question.getWronganser3());

        for (Button button : buttonList) {
            int random = (int) (Math.random() * possibleAnswerList.size() - 1);
            button.setText(possibleAnswerList.get(random));
            possibleAnswerList.remove(random);
        }


    }

    private void checkAnswer(String answer) {
        disableButtons();
        questionListPosition++;
        if (question.getCorrectAnswer().equals(answer)){
            textView.setText(R.string.correct);
            correctAnswers++;

        } else {
            textView.setText(getString(R.string.wrong_answer_text,question.getCorrectAnswer()));



        }


    }

    @Override
    public void onStart() {
        super.onStart();
        questionsList = new ArrayList<>();
        questionsList = getArguments().getParcelableArrayList(QUESTIONS_LIST);
        populateQuizContent();

    }

    @OnClick(R.id.next_button)
    protected void nextButtonClicked() {
        enableButtons();
        if (questionListPosition <= questionsList.size()-1) {
            populateQuizContent();


        } else {
            quizCallback.quizFinished(correctAnswers);
            enableButtons();
        }



    }

    @OnClick(R.id.answerone_button)
    protected void buttonOneClicked() {
        checkAnswer(answerOne.getText().toString());

    }

    @OnClick(R.id.answertwo_button)
    protected void buttonTwoClicked() {
        checkAnswer(answerTwo.getText().toString());

    }

    @OnClick(R.id.answer_three_button)
    protected void buttonThreeClicked() {
        checkAnswer(answerThree.getText().toString());
    }

    @OnClick(R.id.answer_four_button)
    protected void buttonFourClicked() {
        checkAnswer(answerFour.getText().toString());
    }
    private void enableButtons() {
        answerOne.setEnabled(true);
        answerTwo.setEnabled(true);
        answerThree.setEnabled(true);
        answerFour.setEnabled(true);
    }
    private void disableButtons() {
        answerOne.setEnabled(false);
        answerTwo.setEnabled(false);
        answerThree.setEnabled(false);
        answerFour.setEnabled(false);
    }
}
