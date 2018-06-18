package ModelTests;


import ConnectFourLogic.Grid;
import ConnectFourLogic.IPlayer;
import ConnectFourLogic.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.websocket.Session;

public class PlayerTest {

    private IPlayer player;
    private Session session;

    @Before
    public void prepareTest() {
        player = new Player(session);
    }

    @Test
    public void setReadyTest(){
        player.setReady(true);
        Assert.assertTrue(player.isReady());
    }

    @Test
    public void sendCellFilled(){
        Grid grid = player.getGrid();
        Assert.assertTrue( grid.checkIfCellEmpty(1,1));
    }

}
