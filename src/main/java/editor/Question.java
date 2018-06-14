package main.java.editor;

import com.sun.istack.internal.NotNull;
import org.json.*;

public abstract class Question {
    protected String questionText;
    protected Long timer;
    protected JSONObject questionJson;

    public Question(String unparsedQuestion) {
        try {
            questionJson = new JSONObject(unparsedQuestion);
            questionText = questionJson.optString("questionText", "Error Loading the Question");
            timer = questionJson.optLong("timer", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public abstract boolean checkAnswer(Answer userAnswer);
}
