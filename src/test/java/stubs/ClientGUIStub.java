package stubs;

import ConnectFourLogic.*;
import ConnectFourLogic.Enums.CellState;
import ConnectFourWebSocketClient.IClientWebSocket;
import com.google.gson.JsonObject;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javax.websocket.Session;

public class ClientGUIStub implements IClientGUIStub{

    private Session session;
    public IPlayer player = new Player(session);
    public Grid grid;
    public IClientSocketStub client;

    public ClientGUIStub()
    {
        grid = new Grid(7,6);
    }


    public void tryMakeMove(int row)
    {
        Cell cell = grid.checkRow(row);
        sendCellFilled(cell.getX(), cell.getY());
    }

    public void makeMove(int x, int y)
    {

        Cell cell = grid.getCell(x,y);
        System.out.println(x + " " + y);
        cell.setCellState(CellState.FILLED);
    }

    public void sendCellFilled(int x, int y)
    {
        client = new ClientSocketStub(this);
        JsonObject json = new JsonObject();
        json.addProperty("cellFilled", true);
        json.addProperty("x", x);
        json.addProperty("y", y);
        client.onText(json.toString());
    }

    public void playerReady()
    {
        client = new ClientSocketStub(this);
        client.playerReady(true);
    }

    public void isPlayerReady(boolean isReady)
    {
        player.setReady(isReady);
    }



}
