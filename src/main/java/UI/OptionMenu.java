package main.java.UI;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.util.HashMap;

public class OptionMenu extends FlowPane {
    public OptionMenu() {
        init();
    }

    public OptionMenu(Orientation orientation) {
        super(orientation);
    }

    public OptionMenu(double hgap, double vgap) {
        super(hgap, vgap);
    }

    public OptionMenu(Orientation orientation, double hgap, double vgap) {
        super(orientation, hgap, vgap);
    }

    public OptionMenu(Node... children) {
        super(children);
    }

    public OptionMenu(Orientation orientation, Node... children) {
        super(orientation, children);
    }

    public OptionMenu(double hgap, double vgap, Node... children) {
        super(hgap, vgap, children);
    }

    public OptionMenu(Orientation orientation, double hgap, double vgap, Node... children) {
        super(orientation, hgap, vgap, children);
    }

    private String[] OPTION_TEXT = { "New Map", "Edit Map", "Exit"};
    private String[] HINT_TEXT = { "Create a (N)ew map", "(E)dit existing map", "E(X)it" };
    private void init() {

        this.setPadding(new Insets(5, 0, 5, 0));
        this.setVgap(4);
        this.setHgap(4);
        this.setPrefWrapLength(170); // preferred width allows for two columns
        this.setStyle("-fx-background-color: DAE6F3;");

        OptionButton buttons[] = new OptionButton[3];
        for (int i = 0; i < 3; i++) {
            System.out.println(i);
            buttons[i] = new OptionButton(
                OPTION_TEXT[i],
                new ImageView(
                    new Image(
                        OptionMenu.class.getResourceAsStream("resources/option_button_" + i + ".png")
                    )
                )
            );
            buttons[i].setTooltip(new Tooltip(HINT_TEXT[i]));
            buttons[i].setBorder(null);
            this.getChildren().add(buttons[i]);
        }
    }
}
