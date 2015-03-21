package objects;

import gui.controllers.Controller;

/**
 * Created by Max on 20.03.2015.
 */
public class GameManager {
    private static int gridX = Controller.gameGridWidth;
    private int gridY = Controller.gameGridHeight;
    private Grid currentGeneration = new Grid(gridX, gridY);
    private Grid newGeneration  = new Grid(gridX, gridY);
    private int generationCounter = 1;

    public void bornNewGeneration(){
        //calculation of surrounding dead/alive neighbours (not on the edge of the grid)
        for (int j = 1; j < gridY - 1; j++){
            for (int i = 1; i < gridX - 1; i++) {
                if (currentGeneration.getCell(i, j).isAlive()) {
                    //under-population or overcrowding
                    if (amountOfAliveNeighbours(i, j) < 2 || amountOfAliveNeighbours(i, j) > 3) newGeneration.getCell(i, j).setState(Cell.State.DEAD);
                        //keeping beeing alive
                    else newGeneration.getCell(i, j).setState(Cell.State.ALIVE);
                }
                if (currentGeneration.getCell(i, j).isDead()) {
                    //reproduction
                    if (amountOfAliveNeighbours(i, j) == 3) newGeneration.getCell(i, j).setState(Cell.State.ALIVE);
                }
            }
        }
        generationCounter++;
        currentGeneration = newGeneration;
    }

    private int amountOfAliveNeighbours(int x, int y){
        int amountOfAliveNeighbours = 0;

        int leftX = x - 1;
        int centralX = x;
        int rightX = x + 1;
        int topY = y + 1;
        int centralY = y;
        int bottomY = y - 1;

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

        if (currentGeneration.getCell(leftX, topY).isAlive()) amountOfAliveNeighbours++;
        if (currentGeneration.getCell(leftX, centralY).isAlive()) amountOfAliveNeighbours++;
        if (currentGeneration.getCell(leftX, bottomY).isAlive()) amountOfAliveNeighbours++;
        if (currentGeneration.getCell(centralX, topY).isAlive()) amountOfAliveNeighbours++;

        if (currentGeneration.getCell(centralX, bottomY).isAlive()) amountOfAliveNeighbours++;
        if (currentGeneration.getCell(rightX, topY).isAlive()) amountOfAliveNeighbours++;
        if (currentGeneration.getCell(rightX, centralY).isAlive()) amountOfAliveNeighbours++;
        if (currentGeneration.getCell(rightX, bottomY).isAlive()) amountOfAliveNeighbours++;

        return amountOfAliveNeighbours;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public Grid getCurrentGeneration() {
        return currentGeneration;
    }

    public Grid getNewGeneration() {
        return newGeneration;
    }

    public int getGenerationCounter() {
        return generationCounter;
    }

    public void setCurrentGeneration(Grid currentGeneration) {
        this.currentGeneration = currentGeneration;
    }

    public void randomlyBornGeneration() {
        currentGeneration.randomlyFillGrid();
    }
}
