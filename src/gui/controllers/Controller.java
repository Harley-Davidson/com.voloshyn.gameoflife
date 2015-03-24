package gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import objects.Cell;
import objects.Grid;
import objects.GridManager;
import org.reactfx.util.FxTimer;
import org.reactfx.util.Timer;

import java.time.Duration;

import static javafx.scene.paint.Color.*;


//
public class Controller{
    public static final double ELEMENT_SIZE = 12;
    public static final double GAP = ELEMENT_SIZE / 10;
    public static int gameGridHeight = 700;
    public static int gameGridWidth = 1150;
    public static int rows = (int) (Controller.gameGridHeight / (ELEMENT_SIZE + GAP));
    public static int cols = (int) (Controller.gameGridWidth / (ELEMENT_SIZE + GAP));
    @FXML
    public Button btnNext;
    @FXML
    public AnchorPane anchorGridGame;
    @FXML
    public TilePane tilePane;
    @FXML
    public ChoiceBox<String> choiceBox;
    @FXML
    public Button btnReDraw;
    @FXML
    public Label lblGenCount;
    public Button btnStopLife;
    public Button btnRunLife;

    private Grid generation;
    private GridManager gridManager;
    private ObservableList<Rectangle> rectangles = FXCollections.observableArrayList();
    private Timer timer0;
    private Timer timer1;

    @FXML
    private void initialize() {
//        initListeners();
        gridManager = new GridManager();
        choiceBox.getItems().addAll("9CellShape", "10CellShape", "LineShape", "Glider", "Pulsar", "LightWeightSpaceShip", "Blank Grid", "Random Fill");
        drawNewGridDisplay(gridManager.getCurrentGeneration());
    }

    @FXML
    public void btnNextGenerate(ActionEvent actionEvent) {
        gridManager.bornNewGeneration();
        reDrawGridDisplay(gridManager.getCurrentGeneration());
        gridManager.checkIfGameOver();
    }

    @FXML
    public void fillShapeToGrid(ActionEvent actionEvent) {
        String choiceBoxValue = choiceBox.getValue();
        if (choiceBoxValue != null) {
            switch (choiceBoxValue) {
                case "Random Fill":
                    gridManager.getCurrentGeneration().randomlyFillGrid();
                    break;
                case "Pulsar":
                    gridManager.fillPulsarShape();
                    break;
                case "Blank Grid":
                    gridManager.setCurrentGeneration(new Grid(cols, rows));
                    break;
                case "10CellShape":
                    gridManager.fill10CellShape();
                    break;
                case "9CellShape":
                    gridManager.fill9CellShape();
                    break;
                case "LineShape":
                    gridManager.fillLineShape();
                    break;
                case "Glider":
                    gridManager.fillGliderShape();
                    break;
                case "LightWeightSpaceShip":
                    gridManager.fillLightWeightSpaceShipShape();
                    break;
            }
        }
        gridManager.setGenerationCounter(1);
        reDrawGridDisplay(gridManager.getCurrentGeneration());
    }

    @FXML
    public void labelGenerationCounter(Event event) {
        lblGenCount.setText(String.valueOf(gridManager.getGenerationCounter()));
    }

    @FXML
    public void runLife(ActionEvent actionEvent) {
        timer0 = FxTimer.runPeriodically(
                Duration.ofMillis(20),
                () -> reDrawGridDisplay(gridManager.getCurrentGeneration()));
        timer1 = FxTimer.runPeriodically(
                Duration.ofMillis(20),
                gridManager::bornNewGeneration);
        gridManager.checkIfGameOver();
        btnRunLife.setDisable(true);
    }

    @FXML
    public void stopLife(ActionEvent actionEvent) {
        if (btnRunLife.isDisabled()) {
            timer0.stop();
            timer1.stop();
            btnRunLife.setDisable(false);
        } else DialogManager.showErrorDialog("Oops", "Seems like we are in the outer space. Life is frozen here!");
        gridManager.checkIfGameOver();
    }

    public void drawNewGridDisplay(Grid generation) {
        this.generation = generation;
        tilePane.setStyle("-fx-background-color: rgba(255, 215, 200, 0.1);");
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP);
        tilePane.setPrefColumns(cols);
        tilePane.setPrefRows(rows);
        drawGridDisplay(generation);
        System.out.println("GridDisplay cols:" + cols + "  GridDisplay rows:" + rows);
    }

    public void drawGridDisplay(Grid generation) {
        this.generation = generation;
        createElements();
        for (Rectangle currentRectangle : rectangles) {
            tilePane.getChildren().add(currentRectangle);
        }
    }

    public void reDrawGridDisplay(Grid generation) {
        this.generation = generation;

        for (Rectangle currentRectangle : rectangles) {
            int x = (int) currentRectangle.getX();
            int y = (int) currentRectangle.getY();
            setRectangleColor(currentRectangle, generation.getCell(x, y).getState());
            lblGenCount.setText("Generations born: ".concat(String.valueOf(gridManager.getGenerationCounter())));
        }
    }

    private void createElements() {
        tilePane.getChildren().clear();
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                rectangles.add(createElement(i, j));
            }
        }
    }

    private Rectangle createElement(int x, int y) {
        Rectangle rectangle = new Rectangle(x, y, ELEMENT_SIZE, ELEMENT_SIZE);
        rectangle.setStroke(Color.GREY);
        setRectangleColor(rectangle, generation.getCell(x, y).getState());
        return rectangle;
    }

    private Rectangle setRectangleColor(Rectangle rectangle, Cell.State state) {
        switch (state) {
            case ALIVE:
                rectangle.setFill(GREEN);
                break;
            case DEAD:
                rectangle.setFill(LIGHTGRAY);
                break;
            case BLANK:
                rectangle.setFill(WHITE);
                break;
        }

        return rectangle;
    }


//    private void initListeners() {
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
