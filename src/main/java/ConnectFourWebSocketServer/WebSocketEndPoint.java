package ConnectFourWebSocketServer;

import ConnectFourLogic.Game;
import ConnectFourLogic.IGame;
import ConnectFourLogic.IPlayer;
import ConnectFourLogic.Player;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint(value = "/ConnectFour/")
public class WebSocketEndPoint {

    private static IGame game;

    @OnOpen
    public void openConnection(Session session) throws IOException {
        if(game == null)
        {
            game = new Game();
        }

    }

    @OnMessage
    public void onClientText(String message, Session session)
    {
        System.out.println("[Message:] " + message);
        JsonObject json = new JsonParser().parse(message).getAsJsonObject();
        if(keyInJson(json, "ready"))
        {
            tryRegisterPlayer(session);
        }
        else if (keyInJson(json, "makeMove"))
        {
            int row = json.get("makeMove").getAsInt();
            game.tryMakeMove(row);
        }

    }

    private boolean keyInJson(JsonObject json, String key) {
        try {
            return json.has(key);

        } catch (NullPointerException ex) {
            return false;
        }
    }

    private void tryRegisterPlayer(Session session)
    {
        try {
            IPlayer player = new Player(session);
            boolean canPlayerRegister = game.RegisterNewPlayer(player);
            System.out.println(canPlayerRegister);
            JsonObject json = new JsonObject();
            json.addProperty("ready", canPlayerRegister);
            session.getBasicRemote().sendText(json.toString());
            game.checkStartGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }









}
