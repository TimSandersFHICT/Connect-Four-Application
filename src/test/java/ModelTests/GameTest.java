package ModelTests;

import ConnectFourLogic.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.websocket.Session;

public class GameTest {

    IGame game;
    private IPlayer p1;
    private IPlayer p2;


    @Before
    public void prepareTest(){

        game = new Game();
        //Setup Game makes 2 IPlayers for the game which are added to the players list
        game.SetupGame();
    }

    @Test
    public void registerPlayerTest()
    {
      boolean valueP1 = game.RegisterNewPlayer(p1);
      Assert.assertTrue(valueP1);
    }

    @Test
    public void tryMakeMoveTest()
    {
        game.RegisterNewPlayer(p1);
        p1.isReady();
        Grid grid = p1.getGrid();
        Assert.assertFalse(grid.checkIfCellEmpty(5,5));
    }
}
