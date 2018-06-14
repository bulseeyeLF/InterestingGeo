package main.java.editor;

import com.sun.istack.internal.Nullable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.WindowEvent;
import main.java.UI.*;
import main.java.utils.UtilsCommon;

import javax.swing.text.html.HTMLDocument;
import java.io.File;
import java.util.HashMap;

public class EditorApp extends Application {

    public void addQuestions() {
        addRoot.getChildren().clear();
        addRoot.getChildren().add(initAddScreen());
        mainScene.setRoot(addRoot);
        currentMenu = addMenu;
        currentRoot =
    }
    public void newMap() {
        editRoot.getChildren().clear();
        editRoot.getChildren().add(initEditScreen(null));
        mainScene.setRoot(editRoot);
        currentMenu = editMenu;
        currentRoot = editRoot;
    }

    public void importBackground() {

    }
    public void addQuestion() {

    }
    public void deleteQuestion() {

    }
    public void saveAndBack() {

    }
    public void backToMain() {
        mainScene.setRoot(mainRoot);
        currentMenu = mainMenu;
        currentRoot = mainRoot;
    }

    public void close() {
        Platform.exit();
        System.exit(0);
    }

    public void editMap() {
        String path = "test.txt";
        //TODO: implement choosing of file
        editRoot.getChildren().clear();
        editRoot.getChildren().add(initEditScreen(new File(path)));
        mainScene.setRoot(editRoot);
        currentMenu = editMenu;
    }

    private void initShortcuts(Stage parent) {
        parent.addEventHandler(KeyEvent.KEY_PRESSED, event -> currentMenu.pressButon(event.getCode()));
    }

    private void initScreen(Stage parent) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        parent.setX(primaryScreenBounds.getMinX());
        parent.setY(primaryScreenBounds.getMinY());
        parent.setWidth(primaryScreenBounds.getWidth());
        parent.setHeight(primaryScreenBounds.getHeight());
        parent.setOnCloseRequest(event -> close());
        parent.setTitle("Editor");
    }

    private BorderPane initMainScreen() {
        BorderPane mainScreen = new BorderPane();
        mainScreen.setCenter(this.mainMenu);
        this.mainMenu.setAlignment(Pos.CENTER);
        return mainScreen;
    }

    private BorderPane initEditScreen(@Nullable File input) {
        GameFrame editScreen = new GameFrame();
        if (input != null && input.canRead()) {
            //TODO: implement loading the file of loaded file
        } else {
            editScreen.setBackground(defaultMap);
            this.editMenu.setPrefWidth(utils.getScreenWidth()/5);
            this.editMenu.setPrefHeight(utils.getScreenHeight());
            editScreen.setRight(this.editMenu);
            this.editMenu.setAlignment(Pos.CENTER);
        }
        return editScreen;
    }

    private BorderPane initAddScreen() {
        BorderPane addScreen = new MultipleChoiceQFrame(new MultipleChoiceQ(""));
        addScreen.setRight(this.addMenu);
        this.addMenu.setAlignment(Pos.CENTER);
        return addScreen;
    }
    @Override
    public void start(Stage editorStage) {
        BorderPane mainScreen = initMainScreen();
        initShortcuts(editorStage);
        mainRoot = new Group();
        editRoot = new Group();
        addRoot = new Group();
        initScreen(editorStage);
        mainRoot.getChildren().addAll(mainScreen);
        mainScene = new Scene(mainRoot);
        editorStage.setResizable(false);
        editorStage.show();
        mainScreen.setPrefHeight(editorStage.getHeight());
        mainScreen.setPrefWidth(editorStage.getWidth());
        currentMenu = mainMenu;
        currentRoot = mainRoot;
        editorStage.setScene(mainScene);
    }

    public static void main(String[] argv) {
        launch(argv);
    }

    private Group mainRoot;
    private Group editRoot;
    private Group addRoot;

    private String[] MAIN_MENU_TEXT = {
        "New Map",
        "Edit Map",
        "Exit"
    };
    private String[] MAIN_MENU_HINT_TEXT = {
        "Create a (N)ew map",
        "(E)dit existing map",
        "E(X)it"
    };
    private KeyCode[] MAIN__MENU_TRIGGERS = {
        KeyCode.N,
        KeyCode.E,
        KeyCode.X
    };
    private Selection[] MAIN_MENU_ACTIONS = {
        this::newMap,
        this::editMap,
        this::close
    };
    private String[] EDIT_MENU_TEXT = {
        "Add Question",
        "Set Global Timer",
        "Import map",
        "Back"
    };
    private String[] EDIT_MENU_HINT_TEXT = {
        "(A)dd more one question",
        "Set the (T)imer",
        "(I)mport new map file",
        "Go (B)ack"
    };
    private KeyCode[] EDIT_MENU_TRIGGERS = {
        KeyCode.A,
        KeyCode.T,
        KeyCode.I,
        KeyCode.B
    };
    private Selection[] EDIT_MENU_ACTIONS = {
        this::addQuestions,
        ()->{},
        ()->{},
        this::backToMain
    };
    private String[] ADD_MENU_TEXT = {
        "Add",
        "Delete",
        "Save"
    };
    private String[] ADD_MENU_HINT_TEXT = {
        "(A)dd another question",
        "(D)elete current question",
        "(S)ave and back"
    };
    private Selection[] ADD_MENU_ACTIONS = {
        this::addQuestion,
        this::deleteQuestion,
        this::saveAndBack
    };
    private KeyCode[] ADD_MENU_TRIGGERS = {
        KeyCode.A,
        KeyCode.D,
        KeyCode.S
    };
    private OptionMenu mainMenu = new OptionMenu(
        "main",
        new OptionButton[MAIN_MENU_TEXT.length],
        MAIN_MENU_TEXT,
        MAIN_MENU_HINT_TEXT,
        MAIN_MENU_ACTIONS,
        MAIN__MENU_TRIGGERS
    );
    private OptionMenu editMenu = new OptionMenu(
        "edit",
        new OptionButton[EDIT_MENU_TEXT.length],
        EDIT_MENU_TEXT,
        EDIT_MENU_HINT_TEXT,
        EDIT_MENU_ACTIONS,
        EDIT_MENU_TRIGGERS
    );
    private OptionMenu addMenu = new OptionMenu(
        "add",
        new OptionButton[ADD_MENU_TEXT.length],
        ADD_MENU_TEXT,
        ADD_MENU_HINT_TEXT,
        ADD_MENU_ACTIONS,
        ADD_MENU_TRIGGERS
    );
    private UtilsCommon utils = new UtilsCommon();
    private Group currentRoot;
    private OptionMenu currentMenu;
    private Scene mainScene;
    private Image defaultMap = new Image(GameFrame.class.getResourceAsStream("resources/maps/default.png"));
    private FileChooser fileChooser = new FileChooser();
    private QuestionFrame questionAdapter;
}
