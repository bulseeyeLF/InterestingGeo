package main.java.editor;

import main.java.editor.Answer;
import main.java.editor.Question;
import org.json.JSONException;
import org.json.JSONObject;

public class UserInputQ extends Question {
    public Answer getAnswer() {
        return answer;
    }

    private Answer answer;

    public UserInputQ(JSONObject jsonQuestion) {
        super(jsonQuestion);
        answer = new Answer(jsonQuestion.optString("text", "error"));
    }

    @Override
    public JSONObject save() {
        try {
            return new JSONObject().put("questionText", this.getQuestionTextArea().getText())
                    .put("timer", getTimer())
                    .put("text", answer.getAnswerTextField().getText())
                    .put("type", getType());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
