package gui.controllers;

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
    @FXML
    public TilePane tileGridGame;
    @FXML
    public Button btnStop;
    @FXML
    public Button btnStart;

//    public static int gameGridHeight = 500;
//    public static int gameGridWidth = 700;
//    public static volatile int rows = (int) (Controller.gameGridHeight / (GridDisplay.ELEMENT_SIZE + GridDisplay.GAP));
//    public static volatile int cols = (int) (Controller.gameGridWidth / (GridDisplay.ELEMENT_SIZE + GridDisplay.GAP));
    public static volatile int rows = 25;
    public static volatile int cols = 25;
    private GameManager gameManager;
    private GridDisplay gridDisplay;
    private static boolean runLife = false;


    @FXML
    private void initialize() {
//        initListeners();
//        setGridGame();
//        gameManager.bornNewGeneration();
        gameManager = new GameManager();
        gridDisplay = new GridDisplay(tileGridGame, cols, rows, gameManager.getCurrentGeneration());
//        gameManager.getCurrentGeneration().printGrid();
        System.out.println("Controller cols " + cols + "  Controller rows " + rows);
    }


    public void nextGeneration(ActionEvent actionEvent) {
        System.out.println("generated");
        gridDisplay.redrawGridDisplay(gameManager.bornNewGeneration());
    }

    public void runLife(ActionEvent actionEvent) throws InterruptedException {
        if (toggleRun.isSelected()) {
            for (int i = 0; i < 1000; i++) {

            }

            System.out.println("pressed");
        }
        else System.out.println("not pressed");
    }

    public void startLife(ActionEvent actionEvent) throws InterruptedException {
        runLife = true;
        while (true){
            gridDisplay.redrawGridDisplay(gameManager.bornNewGeneration());

            System.out.println("next gen");
            if (!runLife) break;
        }
    }

    public void stopLife(ActionEvent actionEvent) {
        runLife = false;
    }

//    private void initListeners(){
//        anchorGridGame.widthProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
//                gameGridWidth = newSceneWidth.intValue();
//                System.out.println("Width: " + gameGridWidth);
//            }
//        });
//        anchorGridGame.heightProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
//                gameGridHeight = newSceneHeight.intValue();
//                System.out.println("Height: " + gameGridHeight);
//            }
//        });
//    }



}
