package IntegrationTests;

import org.junit.Assert;
import org.junit.Test;
import stubs.ClientGUIStub;
import stubs.ClientSocketStub;
import stubs.IClientSocketStub;

public class SetPlayerReadyTest {

    @Test
    public void SetPlayerReady()
    {
        ClientGUIStub application = new ClientGUIStub();
        IClientSocketStub client = new ClientSocketStub(application);
        application.playerReady();
        Assert.assertTrue(application.player.isReady());
    }
}
