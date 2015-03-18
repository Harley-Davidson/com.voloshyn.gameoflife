package objects;

/**
 * Created by Max on 18.03.2015.
 */
public class Cell {
    public final int column;
    public final int row;

    public int amountOfNeighbours;

    public Cell(int column, int row, int amountOfNeighbours) {
        this.column = column;
        this.row = row;
        this.amountOfNeighbours = 0;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "column=" + column +
                ", row=" + row +
                ", amountOfNeighbours=" + amountOfNeighbours +
                '}';
    }

    public boolean equals(Cell cell){
        return row == cell.row && column == cell.column;
    }
}
