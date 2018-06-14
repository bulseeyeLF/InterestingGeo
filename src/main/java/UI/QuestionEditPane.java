package main.java.UI;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import main.java.editor.Question;

import java.util.ArrayList;

public class QuestionEditPane extends BorderPane {
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    private ArrayList<Question> questions;
    private Button[] questionButtons;
    private FlowPane buttonsPane;

    public QuestionFrame getAdapter() {
        return frame;
    }

    private QuestionFrame frame;

    public void setFrame(QuestionFrame frame) {
        this.frame = frame;
        this.setBottom(frame);
    }

    public QuestionEditPane(ArrayList<Question> questions) {
        super();
        buttonsPane = new FlowPane();
        this.questions = questions;
        questionButtons = new Button[questions.size()];
        for (int i = 0; i < questions.size(); i ++) {
            questionButtons[i] = new Button(Integer.toString(i));
            buttonsPane.getChildren().add(questionButtons[i]);
        }
        buttonsPane.setPrefWidth(200);
        buttonsPane.setPrefHeight(200);
        this.setCenter(buttonsPane);
    }


}
