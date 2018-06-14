package main.java.editor;

import main.java.editor.Answer;
import main.java.editor.Question;

public class UserInputQ extends Question {
    public UserInputQ(String unparsedQuestion) {
        super(unparsedQuestion);
        correctAnswer = new Answer(questionJson.optString("answer", "error"));

    };
    @Override
    public boolean checkAnswer(Answer userAnswer) {
        return userAnswer.getText().equals(correctAnswer.getText());
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    private String givenAnswer;
    private Answer correctAnswer;
}
