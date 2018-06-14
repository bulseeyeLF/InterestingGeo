package main.java.UI;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import main.java.editor.MultipleChoiceQ;


public class MultipleChoiceQFrame extends QuestionFrame {

    private TextField[] answerFields;
    private FlowPane answerTextFieldsPane;
    private BorderPane answersChooserPane;
    private ToggleGroup validityButtons;
    private RadioButton[] radioButtons;
    private FlowPane validityButtonsPane;

    public MultipleChoiceQFrame(MultipleChoiceQ question) {
        super(question);
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
        answerTextFieldsPane.setPrefHeight(400);
        answerTextFieldsPane.setPrefWidth(400);
        answersChooserPane.setLeft(answerTextFieldsPane);
        validityButtonsPane.setPrefHeight(400);
        validityButtonsPane.setPrefWidth(200);
        answersChooserPane.setRight(validityButtonsPane);
        this.setBottom(answersChooserPane);
    }

    @Override
    public void save() {

    }
}
