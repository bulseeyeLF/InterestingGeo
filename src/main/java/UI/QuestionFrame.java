package main.java.UI;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import main.java.editor.Question;
import main.java.utils.UtilsCommon;
import org.json.JSONObject;

public abstract class QuestionFrame extends BorderPane {
    public Question getQuestion() {
        return question;
    }

    public abstract QuestionFrame setQuestion(Question question);

    protected Question question;
    protected TextArea inputField;

    public QuestionFrame(Question question) {
        this.inputField = new TextArea(question.getQuestionText());
        this.inputField.setPrefHeight(50);
        this.inputField.setPrefWidth(200);
        this.question = question;
        this.setCenter(this.inputField);
    }

    public QuestionFrame() {
        UtilsCommon utils = new UtilsCommon();
        this.inputField = new TextArea("");
        this.inputField.setPrefWidth(utils.getScreenWidth()/4);
        this.inputField.setPrefHeight(utils.getScreenHeight()/4);
        this.setCenter(this.inputField);
    }

    public String getInputFieldString() {
        return this.inputField.getText().toString();
    }

    public abstract void init();
}
