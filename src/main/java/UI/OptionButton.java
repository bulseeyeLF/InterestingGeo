package main.java.UI;

import com.sun.istack.internal.Nullable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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

    public OptionButton(String text, Node graphic, Selection action, @Nullable KeyCode trigger) {
        super(text, graphic);
        this.action = action;
        this.triggerKey = trigger;
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

    public void doAction() {
        action.execute();
    }

    void setAction(Selection action) {
        this.action = action;
    }

    public Selection getAction() {
        return this.action;
    }

    private Selection action;

    public KeyCode getTriggerKey() {
        return triggerKey;
    }

    private KeyCode triggerKey;

}
