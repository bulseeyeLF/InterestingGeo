package main.java.UI;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import main.java.editor.Question;
import main.java.editor.UserInputQ;
import org.json.JSONObject;


public class UserInputQFrame extends QuestionFrame {

    private TextField answerTextField;

    public TextField getAnswerTextField() {
        return answerTextField;
    }

    public void setAnswerTextField(TextField answerTextField) {
        this.answerTextField = answerTextField;
    }

    @Override
    public QuestionFrame setQuestion(Question question) {
        this.question = question;
        init();
        return this;
    }

    public UserInputQFrame() {
        super();

    }

    @Override
    public void init() {
        this.inputField.setText(this.question.getQuestionText());
        answerTextField = new TextField("");
        answerTextField.setPrefHeight(400);
        answerTextField.setPrefWidth(400);
        this.setBottom(answerTextField);
    }
}
