package gui.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import objects.GameManager;


//
public class Controller {
    @FXML
    public Button btnNext;
    @FXML
    public ChoiceBox comboSpeed;
    @FXML
    public ChoiceBox comboShape;
    @FXML
    public ToggleButton toggleRun;
    @FXML
    public AnchorPane anchorGridGame;

    public static int gameGridHeight = 8;
    public static int gameGridWidth = 50;
//    public GameManager gameManager = new GameManager();
//    public Grid currentGeneration = gameManager.getCurrentGeneration();
//    public GridPane gridGame;
    public TilePane tileGridGame;


    @FXML
    private void initialize() {
        initListeners();
//        setGridGame();
//        gameManager.bornNewGeneration();

        GameManager gameManager = new GameManager();
        GridDisplay gridDisplay = new GridDisplay(tileGridGame, gameGridWidth, gameGridHeight/*, gameManager.getCurrentGeneration()*/);
    }


    public void nextGeneration(ActionEvent actionEvent) {
        System.out.println("generated");
    }

    public void runLife(ActionEvent actionEvent) {
        if (toggleRun.isSelected()) System.out.println("pressed");
        else System.out.println("not pressed");
    }

    private void initListeners(){
        anchorGridGame.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                gameGridWidth = newSceneWidth.intValue();
                System.out.println("Width: " + gameGridWidth);
            }
        });
        anchorGridGame.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                gameGridHeight = newSceneHeight.intValue();
                System.out.println("Height: " + gameGridHeight);
            }
        });
    }



}
