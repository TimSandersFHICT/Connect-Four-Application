package ConnectFourLogic;

import javax.websocket.Session;

public interface IPlayer {

    String getPassword();

    void setPassword(String password);

    Session getSession();

    Grid getGrid();

    void setGrid(Grid grid);

    boolean isReady();

    void setReady(boolean ready);

    void sendReadyMessage();

    void sendInvalidMoveMessage();

    void sendCellFilled(int x, int y, boolean isPlayer);

    void setEndingMessage(boolean isWinner);
}
