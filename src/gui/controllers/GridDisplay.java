package gui.controllers;

import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import objects.Cell;
import objects.Grid;

import static javafx.scene.paint.Color.*;

/**
 * Created by Max on 21.03.2015.
 */
public class GridDisplay {
    public static final double ELEMENT_SIZE = 15;
    public static final double GAP = ELEMENT_SIZE / 10;
    private int yLength;
    private int xLength;
    private Grid generation;

//    public GameManager gameManager = new GameManager();
//    public Grid currentGeneration = gameManager.getCurrentGeneration();

    private TilePane tilePane;
//    private Group display = new Group(tilePane);
//    private int yLength = (int) (Controller.gameGridHeight / (ELEMENT_SIZE + GAP));
//    private int xLength = (int) (Controller.gameGridWidth / (ELEMENT_SIZE + GAP));



    public GridDisplay(TilePane tile, int xLength, int yLength, Grid generation) {
        this.tilePane = tile;
        this.xLength = xLength;
        this.yLength = yLength;
        this.generation = generation;
        tilePane.setStyle("-fx-background-color: rgba(255, 215, 200, 0.1);");
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP);
        setXLength(xLength);
        setYLength(yLength);
        System.out.println("GridDisplay cols:" + xLength + "  GridDisplay rows:" + yLength);
    }

    public void redrawGridDisplay(Grid generation){
        this.generation = generation;
        setXLength(xLength);
        setYLength(yLength);
    }

    public void setXLength(int columns) {
        xLength = columns;
        tilePane.setPrefColumns(xLength);
        createElements();
    }

    public void setYLength(int rows) {
        yLength = rows;
        tilePane.setPrefRows(yLength);
        createElements();
    }

    private void createElements() {
        tilePane.getChildren().clear();
        for (int j = 0; j < yLength; j++) {
            for (int i = 0; i < xLength; i++) {
                tilePane.getChildren().add(createElement(i, j));
            }
        }
    }

    private Rectangle createElement(int x, int y) {
        Rectangle rectangle = new Rectangle(ELEMENT_SIZE, ELEMENT_SIZE);
        rectangle.setStroke(Color.GREY);
        Cell.State state = generation.getCell(x, y).getState();
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

}
