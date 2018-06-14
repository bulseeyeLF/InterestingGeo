package main.java.UI;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import main.java.editor.Question;

public abstract class QuestionFrame extends BorderPane {
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    protected Question question;
    protected TextArea inputField;

    public QuestionFrame(Question question) {
        this.inputField = new TextArea("Enter your answer here");
        this.inputField.setPrefHeight(50);
        this.inputField.setPrefWidth(500);
        this.question = question;
        this.setCenter(this.inputField);
    }

    public String getInputFieldString() {
        return this.inputField.getText().toString();
    }
    public abstract void save();

}
