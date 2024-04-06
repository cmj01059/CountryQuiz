package edu.uga.cs.countryquiz;
import java.util.Random;
import android.content.Context;
public class Quiz {
    private Question[] questions;
    private int score;
    private int questionsAnswered;
    public Quiz(Context context) {
        score = 0;
        questionsAnswered = 0;
        questions = new Question[6];
        Random gen = new Random();
        questions[0] = new Question(context, gen.nextInt());
        for (int count = 1; count < 6; count++) {
            boolean repeat;
            do {
                questions[count] = new Question(context, gen.nextInt());
                repeat = false;
                int newCount = count - 1;
                while (newCount >= 0) {
                    if (questions[count].getCountry().equals(questions[newCount].getCountry())) {
                        repeat = true;
                        break;
                    }
                    newCount--;
                }
            } while (repeat);
        }
    }

    public Question[] getQuestions() {
        return questions;
    }
    public double getScore() {
        return score;
    }

    public int getQuestionsAnswered() {
        return questionsAnswered;
    }

    public void setAnswer(String answer) {
        if (answer.equals(questions[questionsAnswered].getCountry().getContinent())) {
            score++;
        }
        questionsAnswered++;
    }
}
