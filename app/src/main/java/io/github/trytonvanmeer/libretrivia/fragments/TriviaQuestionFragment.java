package io.github.trytonvanmeer.libretrivia.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import io.github.trytonvanmeer.libretrivia.R;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuestion;
import io.github.trytonvanmeer.libretrivia.trivia.TriviaQuestionMultiple;


public class TriviaQuestionFragment extends Fragment {

    private static final int radioButtonAnswerOneID = R.id.radio_button_answer_one;
    private static final int radioButtonAnswerTwoID = R.id.radio_button_answer_two;
    private static final int radioButtonAnswerThreeID = R.id.radio_button_answer_three;
    private static final int radioButtonAnswerFourID = R.id.radio_button_answer_four;

    private TriviaQuestion question;
    private RadioGroup radioGroup;

    @BindViews({
            radioButtonAnswerOneID,
            radioButtonAnswerTwoID,
            radioButtonAnswerThreeID,
            radioButtonAnswerFourID
    })
    RadioButton[] radioButtonAnswers;

    public TriviaQuestionFragment() {}

    public static TriviaQuestionFragment newInstance(TriviaQuestion question) {
        TriviaQuestionFragment fragment = new TriviaQuestionFragment();
        fragment.question = question;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;

        if (question instanceof TriviaQuestionMultiple) {
            view = inflater.inflate(R.layout.fragment_trivia_question_multiple, container, false);
            ButterKnife.bind(this, view);
            setupRadioButtons();
        } else {
            view = inflater.inflate(R.layout.fragment_trivia_question_boolean, container, false);
        }

        this.radioGroup = view.findViewById(R.id.radio_group_answers);

        TextView textViewQuestion = view.findViewById(R.id.text_trivia_question);
        textViewQuestion.setText(question.getQuestion());

        return view;
    }

    private void setupRadioButtons() {
        List<String> answers = Arrays.asList((
                (TriviaQuestionMultiple) this.question).getAnswerList());
        Collections.shuffle(answers);

        radioButtonAnswers[0].setText(answers.get(0));
        radioButtonAnswers[1].setText(answers.get(1));
        radioButtonAnswers[2].setText(answers.get(2));
        radioButtonAnswers[3].setText(answers.get(3));
    }

    public String getSelectedAnswer() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton selected = radioGroup.findViewById(selectedId);
        return selected.getText().toString();
    }
}
