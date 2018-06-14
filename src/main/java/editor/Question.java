package main.java.editor;

import com.sun.istack.internal.NotNull;
import org.json.*;

import java.io.File;

public abstract class Question {

    public String getQuestionText() {
        return questionText;
    }

    protected String questionText;

    public Long getTimer() {
        return timer;
    }

    protected Long timer;
    protected JSONObject questionJson;
    protected Answer correctAnswer;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    protected int type;

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    protected Answer givenAnswer;


    public Answer getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(Answer givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    public Question(JSONObject jsonQuestion) {
            questionJson = jsonQuestion;
            questionText = questionJson.optString("questionText", "Error Loading the Question");
            timer = questionJson.optLong("timer", 0);
            type = questionJson.optInt("type", 0);
    }

    public abstract boolean checkAnswer(Answer userAnswer);
    public abstract JSONObject save();
}
