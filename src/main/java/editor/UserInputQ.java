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
        givenAnswer = userAnswer;
        return userAnswer.getText().equals(correctAnswer.getText());
    }

    private Answer givenAnswer;
    private Answer correctAnswer;
}
