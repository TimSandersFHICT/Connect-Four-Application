package ModelTests;


import ConnectFourLogic.Cell;
import ConnectFourLogic.Enums.CellState;
import ConnectFourLogic.IPlayer;
import ConnectFourLogic.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CellTest {
    private Cell cell;

    @Before
    public void prepareTest() {
        cell = new Cell(1, 1);
    }

    @Test
    public void checkDefaultOwnerTest()
    {
        //Owner needs to be null if its not filled
        Assert.assertNull(cell.getOwner());
    }

    @Test
    public void checkDefaultCellStateTest()
    {
        //CellState needs to be empty by default
        Assert.assertTrue(CellState.EMPTY == cell.getCellState());
    }

    @Test
    public void setCellStateTest()
    {
        cell.setCellState(CellState.FILLED);
        Assert.assertTrue(CellState.FILLED == cell.getCellState());
    }

    @Test
    public void getCellX()
    {
        Assert.assertTrue(1 == cell.getX());
    }

    @Test
    public void getCellY()
    {
        Assert.assertTrue(1 == cell.getY());
    }






}
