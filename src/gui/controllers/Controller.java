package gui.controllers;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import objects.GridManager;


//
public class Controller{
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
    public static volatile int rows = 35;
    public static volatile int cols = 35;
    private GridManager gridManager;
    private GridDisplay gridDisplay;
    public static boolean runLife = false;
    protected static Thread gameThread = null;
    private AnimationTimer timer;
    private Timeline timeline;


    @FXML
    private void initialize() {
//        initListeners();
        gridManager = new GridManager();
        gridDisplay = new GridDisplay(tileGridGame, cols, rows, gridManager.getCurrentGeneration());
//        gameManager.getCurrentGeneration().printGrid();
//        System.out.println("Controller cols " + cols + "  Controller rows " + rows);
    }

    public void nextGeneration() {
        gridManager.bornNewGeneration();
    }

    public void btnNextGenerate(ActionEvent actionEvent) {
        System.out.println("generated");
        nextGeneration();
        gridDisplay.reDrawGridDisplay(gridManager.getCurrentGeneration());
    }

    public synchronized void runLife(ActionEvent actionEvent) throws InterruptedException {
        if (toggleRun.isSelected()) {
            System.out.println("pressed");
        } else System.out.println("not pressed");
    }

    public void startLife(ActionEvent actionEvent) throws InterruptedException {
    }


    public void stopLife(ActionEvent actionEvent) {
        runLife = false;
        gameThread = null;
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
