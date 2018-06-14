package main.java.editor;

import com.sun.istack.internal.NotNull;
import org.json.*;

public abstract class Question {
    protected String questionText;
    protected Long timer;
    protected JSONObject questionJson;

    public Question(JSONObject jsonQuestion) {
            questionJson = jsonQuestion;
            questionText = questionJson.optString("questionText", "Error Loading the Question");
            timer = questionJson.optLong("timer", 0);

    }

    public abstract boolean checkAnswer(Answer userAnswer);
}
