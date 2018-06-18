package IntegrationTests;

import ConnectFourClientApp.Controllers.IConnectFourController;
import ConnectFourLogic.Cell;
import ConnectFourLogic.Enums.CellState;
import ConnectFourWebSocketClient.IClientWebSocket;
import org.junit.Assert;
import org.junit.Test;
import stubs.ClientGUIStub;
import stubs.ClientSocketStub;
import stubs.IClientSocketStub;

public class MakeMoveResultTest {

    @Test
    public void MakeMoveTest()
    {
        ClientGUIStub application = new ClientGUIStub();
        IClientSocketStub client = new ClientSocketStub(application);
        application.tryMakeMove(3);

        Assert.assertFalse(application.grid.checkIfCellEmpty(3,0));
    }
}
