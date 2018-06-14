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

    public QuestionFrame() {
    }

    public String getInputFieldString() {
        return this.inputField.getText().toString();
    }

    public abstract void init();
}
