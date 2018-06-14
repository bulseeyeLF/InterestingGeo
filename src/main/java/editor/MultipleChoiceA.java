package main.java.editor;

import javafx.scene.control.RadioButton;

public class MultipleChoiceA extends Answer {
    public MultipleChoiceA(String text, boolean correct) {
        super(text);
        button = new RadioButton();
        button.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if (isNowSelected) {
                setCorrect(true);
                answerTextField.setStyle("-fx-text-inner-color: green;");
            } else {
                setCorrect(false);
                answerTextField.setStyle("-fx-text-inner-color: red;");
            }
        });
        this.correct = correct;
    }


    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public RadioButton getButton() {
        return button;
    }

    private RadioButton button;
    private boolean correct;

}
