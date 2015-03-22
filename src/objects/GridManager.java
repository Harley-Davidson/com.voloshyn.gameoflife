package objects;

import gui.controllers.Controller;

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
        gridX = Controller.xLength - 1;
        gridY = Controller.yLength - 1;
        currentGeneration = new Grid(Controller.xLength, Controller.yLength);
//        fillShape();
        newGeneration  = new Grid(Controller.xLength, Controller.yLength);
        generationCounter = 1;
        currentGeneration.randomlyFillGrid();
//        currentGeneration.printGrid();
    }

    public synchronized Grid bornNewGeneration() {
        newGeneration = new Grid(Controller.xLength, Controller.yLength);
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
//            System.out.print("   " + leftX + " " + topY + " is Alive  &");
        }
//        else System.out.print("   " + leftX + " " + topY + " is Dead  &");

        if (currentGeneration.getCell(leftX, centralY).isAlive()){ amountOfAliveNeighbours++;
//        System.out.print("   " + leftX + " " + centralY + " is Alive  &");
    }
//    else System.out.print("   " + leftX + " " + centralY + " is Dead  &");

        if (currentGeneration.getCell(leftX, bottomY).isAlive()) {amountOfAliveNeighbours++;
//            System.out.println("   " + leftX + " " + bottomY + " is Alive  &");
        }
//        else System.out.println("   " + leftX + " " + bottomY + " is Dead  &");

        if (currentGeneration.getCell(centralX, topY).isAlive()){ amountOfAliveNeighbours++;
//            System.out.print("   " + centralX + " " + topY + " is Alive  &");
        }
//        else System.out.print("   " + centralX + " " + topY + " is Dead  &");


        if (currentGeneration.getCell(centralX, bottomY).isAlive()) {amountOfAliveNeighbours++;
//            System.out.println("   " + centralX + " " + bottomY + " is Alive  &");
        }
//        else System.out.println("   " + centralX + " " + bottomY + " is Dead  &");

        if (currentGeneration.getCell(rightX, topY).isAlive()){ amountOfAliveNeighbours++;
//            System.out.print("   " + rightX + " " + topY + " is Alive  &");
        }
//        else System.out.print("   " + rightX + " " + topY + " is Dead  &");

        if (currentGeneration.getCell(rightX, centralY).isAlive()) {amountOfAliveNeighbours++;
//            System.out.print("   " + rightX + " " + centralY + " is Alive  &");
        }
//        else System.out.print("   " + rightX + " " + centralY + " is Dead  &");

        if (currentGeneration.getCell(rightX, bottomY).isAlive()) {amountOfAliveNeighbours++;
//            System.out.println("   " + rightX + " " + bottomY + " is Alive  &");
        }
//        else System.out.println("   " + rightX + " " + bottomY + " is Dead  &");

        return amountOfAliveNeighbours;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public synchronized Grid getCurrentGeneration() {
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

    public void fillShape(){
        currentGeneration = new Grid(Controller.xLength, Controller.yLength);
        for (int i = 0; i <5; i++) {
            currentGeneration.setCellStateInGrid((gridX/2 - 2), (gridY/2 - 2 + i), Cell.State.ALIVE);
            currentGeneration.setCellStateInGrid((gridX/2 + 2), (gridY/2 - 2 + i), Cell.State.ALIVE);
        }
        currentGeneration.setCellStateInGrid((gridX/2), (gridY/2 - 2), Cell.State.ALIVE);
        currentGeneration.setCellStateInGrid((gridX/2), (gridY/2 + 2), Cell.State.ALIVE);

//        currentGeneration.setCellStateInGrid(1, 0, Cell.State.ALIVE);
//        currentGeneration.setCellStateInGrid(1, 1, Cell.State.ALIVE);
//        currentGeneration.setCellStateInGrid(1, 2, Cell.State.ALIVE);
//        currentGeneration.setCellStateInGrid(21, 17, Cell.State.ALIVE);
    }


//    @Override
//    public void run() {
//        while (Controller.runLife){
//            bornNewGeneration();
//        }
//    }
}
