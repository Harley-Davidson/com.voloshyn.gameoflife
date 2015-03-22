package gui.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import objects.Grid;
import objects.GridManager;


//
public class Controller{
    public static int gameGridHeight = 500;
    public static int gameGridWidth = 750;
    public static int rows = (int) (Controller.gameGridHeight / (GridDisplay.ELEMENT_SIZE + GridDisplay.GAP));
    public static int cols = (int) (Controller.gameGridWidth / (GridDisplay.ELEMENT_SIZE + GridDisplay.GAP));
    public static boolean runLife = false;
    private static GridDisplay gridDisplay;
    @FXML
    public Button btnNext;
    @FXML
    public AnchorPane anchorGridGame;
    @FXML
    public TilePane tileGridGame;
    @FXML
    public ChoiceBox choiceBox;
    @FXML
    public Button btnReDraw;
    //    public static int rows = 35;
//    public static int cols = 35;
    private GridManager gridManager;

//    Task<Void> task = new Task<Grid>() {
//
//        @Override protected Grid call() throws Exception {
//
//
//
//            while (runLife) {
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        gridManager.bornNewGeneration();
//                    }
//                });
//            }
//            return gridManager.getCurrentGeneration();;
//        }
//    };

//    public class NewGenerationBorner extends Thread{
//        @Override
//        public void run() {
//            while (Controller.runLife) {
//                gridManager.bornNewGeneration();
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
////                Controller.getGridDisplay().reDrawGridDisplay(gridManager.getCurrentGeneration());
//            }
//        }
//    }

    @FXML
    private void initialize() {
//        initListeners();
        gridManager = new GridManager();
        gridDisplay = new GridDisplay(tileGridGame, cols, rows, gridManager.getCurrentGeneration());
        choiceBox.getItems().addAll("Random Fill", "Shape: Pulsar", "Blank Grid");
//        gameManager.getCurrentGeneration().printGrid();
//        System.out.println("Controller cols " + cols + "  Controller rows " + rows);
    }

//    public void nextGeneration() {
//        GridManager.NewGenerationBorner t1 = new GridManager.NewGenerationBorner();
//        gridManager.bornNewGeneration();
//    }

    public void btnNextGenerate(ActionEvent actionEvent) {
        gridManager.bornNewGeneration();
        Controller.gridDisplay.reDrawGridDisplay(gridManager.getCurrentGeneration());
        gridManager.gameOver();
    }
//
//    public void runLife(ActionEvent actionEvent) throws InterruptedException {
////        if (toggleRun.isSelected()) {
////            System.out.println("pressed");
////        } else System.out.println("not pressed");
//    }
//
//    public void startLife(ActionEvent actionEvent) throws InterruptedException {
//        runLife = true;
////        NewGenerationBorner t1 = new NewGenerationBorner();
////        t1.start();
//////        task.run();
//        gridManager.bornNewGeneration();
//        Controller.gridDisplay.reDrawGridDisplay(gridManager.getCurrentGeneration());
//    }


//    public void stopLife(ActionEvent actionEvent) {
//        runLife = false;
//    }

    private void initListeners() {
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


    @FXML
    public void reDrawGrid(ActionEvent actionEvent) {
        String choiceBoxValue = (String) choiceBox.getValue();
        if (choiceBoxValue != null) {
            switch (choiceBoxValue) {
                case "Random Fill":
                    gridManager.getCurrentGeneration().randomlyFillGrid();
                    break;
                case "Shape: Pulsar":
                    gridManager.fillShape();
                    break;
                case "Blank Grid":
                    gridManager.setCurrentGeneration(new Grid(cols, rows));
                    break;
            }
        }

        Controller.gridDisplay.reDrawGridDisplay(gridManager.getCurrentGeneration());
    }
}
