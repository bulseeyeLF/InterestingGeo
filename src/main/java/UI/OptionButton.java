package main.java.UI;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Text;

public class OptionButton extends Button {
    public OptionButton() {
    }

    public OptionButton(String text) {
        super(text);
    }

    OptionButton(String text, Node graphic) {
        super(text, graphic);
        //this.setHeight(70);
        //this.setWidth(200);
        //this.setPrefWidth(this.getWidth());
        //this.setPrefHeight(this.getHeight());
        this.setPrefHeight(50);
        this.setPrefWidth(200);
    }

    OptionButton(Image backgroundImage) {
        super();
        this.setGraphic(new ImageView(backgroundImage));

        //this.setMaxWidth(100);
        //this.setPrefWidth(100);
        //this.setMinWidth(100);
    }
}
