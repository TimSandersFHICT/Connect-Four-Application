package ConnectFourLogic;


import com.google.gson.JsonObject;

import javax.websocket.Session;
import java.io.IOException;

public class Player implements IPlayer{

    private String password;
    private Session session;
    private Grid grid;
    private boolean ready;

    public Player(Session session) {
        this.session = session;
        this.grid = new Grid(7,6);
    }


    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Session getSession() {
        return session;
    }


    @Override
    public Grid getGrid() {
        return grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public boolean isReady() {
        return ready;
    }

    @Override
    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void sendReadyMessage()
    {
        try {
            JsonObject json = new JsonObject();
            json.addProperty("onTurn", true);
            session.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendInvalidMoveMessage()
    {
        try {
            JsonObject json = new JsonObject();
            json.addProperty("invalidMove", true);
            session.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCellFilled(int x, int y, boolean isPlayer)
    {
        try {
            JsonObject json = new JsonObject();
            json.addProperty("cellFilled", true);
            json.addProperty("x", x);
            json.addProperty("y", y);
            json.addProperty("isPlayer", isPlayer);
            session.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setEndingMessage(boolean isWinner)
    {
        try {
            JsonObject json = new JsonObject();
            json.addProperty("isWinner", isWinner);
            session.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
