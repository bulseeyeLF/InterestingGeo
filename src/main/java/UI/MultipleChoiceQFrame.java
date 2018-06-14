package main.java.UI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    private ArrayList<MultipleChoiceA> answers;
    private FlowPane answerTextFieldsPane;
    private BorderPane answersChooserPane;
    private ToggleGroup validityButtons;
    private FlowPane validityButtonsPane;

    @Override
    public QuestionFrame setQuestion(Question question) {
        answers = ((MultipleChoiceQ)question).getAnswers();
        init();
        return this;
    }

    public MultipleChoiceQFrame() {
        super();
    }

    @Override
    public void init() {
        answersChooserPane = new BorderPane();
        answerTextFieldsPane = new FlowPane();
        validityButtonsPane = new FlowPane();
        validityButtons = new ToggleGroup();
        for (MultipleChoiceA answer : answers) {
            answer.getButton().setToggleGroup(validityButtons);
            if (answer.isCorrect()) {
                answer.getButton().setSelected(true);
                (answer.getAnswerTextField()).setStyle("-fx-text-inner-color: green;");
            } else {
                answer.getAnswerTextField().setStyle("-fx-text-inner-color: red;");
            }
            answerTextFieldsPane.getChildren().addAll(answer.getAnswerTextField(), answer.getButton());
        }
        answersChooserPane.setLeft(answerTextFieldsPane);
        answersChooserPane.setRight(validityButtonsPane);
        this.setBottom(answersChooserPane);
    }
}
