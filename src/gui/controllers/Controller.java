package gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import objects.Cell;
import objects.Grid;
import objects.GridManager;

import static javafx.scene.paint.Color.*;


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
    public static volatile int yLength = 35;
    public static volatile int xLength = 35;
    public static final double ELEMENT_SIZE = 10;
    public static final double GAP = ELEMENT_SIZE / 10;
    private GridManager gridManager;
    private Rectangle[][] rectangles;
    public static boolean runLife = false;
    private Grid generation;



    @FXML
    private void initialize() {
//        initListeners();
        gridManager = new GridManager();
        tileGridGame.setStyle("-fx-background-color: rgba(255, 215, 200, 0.1);");
        tileGridGame.setHgap(GAP);
        tileGridGame.setVgap(GAP);
        tileGridGame.setPrefColumns(xLength);
        tileGridGame.setPrefRows(yLength);
        drawGridDisplay(gridManager.getCurrentGeneration());
        System.out.println("GridDisplay cols:" + xLength + "  GridDisplay rows:" + yLength);
//        gameManager.getCurrentGeneration().printGrid();
//        System.out.println("Controller cols " + cols + "  Controller rows " + rows);
    }

    public void drawGridDisplay(Grid generation){
        this.generation = generation;
        createElements();
        for (int j = 0; j < yLength; j++) {
            for (int i = 0; i < xLength; i++) {
                tileGridGame.getChildren().add(rectangles[i][j]);
            }
        }
    }

    public void reDrawGridDisplay(Grid generation){
        this.generation = generation;
        for (int j = 0; j < yLength; j++) {
            for (int i = 0; i < xLength; i++) {
                setRectangleColor(rectangles[i][j], generation.getCell(i, j).getState());
            }
        }
    }

    private void createElements() {
        tileGridGame.getChildren().clear();
        rectangles = new Rectangle[xLength][yLength];
        for (int j = 0; j < yLength; j++) {
            for (int i = 0; i < xLength; i++) {
                rectangles[i][j] = createElement(i, j);
            }
        }
    }

    private Rectangle createElement(int x, int y) {
        Rectangle rectangle = new Rectangle(ELEMENT_SIZE, ELEMENT_SIZE);
        rectangle.setStroke(Color.GREY);
        setRectangleColor(rectangle, generation.getCell(x, y).getState());
        return rectangle;
    }

    private Rectangle setRectangleColor(Rectangle rectangle, Cell.State state){
        switch (state){
            case ALIVE:
                rectangle.setFill(GREEN);
//                System.out.println("1");
                break;
            case DEAD:
                rectangle.setFill(LIGHTGRAY);
//                System.out.println("2");
                break;
            case BLANK:
                rectangle.setFill(WHITE);
//                System.out.println("pipi");
                break;
        }

        return rectangle;
    }

    public void nextGeneration() {
        gridManager.bornNewGeneration();
    }

    public void btnNextGenerate(ActionEvent actionEvent) {
        System.out.println("generated");
        nextGeneration();
        reDrawGridDisplay(gridManager.getCurrentGeneration());
    }

    public synchronized void runLife(ActionEvent actionEvent) throws InterruptedException {
        if (toggleRun.isSelected()) {
            System.out.println("pressed");
        } else System.out.println("not pressed");
    }

    public void startLife(ActionEvent actionEvent) throws InterruptedException {
        runLife = true;
        while (runLife){
            reDrawGridDisplay(gridManager.getCurrentGeneration());
            Thread.sleep(1000);
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
