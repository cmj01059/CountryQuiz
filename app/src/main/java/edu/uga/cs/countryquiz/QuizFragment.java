package edu.uga.cs.countryquiz;


import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class QuizFragment extends Fragment {

    private Quiz quiz;

    private int questionsAnswered;

    public QuizFragment() {
        quiz = new Quiz(getContext());
    }
        public static QuizFragment newInstance(int questionNum) {
            QuizFragment fragment = new QuizFragment();
            Bundle args = new Bundle();
            args.putInt( "questionNum", questionNum );
            fragment.setArguments( args );
            return fragment;
        }

        @Override
        public void onCreate( Bundle savedInstanceState ) {
            super.onCreate( savedInstanceState );
            if( getArguments() != null ) {
               questionsAnswered = getArguments().getInt( "questionNum" );
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState ) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.quiz_fragment, container, false );
        }

        @Override
        public void onViewCreated( @NonNull View view, Bundle savedInstanceState ) {
            //public void onActivityCreated(Bundle savedInstanceState) {
            super.onViewCreated( view, savedInstanceState );

            TextView titleView = view.findViewById( R.id.textView2 );
            TextView questionView = view.findViewById( R.id.textView3 );

            int questionNum = quiz.getQuestionsAnswered() + 1;
            titleView.setText("Question " + questionNum + ":");
            questionView.setText("In which continent is " + quiz.getQuestions()[quiz.getQuestionsAnswered()].getCountry().getName() + " located?");
        }
    }
