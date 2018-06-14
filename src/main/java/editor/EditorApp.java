package main.java.editor;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.WindowEvent;
import main.java.UI.GameMap;
import main.java.UI.OptionButton;
import main.java.UI.OptionMenu;

import javax.swing.text.html.HTMLDocument;
import java.util.HashMap;

public class EditorApp extends Application {

    interface Selection {
        void execute();
    }

    private void newMap() {
        //TODO implement creating of a new map
    }

    private void close() {
        Platform.exit();
        System.exit(0);
    }

    private void editMap() {
        //TODO implement editing of a map
    }

    private void doAction(KeyCode pressedKey) {
        ((Selection) this.possibleActions.get(pressedKey)).execute();
    }

    private void initShortcuts(Stage parent) {
        this.possibleActions.put(KeyCode.N, (Selection) this::newMap);
        this.possibleActions.put(KeyCode.E, (Selection) this::editMap);
        this.possibleActions.put(KeyCode.X, (Selection) this::close);
        parent.addEventHandler(KeyEvent.KEY_PRESSED, event -> doAction(event.getCode()));
    }

    @Override
    public void start(Stage editorStage) {
        initShortcuts(editorStage);
        OptionMenu mainMenu = new OptionMenu();
        Group root = new Group();
        root.getChildren().addAll(mainMenu);
        Scene scene = new Scene(root);
        editorStage.setFullScreen(true);
        editorStage.setOnCloseRequest(event -> close());
        editorStage.setTitle("MAP EDITOR");
        editorStage.setScene(scene);
        editorStage.show();
    }

    public static void main(String[] argv) {
        launch(argv);
    }

    private HashMap<KeyCode, Object> possibleActions = new HashMap<>(3);

}
