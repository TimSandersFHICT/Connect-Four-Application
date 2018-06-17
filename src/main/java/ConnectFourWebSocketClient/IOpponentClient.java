package ConnectFourWebSocketClient;

import ConnectFourClientApp.Controllers.IConnectFourController;

public interface IOpponentClient {

    void makeOpponentMove(int row);
    void opponentReady();
    void isPlayerReady(boolean isReady);
    void onTurn();
    void InvalidMoveMessage();
    void makeMove(int x, int y, boolean isPlayer);
    void showEndingMessage(boolean isWinner);
}
