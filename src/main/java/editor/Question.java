package main.java.editor;

import com.sun.istack.internal.NotNull;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.*;

import java.io.File;

public abstract class Question {

    public Long getTimer() {
        return timer;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Question(JSONObject jsonQuestion) {
        questionTextArea = new TextArea(
            jsonQuestion.optString("questionText", "")
        );
        timer = jsonQuestion.optLong("timer", 0);
        type = jsonQuestion.optInt("type", 0);
    }

    public TextArea getQuestionTextArea() {
        return questionTextArea;
    }

    public void setQuestionTextArea(TextArea questionTextArea) {
        this.questionTextArea = questionTextArea;
    }

    protected Long timer;
    protected int type;
    protected TextArea questionTextArea;

    public abstract JSONObject save();
}
