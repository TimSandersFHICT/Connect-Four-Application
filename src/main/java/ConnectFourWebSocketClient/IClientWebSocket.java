package ConnectFourWebSocketClient;

import javax.websocket.CloseReason;
import javax.websocket.Session;

public interface IClientWebSocket {

    void playerReady(boolean isReady);
    void onWebSocketConnect();
    void onWebSocketMessage(String message);
    void onWebSocketClose(CloseReason reason);
    void onWebSocketError(Throwable cause);
    void makeMove(int row);
}
