package main.java.UI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import main.java.editor.Question;
import main.java.utils.UtilsCommon;

import java.util.ArrayList;

public class QuestionEditPane extends BorderPane {
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    private ArrayList<Question> questions;
    private ArrayList<Button> questionButtons;
    private FlowPane buttonsPane;
    private TextArea questionArea;
    private Question selectedQuestion;
    private MultipleChoiceQFrame MultipleChoiceAdapter;
    private UserInputQFrame UserInputAdapter;

    public void setSelectedQuestion(Question question) {
        this.selectedQuestion = question;
        this.questionArea.setText(question.getQuestionText());
        this.frame = (question.getType() == 0) ? UserInputAdapter : MultipleChoiceAdapter;
        this.setBottom(this.frame.setQuestion(question));
    }
    public QuestionFrame getAdapter() {
        return frame;
    }

    private QuestionFrame frame;

    public void setFrame(QuestionFrame frame) {
        this.frame = frame;
    }

    public QuestionEditPane(ArrayList<Question> questions) {
        super();
        UserInputAdapter = new UserInputQFrame();
        MultipleChoiceAdapter = new MultipleChoiceQFrame();
        questionArea = new TextArea("");
        buttonsPane = new FlowPane();
        this.questions = questions;
        questionButtons = new ArrayList<>();
        for (int i = 0; i < questions.size(); i ++) {
            if (i == 0 ) {
                setSelectedQuestion(questions.get(i));
            }
            questionButtons.add(new Button(Integer.toString(i)));
            questionButtons.get(i).setPrefHeight(50);
            questionButtons.get(i).setPrefWidth(50);
            questionButtons.get(i).setOnMouseClicked(event -> setSelectedQuestion(questions.get(questionButtons.indexOf(event.getSource()))));
            buttonsPane.getChildren().add(questionButtons.get(i));
        }
        this.setTop(buttonsPane);
        UtilsCommon utils = new UtilsCommon();
        questionArea.setPrefHeight(utils.getScreenHeight()/3);
        buttonsPane.setPrefHeight(utils.getScreenHeight()/3);
        this.setCenter(questionArea);
        this.buttonsPane.setAlignment(Pos.CENTER);
        setBottom(frame);
    }


}
