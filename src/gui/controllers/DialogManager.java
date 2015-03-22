package gui.controllers;

import javafx.scene.control.Alert;

/**
 * Created by Max on 16.03.2015.
 */
public class DialogManager {

    public static void showInfoDialog(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText("Life is Life! Na-na, na-na-na");
        alert.showAndWait();
    }

    public static void showErrorDialog(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText("We are totally sorry!");
        alert.showAndWait();
    }
}
