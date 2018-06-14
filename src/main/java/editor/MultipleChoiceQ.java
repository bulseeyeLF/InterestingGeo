package main.java.editor;

import main.java.editor.MultipleChoiceA;
import main.java.editor.Question;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MultipleChoiceQ extends Question {
    public ArrayList<MultipleChoiceA> getAnswers() {
        return answers;
    }

    private ArrayList<MultipleChoiceA> answers = new ArrayList<>();

    public MultipleChoiceQ(JSONObject jsonQuestion) {
        super(jsonQuestion);
        JSONArray answersJson = jsonQuestion.optJSONArray("answers");
        for (int i = 0; i < answersJson.length(); i++) {
            JSONObject oneAnswer = answersJson.optJSONObject(i);
            answers.add(new MultipleChoiceA(
                oneAnswer.optString("text", "error"),
                oneAnswer.optBoolean("correct", false)
            ));
        }
    }

    @Override
    public JSONObject save() {
        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            answers.forEach(a->{
                try {
                    jsonArray.put(new JSONObject().put("text", a.getAnswerTextField().getText()).put("correct", a.isCorrect()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
            jsonObject.put("questionText", getQuestionTextArea().getText())
                    .put("timer", getTimer())
                    .put("type", getType());
            jsonObject.put("answers",jsonArray);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
