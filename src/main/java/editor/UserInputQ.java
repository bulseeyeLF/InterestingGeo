package main.java.editor;

import main.java.editor.Answer;
import main.java.editor.Question;
import org.json.JSONException;
import org.json.JSONObject;

public class UserInputQ extends Question {
    public UserInputQ(JSONObject jsonQuestion) {
        super(jsonQuestion);
        correctAnswer = new Answer(questionJson.optString("text", "error"));

    }

    @Override
    public boolean checkAnswer(Answer userAnswer) {
        givenAnswer = userAnswer;
        return userAnswer.getText().equals(correctAnswer.getText());
    }

    @Override
    public JSONObject save() {
        try {
            return new JSONObject().put("questionText", getQuestionText())
                    .put("timer", getTimer())
                    .put("text", getCorrectAnswer().getText())
                    .put("type", getType());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
