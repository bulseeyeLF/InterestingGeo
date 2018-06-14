package main.java.editor;

public class MultipleChoiceA extends Answer {
    public MultipleChoiceA(String text, boolean correct) {
        super(text);
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    private String text;
    private boolean correct;

}
