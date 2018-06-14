package main.java.editor;

public class Answer {
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    protected String text;
    public Answer(String text) {
        this.text = text;
    }
}
