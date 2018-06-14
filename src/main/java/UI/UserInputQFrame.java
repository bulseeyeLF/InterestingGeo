package main.java.UI;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import main.java.editor.Answer;
import main.java.editor.Question;
import main.java.editor.UserInputQ;
import org.json.JSONObject;


public class UserInputQFrame extends QuestionFrame {
    private Answer answer;

    @Override
    public QuestionFrame setQuestion(Question question) {
        answer = ((UserInputQ) question).getAnswer();
        this.question = question;
        init();
        return this;
    }

    public UserInputQFrame() {
        super();

    }

    @Override
    public void init() {
        this.setBottom(answer.getAnswerTextField());
    }
}
