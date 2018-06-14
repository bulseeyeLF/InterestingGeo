package main.java.editor;

import javafx.scene.control.TextField;

public class Answer {

    public TextField getAnswerTextField() {
        return answerTextField;
    }

    protected TextField answerTextField;
    public Answer(String text) {
        this.answerTextField = new TextField(text);
    }
}
