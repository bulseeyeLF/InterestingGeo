package main.java.UI;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import main.java.editor.Question;

import java.awt.*;
import java.util.ArrayList;

public class QuestionEditPane extends BorderPane {
    private ArrayList<Question> questions;
    private Button[] questionButtons;
    private FlowPane buttonsPane;
    public QuestionEditPane(ArrayList<Question> questions) {
        super();
        buttonsPane = new FlowPane();
        this.questions = questions;

    }

}
