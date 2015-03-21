package objects;

/**
 * Created by Max on 18.03.2015.
 */
public class Grid {
    private int gridX;
    private int gridY;
    private Cell[][] grid;

    public Grid(int gridX, int gridY) {
        this.gridX = gridX;
        this.gridY = gridY;
        grid = new Cell[gridX][gridY];
        for (int j = 0; j < gridY; j++) {
            for (int i = 0; i < gridX; i++) {
                grid[i][j] = new Cell();
//                System.out.print("*");
            }
//            System.out.println();
        }
    }


    public void printGrid() {
        Cell.State state;
        for (int j = 0; j < gridY; j++) {
            for (int i = 0; i < gridX; i++) {
                state = grid[i][j].getState();
                switch (state) {
                    case ALIVE:
                        System.out.print("0");
                        break;
                    case DEAD:
                        System.out.print("*");
                        break;
                    case BLANK:
                        System.out.print(".");
                        break;
                }
            }
            System.out.println();
        }
    }

    public void clearGrid() {
        for (int i = 0; i < gridX; i++) {
            for (int j = 0; j < gridY; j++) {
                grid[i][j].isBlank();
            }
        }
    }

    public Grid randomlyFillGrid() {
        int state;
        for (int j = 0; j < gridY; j++) {
            for (int i = 0; i < gridX; i++) {
                state = 1 + (int) (Math.random() * 3);
                switch (state) {
                    case 1:
                        grid[i][j].setState(Cell.State.DEAD);
                        System.out.print(".");
                        break;
                    case 2:
                        grid[i][j].setState(Cell.State.DEAD);
                        System.out.print("*");
                        break;
                    case 3:
                        grid[i][j].setState(Cell.State.ALIVE);
                        System.out.print("0");
                        break;
                }
            }
            System.out.println();
        }
        return null;
    }

    public int getGridX() {
        return gridX;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    public void setCellStateInGrid(int x, int y, Cell.State state) {
        grid[x][y].setState(state);
    }

    public Cell.State getCellStateInGrid(int x, int y) {
        return grid[x][y].getState();
    }
}
