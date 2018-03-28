package com.example.elijahsmith.triviaappclass;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionCreatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionCreatorFragment extends Fragment {
    @BindView(R.id.question_titel_edittex)
    protected EditText questionInput;
    @BindView(R.id.correct_edittext)
    protected EditText correctAnswer;
    @BindView(R.id.incorrect_editext)
    protected EditText incorrect;
    @BindView(R.id.incorrecttwo_edittext)
    protected EditText incorrectTwo;
    @BindView(R.id.incorrect_three_edittext)
    protected EditText incorrectthree;
    private Callback callback;


    // TODO: Rename and change types and number of parameters
    public static QuestionCreatorFragment newInstance() {
        QuestionCreatorFragment fragment = new QuestionCreatorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_creator, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    public void attachParent(Callback callback) {
        this.callback = callback;


    }

    @OnClick(R.id.save_button)
    protected void saveButtonClicked() {
        if (questionInput.getText().toString().isEmpty() ||
                correctAnswer.getText().toString().isEmpty() ||
                incorrect.getText().toString().isEmpty() ||
                incorrectTwo.getText().toString().isEmpty() ||
                incorrectthree.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Please Fill in all Options", Toast.LENGTH_LONG).show();


        } else {
            String questionTitle = questionInput.getText().toString();
            String correct = correctAnswer.getText().toString();
            String incorrectOne = incorrect.getText().toString();
            String wrongtwo = incorrectTwo.getText().toString();
            String wrongThree = incorrectthree.getText().toString();


            Question question = new Question(questionTitle, correct, incorrectOne, wrongtwo, wrongThree);
            callback.saveQuestion(question);

        }
    }

    public interface Callback {
        void saveQuestion(Question question);
    }

}
