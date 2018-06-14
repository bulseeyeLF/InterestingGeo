package main.java.UI;

import main.java.editor.Question;
import main.java.utils.UtilsCommon;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

import java.util.ArrayList;


public class GameFrame extends BorderPane {
    public ArrayList<Question> questions;
    public void setBackground(Image background) {
        this.background = background;
        ImageView map = new ImageView(this.background);
        this.setCenter(map);
        map.setFitHeight(HEIGHT);
        map.setFitWidth(WIDTH);
    }

    public GameFrame() {
    }

    public GameFrame(Node center) {
        super(center);
    }

    public GameFrame(Node center, Node top, Node right, Node bottom, Node left) {
        super(center, top, right, bottom, left);
    }

    private Image background;

    private UtilsCommon utils = new UtilsCommon();
    private double WIDTH = utils.getScreenWidth() - utils.getScreenWidth()/5;
    private double HEIGHT = utils.getScreenHeight();
}
