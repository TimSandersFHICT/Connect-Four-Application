package ConnectFourLogic;


import ConnectFourLogic.Enums.CellState;

public class Cell {

    private CellState cellState;
    private int x;
    private int y;
    private IPlayer owner;

    public Cell(int x, int y) {
        this.cellState = cellState.EMPTY;
        this.x = x;
        this.y = y;
        this.owner = null;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setOwner(IPlayer owner) {
        this.owner = owner;
    }

    public IPlayer getOwner() {
        return owner;
    }

}
