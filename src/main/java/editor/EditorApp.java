package main.java.editor;

import com.sun.istack.internal.Nullable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.Group;
import main.java.UI.*;
import main.java.utils.UtilsCommon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EditorApp extends Application {

    public void addQuestions() {
        addRoot.getChildren().clear();
        addScreen = initAddScreen();
        //addScreen.setPrefHeight(utils.getScreenHeight());
        //addScreen.setPrefWidth(utils.getScreenWidth());
        addRoot.getChildren().add(addScreen);
        mainScene.setRoot(addRoot);
        currentMenu = addMenu;
    }

    public void newMap() {
        editRoot.getChildren().clear();
        try {
            editScreen = initEditScreen(null);
            editRoot.getChildren().add(editScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainScene.setRoot(editRoot);
        currentMenu = editMenu;
    }

    private void setGlobalTimer() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Timer");
        dialog.setHeaderText("Set global timer for this map.");
        dialog.setContentText("Number in milisec:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(number ->{
            editScreen.setTimer(Long.parseLong(number));});
    }
    private void importMap() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import map");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.png")
        );
        File file = (fileChooser.showOpenDialog(new Stage()));
        Image img=new Image(file.toURI().toString());
        editScreen.setBackgroundPath(file.getAbsolutePath());
        editScreen.setBackground(img);
    }


    public void importBackground() {

    }
    public void addUserInputQuestion() {
        UserInputQ newQuestion = null;
        try {
            newQuestion = new UserInputQ(new JSONObject("{\"type\":0}"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        addScreen.addQuestion(newQuestion);
    }
    public void addMultipleChoiceQuestion() {
        MultipleChoiceQ newQuestion = null;
        try {
            newQuestion = new MultipleChoiceQ(new JSONObject("{\"type\":1}"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        addScreen.addQuestion(newQuestion);
    }
    public void deleteQuestion() {
        addScreen.deleteQuestion(addScreen.getSelectedQuestion());
    }
    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.flush();
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAndBack() {
        JSONObject jsonObject = new JSONObject();
        ArrayList<JSONObject> jsonObjectArrayListOfQuestions= new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        addScreen.getQuestions().stream().map(Question::save).forEach(jsonObjectArrayListOfQuestions::add);
        jsonObjectArrayListOfQuestions.forEach(jsonArray::put);
        try { jsonObject.put("backgroundSource", editScreen.getBackgroundPath())
                        .put("globalTimer", editScreen.getTimer())
                        .put("questions", jsonObjectArrayListOfQuestions)
                        .put("shapes",new JSONArray());
            if (currentlyOpenFile==null){
                currentlyOpenFile= new File("newMap.map");
            }
            SaveFile(jsonObject.toString(4),currentlyOpenFile);
         }catch (JSONException e) {
            e.printStackTrace();
        }
        backToEdit();
    }

    private void backToEdit(){
        mainScene.setRoot(editRoot);
        currentMenu = editMenu;
    }
    public void backToMain() {
        mainScene.setRoot(mainRoot);
        currentMenu = mainMenu;
    }

    public void close() {
        Platform.exit();
        System.exit(0);
    }

    public void editMap() {
        fileChooser.setTitle("Open Map");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.map")
        );
        Stage currentStage = new Stage();
        currentlyOpenFile = fileChooser.showOpenDialog(currentStage);
            editRoot.getChildren().clear();
        try {
            editScreen = initEditScreen(currentlyOpenFile.getCanonicalPath());
            editRoot.getChildren().add(editScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    private GameFrame initEditScreen(String input) throws IOException {
        GameFrame editScreen = new GameFrame();
        if ( input!= null ){
            mapLoader(input, editScreen);
        } else {
            editScreen.setBackground(defaultMap);
        }
        this.editMenu.setPrefWidth(utils.getScreenWidth()/5);
        this.editMenu.setPrefHeight(utils.getScreenHeight());
        editScreen.setRight(this.editMenu);
        this.editMenu.setAlignment(Pos.CENTER);

        return editScreen;
    }

    private void mapLoader(String input, GameFrame editScreen) throws IOException{
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            String myJsonFile = sb.toString();
            JSONObject jsonObjectMap = new JSONObject(myJsonFile);
            String backgroundPath = jsonObjectMap.optString("backgroundSource", "default.png");
            InputStream fileInputStream;
            if (!backgroundPath.contains("/")) {
                backgroundPath = "resources/maps/" + backgroundPath;
                fileInputStream = GameFrame.class.getResourceAsStream(backgroundPath);
            }else {
                fileInputStream =new FileInputStream(backgroundPath);
            }

            editScreen.setBackgroundPath(backgroundPath);
            editScreen.setBackground(new Image(fileInputStream));

            JSONArray jsonArray = jsonObjectMap.getJSONArray("questions");
            ArrayList<Question> arrayListQuestion = new ArrayList<>();

            for (int i = 0 ; i < jsonArray.length(); i++){
                int typeInt = jsonArray.optJSONObject(i).optInt("type");
                if (typeInt == 0){
                    arrayListQuestion.add(new UserInputQ(jsonArray.getJSONObject(i)));
                } else if (typeInt == 1){
                    arrayListQuestion.add(new MultipleChoiceQ(jsonArray.getJSONObject(i)));
                }
            }
            editScreen.setQuestions(arrayListQuestion);

        } catch (JSONException e) {
            editScreen.setBackground(defaultMap);
            e.printStackTrace();
        }


    }

    private QuestionEditPane initAddScreen() {
        QuestionEditPane addScreen = new QuestionEditPane(editScreen.getQuestions());
        addScreen.setRight(this.addMenu);
        this.addMenu.setAlignment(Pos.CENTER);
        return addScreen;
    }
    @Override
    public void start(Stage editorStage) {
        mainScreen = initMainScreen();
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
        editorStage.setScene(mainScene);
    }

    public static void main(String[] argv) {
        launch(argv);
    }

    private Group mainRoot;
    private Group editRoot;
    private Group addRoot;
    private GameFrame editScreen;
    private QuestionEditPane addScreen;
    private BorderPane mainScreen;

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
        this:: newMap,
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
        this::setGlobalTimer,
        this::importMap,
        this::backToMain
    };


    private String[] ADD_MENU_TEXT = {
        "Add basic",
        "Add next-lvl",
        "Delete",
        "Save"
    };
    private String[] ADD_MENU_HINT_TEXT = {
        "(A)dd another question",
        "Add another (M)ulti-choice question",
        "(D)elete current question",
        "(S)ave and back"
    };
    private Selection[] ADD_MENU_ACTIONS = {
        this::addUserInputQuestion,
        this::addMultipleChoiceQuestion,
        this::deleteQuestion,
        this::saveAndBack
    };
    private KeyCode[] ADD_MENU_TRIGGERS = {
        KeyCode.A,
        KeyCode.M,
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
    private OptionMenu currentMenu;
    private Scene mainScene;
    private QuestionFrame currentAdapter;
    private Image defaultMap = new Image(GameFrame.class.getResourceAsStream("resources/maps/default.png"));
    private FileChooser fileChooser = new FileChooser();
    private File currentlyOpenFile=null;
}
