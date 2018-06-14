package main.java.UI;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import main.java.editor.Answer;
import main.java.editor.MultipleChoiceA;
import main.java.editor.MultipleChoiceQ;
import main.java.editor.Question;
import org.json.JSONObject;

import java.util.ArrayList;


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
        ArrayList<MultipleChoiceA> answers = ((MultipleChoiceQ) question).getAnswers();
        answersChooserPane = new BorderPane();
        answerTextFieldsPane = new FlowPane();
        validityButtonsPane = new FlowPane();
        answerFields = new TextField[4];
        radioButtons = new RadioButton[4];
        validityButtons = new ToggleGroup();
        for (int i = 0; i < answerFields.length; i++) {
            radioButtons[i] = new RadioButton();
            //radioButtons[i].sele
            radioButtons[i].setToggleGroup(validityButtons);
            answerFields[i] = new TextField();
            answerFields[i].setText(answers.get(i).getText());
            if (answers.get(i).isCorrect()) {
                radioButtons[i].setSelected(true);
                answerFields[i].setStyle("-fx-text-inner-color: green;");
            } else {
                answerFields[i].setStyle("-fx-text-inner-color: red;");
            }
            answerTextFieldsPane.getChildren().addAll(answerFields[i], radioButtons[i]);
        }
        answersChooserPane.setLeft(answerTextFieldsPane);
        answersChooserPane.setRight(validityButtonsPane);
        this.setBottom(answersChooserPane);
    }
}
