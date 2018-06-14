package main.java.editor;

import main.java.editor.MultipleChoiceA;
import main.java.editor.Question;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MultipleChoiceQ extends Question {
    private ArrayList<MultipleChoiceA> answers = new ArrayList<>();

    public MultipleChoiceQ(JSONObject jsonQuestion) {
        super(jsonQuestion);
        JSONArray answersJson = questionJson.optJSONArray("answers");
        for (int i = 0; i < answersJson.length(); i++) {
            JSONObject oneAnswer = answersJson.optJSONObject(i);
            answers.add(new MultipleChoiceA(
                oneAnswer.optString("answerText", "error"),
                oneAnswer.optBoolean("correct", false)
            ));
        }
    }
    @Override
    public boolean checkAnswer(Answer userAnswer) {
        return ((MultipleChoiceA)userAnswer).isCorrect();
    }

    @Override
    public JSONObject save() {
        return null;
    }
}
