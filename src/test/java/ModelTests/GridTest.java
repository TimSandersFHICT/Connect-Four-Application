package ModelTests;

import ConnectFourLogic.Cell;
import ConnectFourLogic.Enums.CellState;
import ConnectFourLogic.Grid;
import ConnectFourLogic.IPlayer;
import ConnectFourLogic.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {

    private Grid grid;

    @Before
    public void prepareTest() {
        grid = new Grid(7, 6);
    }

    @Test
    public void getCells(){
        Cell cell = grid.getCell(0,0);
        Cell[][] cells = grid.getCells();
        assertSame(cells[0][0], cell);
    }

    @Test
    public void getCellFilledCorrect() {
        grid.getCell(5, 5).setCellState(CellState.FILLED);
        Cell cell = grid.getCellFilled();
        assertNotNull(cell);
    }

    @Test
    public void getCellFilledIncorrect() {
        Cell cell = grid.getCellFilled();
        assertNull(cell);
    }

    @Test
    public void getCellX() {
        Cell cell = grid.getCell(5, 0);
        int x = grid.getCellX(cell);
        assertEquals(5, x);
    }

    @Test
    public void getCellY() {
        Cell cell = grid.getCell(5, 3);
        int y = grid.getCellY(cell);
        assertEquals(3, y);
    }

    @Test
    public void checkIfCellEmptyCorrect() {
        boolean bool = grid.checkIfCellEmpty(5, 5);
        assertTrue(bool);
    }

    @Test
    public void checkIfCellEmptyIncorrect() {
        grid.getCell(5, 5).setCellState(CellState.FILLED);
        boolean bool = grid.checkIfCellEmpty(5, 5);
        assertFalse(bool);
    }


}
