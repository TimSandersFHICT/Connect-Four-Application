package ConnectFourClientApp.Controllers;

import javafx.scene.input.MouseEvent;

public interface IConnectFourController {
    void showReadyMessage(boolean isReady);
    void circlePlayAreaMousePressed(MouseEvent event, int x, int y);
    void ShowInvalidMoveMessage();
    void MakeMove(int x, int y, boolean isPlayer);
    void showEndingMessage(boolean isWinner);
}
