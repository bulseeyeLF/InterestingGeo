package main.java.editor;

import com.sun.javafx.sg.prism.NGNode;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

import java.util.concurrent.CancellationException;

public class GameApp extends Application {

    @Override
    public void start(Stage editorStage) throws Exception {
        Image backgroundImage = new Image("main/resources/test.png");
        ImagePattern backgroundPattern = new ImagePattern(backgroundImage, 0, 0, 1, 1, true);
        Rectangle background = new Rectangle(500, 500, backgroundPattern);
        Group root = new Group();
        root.getChildren().addAll(background);
        Scene scene = new Scene(root, 600, 600);
        editorStage.setTitle("MAP EDITOR");
        editorStage.setScene(scene);
        editorStage.show();
    }

    public static void main(String[] argv) {
        launch(argv);
    }
}
