package main.java.UI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import main.java.editor.MultipleChoiceQ;
import main.java.editor.Question;
import main.java.editor.UserInputQ;
import main.java.utils.UtilsCommon;

import java.util.ArrayList;

public class QuestionEditPane extends BorderPane {
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    private ArrayList<Question> questions;
    private ArrayList<Button> questionButtons;
    private Question selectedQuestion;
    private MultipleChoiceQFrame multipleChoiceAdapter;
    private UserInputQFrame userInputAdapter;
    private FlowPane buttonsPane;
    public QuestionFrame getAdapter() {
        return frame;
    }

    private QuestionFrame frame;

    public void addQuestion(Question question) {
        questions.add(question);
        Button newButton = new Button(Integer.toString(questionButtons.size()));
        newButton.requestFocus();
        newButton.setPrefHeight(50);
        newButton.setPrefWidth(50);
        newButton.setOnMouseClicked(event -> setSelectedQuestion(questions.get(questionButtons.indexOf(event.getSource()))));
        questionButtons.add(newButton);
        buttonsPane.getChildren().add(newButton);
        setSelectedQuestion(question);
    }

    public void deleteQuestion(Question question) {
        int index = questions.indexOf(question);
        Button oldButton = questionButtons.get(index);
        questionButtons.remove(oldButton);
        buttonsPane.getChildren().remove(oldButton);
        if (!questionButtons.isEmpty()) {
            questionButtons.get(0).requestFocus();
            setSelectedQuestion(questions.get(0));
        }
        questions.remove(question);
    }

    public Question getSelectedQuestion() {
        return this.selectedQuestion;
    }

    public void setSelectedQuestion(Question selectedQuestion) {
        this.selectedQuestion = selectedQuestion;
        this.selectedQuestion.setQuestionTextArea(selectedQuestion.getQuestionTextArea());
        this.setCenter(this.selectedQuestion.getQuestionTextArea());
        frame = (selectedQuestion.getType() == 0) ? userInputAdapter : multipleChoiceAdapter;
        frame.setQuestion(selectedQuestion);
        this.setBottom(frame);
    }

    public void selectQuestion(int index) {
        setSelectedQuestion(questions.get(index));
    }
    public QuestionEditPane(ArrayList<Question> questions) {
        super();
        if (questions == null) {
            questions = new ArrayList<>();
        }
        UtilsCommon utils = new UtilsCommon();
        userInputAdapter = new UserInputQFrame();
        multipleChoiceAdapter = new MultipleChoiceQFrame();
        buttonsPane = new FlowPane();
        this.questions = questions;
        questionButtons = new ArrayList<>();
        for (int i = 0; i < questions.size(); i ++) {
            if (i == 0 ) {
                setSelectedQuestion(questions.get(i));
                selectedQuestion.getQuestionTextArea().setPrefHeight(utils.getScreenHeight()/3);
                this.setCenter(selectedQuestion.getQuestionTextArea());
            }
            questionButtons.add(new Button(Integer.toString(i)));
            questionButtons.get(i).setPrefHeight(50);
            questionButtons.get(i).setPrefWidth(50);
            questionButtons.get(i).setOnMouseClicked(event -> {
                if (questionButtons != null) {
                    int index;
                    index = questionButtons.indexOf(event.getSource());
                    selectQuestion(index);
                }
            });
            buttonsPane.getChildren().add(questionButtons.get(i));
        }
        this.setTop(buttonsPane);
        buttonsPane.setPrefHeight(utils.getScreenHeight()/3);
        buttonsPane.setAlignment(Pos.CENTER);
        setBottom(frame);
    }


}
