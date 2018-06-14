package main.java.editor;

import main.java.editor.Answer;
import main.java.editor.Question;
import org.json.JSONObject;

public class UserInputQ extends Question {
    public UserInputQ(JSONObject jsonQuestion) {
        super(jsonQuestion);
        correctAnswer = new Answer(questionJson.optString("answerText", "error"));

    };
    @Override
    public boolean checkAnswer(Answer userAnswer) {
        givenAnswer = userAnswer;
        return userAnswer.getText().equals(correctAnswer.getText());
    }

    private Answer givenAnswer;
    private Answer correctAnswer;
}
