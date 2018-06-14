package main.java.editor;

public class UserInputA extends Answer {

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    private String correctAnswer;
    public UserInputA(String text, String correctAnswer) {
        super(text);
        this.correctAnswer = correctAnswer;
    }

}
