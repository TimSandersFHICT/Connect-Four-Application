package ConnectFourLogic;

import ConnectFourLogic.Enums.CellState;

public class Grid {

    private Cell[][] cells;

    public Grid(int width, int height) {
        this.cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell(i,j);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public Cell getCell(int posX, int posY) {
        return cells[posX][posY];
    }

    public Cell checkRow(int x)
    {
        for(Cell cell : cells[x])
        {
            if(cell.getCellState() != CellState.FILLED)
            {
                return cell;
            }
        }
        return null;
    }



    public IPlayer checkWin(){
        IPlayer player;
        player = checkVertical();
        if(player != null){
            return player;
        }
        player = checkHorizontal();
        if(player != null)
        {
            return player;
        }
        player = checkMainDiagonal();
        if(player != null)
        {
            return player;
        }
        player = checkCounterDiagonal();
        if(player != null)
        {
            return player;
        }
        return null;
    }

    private IPlayer checkHorizontal()
    {
        for(int j = 0; j < cells[0].length; j++)
        {
            IPlayer owner = null;
            int length = 0;
            for(int i = 0; i < cells.length; i++)
            {
                if(cells[i][j].getOwner() == owner)
                {
                    length++;
                }
                else
                {
                    owner = cells[i][j].getOwner();
                    length = 1;
                }
                if(owner == null)
                {
                    length = 0;
                }
                if(length == 4)
                {
                    return owner;
                }
            }
        }
        return null;
    }

    private IPlayer checkVertical()
    {
        for(int i = 0; i < cells.length; i++)
        {
            IPlayer owner = null;
            int length = 0;
            for(int j = 0; j < cells[i].length; j++)
            {
                if(cells[i][j].getOwner() == owner)
                {
                    length++;
                }
                else
                {
                    owner = cells[i][j].getOwner();
                    length = 1;
                }
                if(owner == null)
                {
                    length = 0;
                }
                if(length == 4)
                {
                    return owner;
                }
            }
        }
        return null;
    }

    private IPlayer checkMainDiagonal()
    {
        for (int row = 0; row < cells.length - 3; row++)
        {
            for (int col = 0; col < cells[row].length - 3; col++)
            {
                IPlayer player = cells[row][col].getOwner();
                if (player == cells[row + 1][col + 1].getOwner() &&
                        player == cells[row + 2][col + 2].getOwner() &&
                        player == cells[row + 3][col + 3].getOwner() && player != null)
                {
                    System.out.println("fck yeah we did it");
                    return player;
                }
            }
        }
        return null;
    }

    private IPlayer checkCounterDiagonal()
    {
        for (int row = 0; row < cells.length - 3; row++)
        {
            for (int col = 3; col < cells[row].length; col++)
            {
                IPlayer player = cells[row][col].getOwner();
                if (player == cells[row + 1][col - 1].getOwner() &&
                        player == cells[row + 2][col - 2].getOwner() &&
                        player == cells[row + 3][col - 3].getOwner() && player != null)
                {
                    System.out.println("fck yeah we did it");
                    return player;
                }
            }
        }
        return null;
    }






}
