package main.java.UI;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import main.java.editor.MultipleChoiceQ;
import main.java.editor.Question;
import org.json.JSONObject;


public class MultipleChoiceQFrame extends QuestionFrame {

    private TextField[] answerFields;
    private FlowPane answerTextFieldsPane;
    private BorderPane answersChooserPane;
    private ToggleGroup validityButtons;
    private RadioButton[] radioButtons;
    private FlowPane validityButtonsPane;

    @Override
    public QuestionFrame setQuestion(Question question) {
        this.question = question;
        init();
        return this;
    }

    public MultipleChoiceQFrame() {
        super();
    }

    @Override
    public void init() {
        this.inputField.setText(question.getQuestionText());
        answersChooserPane = new BorderPane();
        answerTextFieldsPane = new FlowPane();
        validityButtonsPane = new FlowPane();
        answerFields = new TextField[4];
        radioButtons = new RadioButton[4];
        validityButtons = new ToggleGroup();
        for (int i = 0; i < answerFields.length; i++) {
            radioButtons[i] = new RadioButton();
            radioButtons[i].setToggleGroup(validityButtons);
            if (i == 1) {
                radioButtons[i].setSelected(true);
            }
            validityButtonsPane.getChildren().add(radioButtons[i]);
            answerFields[i] = new TextField();
            answerTextFieldsPane.getChildren().add(answerFields[i]);
        }
        answerTextFieldsPane.setPrefHeight(300);
        answerTextFieldsPane.setPrefWidth(50);
        answersChooserPane.setLeft(answerTextFieldsPane);
        validityButtonsPane.setPrefHeight(300);
        validityButtonsPane.setPrefWidth(50);
        answersChooserPane.setRight(validityButtonsPane);
        this.setBottom(answersChooserPane);
    }
}
