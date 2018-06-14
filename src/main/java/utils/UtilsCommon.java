package main.java.utils;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;


public class UtilsCommon {
    private Rectangle2D currentScreen = Screen.getPrimary().getVisualBounds();

    public double getScreenWidth() {
        return currentScreen.getWidth();
    }

    public double getScreenHeight() {
        return currentScreen.getHeight();
    }
}
