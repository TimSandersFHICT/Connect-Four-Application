package ConnectFourWebSocketClient;

import ConnectFourClientApp.Controllers.IConnectFourController;

import javax.websocket.Session;

public interface IPlayerClient {
     void makePlayerMove(int row);
     void setSocket(IClientWebSocket socket);
     void playerReady();
     void setConnectFourController(IConnectFourController connectFourController);
     boolean isPlayerRegistered(String username, String password);
     boolean isOnTurn();

}
