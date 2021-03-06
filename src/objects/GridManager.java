package objects;

import gui.controllers.Controller;
import gui.controllers.DialogManager;

/**
 * Created by Max on 20.03.2015.
 */
public class GridManager{
    private int gridX;
    private int gridY;
    private Grid currentGeneration;
    private Grid newGeneration;
    private int generationCounter;

    public GridManager() {
        gridX = Controller.cols - 1;
        gridY = Controller.rows - 1;
        currentGeneration = new Grid(Controller.cols, Controller.rows);
        newGeneration = new Grid(Controller.cols, Controller.rows);
        generationCounter = 1;
        currentGeneration.randomlyFillGrid();
//        currentGeneration.clearGrid();
//        currentGeneration.printGrid();
    }

    public Grid bornNewGeneration() {
        newGeneration = new Grid(Controller.cols, Controller.rows);
        int amountOfAliveNeighbours;
        //calculation of surrounding dead/alive neighbours (not on the edge of the grid)
        for (int j = 0; j <= gridY; j++){
            for (int i = 0; i <= gridX; i++) {
                amountOfAliveNeighbours = amountOfAliveNeighbours(i, j);
                if (currentGeneration.getCell(i, j).isAlive()) {
                    //under-population or overcrowding
                    if (amountOfAliveNeighbours < 2 || amountOfAliveNeighbours > 3) newGeneration.getCell(i, j).setState(Cell.State.DEAD);
                    //keeping being alive
                    else newGeneration.getCell(i, j).setState(Cell.State.ALIVE);
                }
                if (currentGeneration.getCell(i, j).isDead()) {
                    //reproduction
                    if (amountOfAliveNeighbours == 3) newGeneration.getCell(i, j).setState(Cell.State.ALIVE);
                    else newGeneration.getCell(i, j).setState(Cell.State.DEAD);
                }
                if (currentGeneration.getCell(i, j).isBlank()) {
                    //reproduction
                    if (amountOfAliveNeighbours == 3) newGeneration.getCell(i, j).setState(Cell.State.ALIVE);
                    else newGeneration.getCell(i, j).setState(Cell.State.BLANK);
                }
//                System.out.println("row: " + j + ", col : " + i + ", lifeNeighb : " + amountOfAliveNeighbours + ", CellState : " + currentGeneration.getCell(i, j).getState());
            }
        }
        generationCounter++;
        currentGeneration = newGeneration;

//        Thread.sleep(100);
        return currentGeneration;
    }

    private int amountOfAliveNeighbours(int x, int y){
        int amountOfAliveNeighbours = 0;

        int leftX = x - 1;
        int centralX = x;
        int rightX = x + 1;
        int topY = y - 1;
        int centralY = y;
        int bottomY = y + 1;

        if (x == 0 && y > 0 && y < gridY){
            leftX = gridX;
        }
        if (x == gridX && y > 0 && y < gridY){
            rightX = 0;
        }
        if (x > 0 && x < gridX && y == 0){
            topY = gridY;
        }
        if (x > 0 && x < gridX && y == gridY){
            bottomY = 0;
        }
        if (x == 0 && y == 0){
            leftX = gridX;
            topY = gridY;
        }
        if (x == 0 && y == gridY){
            leftX = gridX;
            bottomY = 0;
        }
        if (x == gridX && y == 0){
            rightX = 0;
            topY = gridY;
        }
        if (x == gridX && y == gridY){
            rightX = 0;
            bottomY = 0;
        }

        if (currentGeneration.getCell(leftX, topY).isAlive()) {
            amountOfAliveNeighbours++;
        }
        if (currentGeneration.getCell(leftX, centralY).isAlive()) {
            amountOfAliveNeighbours++;
        }
        if (currentGeneration.getCell(leftX, bottomY).isAlive()) {
            amountOfAliveNeighbours++;
        }
        if (currentGeneration.getCell(centralX, topY).isAlive()) {
            amountOfAliveNeighbours++;
        }

        if (currentGeneration.getCell(centralX, bottomY).isAlive()) {
            amountOfAliveNeighbours++;
        }
        if (currentGeneration.getCell(rightX, topY).isAlive()) {
            amountOfAliveNeighbours++;
        }
        if (currentGeneration.getCell(rightX, centralY).isAlive()) {
            amountOfAliveNeighbours++;
        }
        if (currentGeneration.getCell(rightX, bottomY).isAlive()) {
            amountOfAliveNeighbours++;
        }


        return amountOfAliveNeighbours;
    }

    public Grid getCurrentGeneration() {
        return currentGeneration;
    }

    public void setCurrentGeneration(Grid currentGeneration) {
        this.currentGeneration = currentGeneration;
    }


    public void checkIfGameOver() {
        int aliveCells = 0;
        for (int j = 0; j < gridY; j++) {
            for (int i = 0; i < gridX; i++) {
                if (currentGeneration.getCell(i, j).isAlive()) aliveCells++;
            }
        }
        if (aliveCells == 0) {
            DialogManager.showInfoDialog("Game Over", "Congratulations, no one is alive here!");

        }
    }

    public void fillPulsarShape() {
        currentGeneration = new Grid(Controller.cols, Controller.rows);
        for (int i = 0; i <5; i++) {
            currentGeneration.setCellStateInGrid((gridX/2 - 2), (gridY/2 - 2 + i), Cell.State.ALIVE);
            currentGeneration.setCellStateInGrid((gridX/2 + 2), (gridY/2 - 2 + i), Cell.State.ALIVE);
        }
        currentGeneration.setCellStateInGrid((gridX/2), (gridY/2 - 2), Cell.State.ALIVE);
        currentGeneration.setCellStateInGrid((gridX/2), (gridY/2 + 2), Cell.State.ALIVE);
    }

    public void fill10CellShape() {
        currentGeneration = new Grid(Controller.cols, Controller.rows);
        for (int i = 0; i < 5; i++) {
            currentGeneration.setCellStateInGrid((gridX / 2 - i), (gridY / 2), Cell.State.ALIVE);
            currentGeneration.setCellStateInGrid((gridX / 2 + i), (gridY / 2), Cell.State.ALIVE);
        }
        currentGeneration.setCellStateInGrid((gridX / 2 + 5), (gridY / 2), Cell.State.ALIVE);
    }

    public void fill9CellShape() {
        currentGeneration = new Grid(Controller.cols, Controller.rows);
        for (int i = 0; i < 5; i++) {
            currentGeneration.setCellStateInGrid((gridX / 2 - i), (gridY / 2), Cell.State.ALIVE);
            currentGeneration.setCellStateInGrid((gridX / 2 + i), (gridY / 2), Cell.State.ALIVE);
        }
    }

    public void fillGliderShape() {
        currentGeneration = new Grid(Controller.cols, Controller.rows);
        currentGeneration.setCellStateInGrid((gridX / 2 + 1), (gridY / 2), Cell.State.ALIVE);
        currentGeneration.setCellStateInGrid((gridX / 2 - 1), (gridY / 2 - 1), Cell.State.ALIVE);
        currentGeneration.setCellStateInGrid((gridX / 2), (gridY / 2 - 1), Cell.State.ALIVE);
        currentGeneration.setCellStateInGrid((gridX / 2 + 1), (gridY / 2 - 1), Cell.State.ALIVE);
        currentGeneration.setCellStateInGrid((gridX / 2), (gridY / 2 + 1), Cell.State.ALIVE);
    }

    public void fillLineShape() {
        currentGeneration = new Grid(Controller.cols, Controller.rows);
        for (int i = 0; i < 19; i++) {
            currentGeneration.setCellStateInGrid((gridX / 2 - i), (gridY / 2), Cell.State.ALIVE);
            currentGeneration.setCellStateInGrid((gridX / 2 + i), (gridY / 2), Cell.State.ALIVE);
        }
        currentGeneration.setCellStateInGrid((gridX / 2 - 2), (gridY / 2), Cell.State.BLANK);
        currentGeneration.setCellStateInGrid((gridX / 2 - 3), (gridY / 2), Cell.State.BLANK);
        currentGeneration.setCellStateInGrid((gridX / 2 - 4), (gridY / 2), Cell.State.BLANK);
        currentGeneration.setCellStateInGrid((gridX / 2 - 10), (gridY / 2), Cell.State.BLANK);
        currentGeneration.setCellStateInGrid((gridX / 2 + 15), (gridY / 2), Cell.State.BLANK);
        for (int i = 0; i < 6; i++) {
            currentGeneration.setCellStateInGrid((gridX / 2 + 2 + i), (gridY / 2), Cell.State.BLANK);
        }
    }

    public void fillLightWeightSpaceShipShape() {
        currentGeneration = new Grid(Controller.cols, Controller.rows);
        for (int i = 0; i < 3; i++) {
            currentGeneration.setCellStateInGrid((gridX / 2 - 2), (gridY / 2 - 1 + i), Cell.State.ALIVE);
            currentGeneration.setCellStateInGrid((gridX / 2 - 1 + i), (gridY / 2 - 1), Cell.State.ALIVE);
        }
        currentGeneration.setCellStateInGrid((gridX / 2 - 1), (gridY / 2 + 2), Cell.State.ALIVE);
        currentGeneration.setCellStateInGrid((gridX / 2 + 2), (gridY / 2), Cell.State.ALIVE);
        currentGeneration.setCellStateInGrid((gridX / 2 + 2), (gridY / 2 + 2), Cell.State.ALIVE);
    }


    public int getGenerationCounter() {
        return generationCounter;
    }

    public void setGenerationCounter(int generationCounter) {
        this.generationCounter = generationCounter;
    }
}
