package ConnectFourWebSocketClient;

import ConnectFourLogic.Game;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.SynchronousQueue;

@ClientEndpoint
public class ClientWebSocket implements IClientWebSocket{

    // Address for the client to listen to
    // (Has to match the settings for the server)
    private static final URI uri = URI.create("ws://localhost:8096/ConnectFour/");
    private Session session;
    private IOpponentClient iOpponentClient;



    public ClientWebSocket(IOpponentClient iOpponentClient) {

        this.iOpponentClient = iOpponentClient;

        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            //Connect to the server
            session = container.connectToServer(this, uri);

        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }

    public void playerReady(boolean isReady){
        //Stuur naar server een ready message
        try {
            JsonObject json = new JsonObject();
            json.addProperty("ready", isReady);
            session.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeMove(int row)
    {
        try {
            JsonObject json = new JsonObject();
            json.addProperty("makeMove", row);
            session.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







    @OnOpen
    public void onWebSocketConnect() {
        System.out.println("[Connected]");
    }

    @OnMessage
    public void onWebSocketMessage(String message) {
        //Was oude code
        System.out.println("[Message:] " + message);
        JsonObject json = new JsonParser().parse(message).getAsJsonObject();
        if(keyInJson(json, "ready"))
        {
            iOpponentClient.isPlayerReady(json.get("ready").getAsBoolean());
        }
        else if(keyInJson(json, "onTurn"))
        {
            this.iOpponentClient.onTurn();
        }
        else if(keyInJson(json, "invalidMove"))
        {
            iOpponentClient.InvalidMoveMessage();
        }
        else if(keyInJson(json, "cellFilled"))
        {
           int x = json.get("x").getAsInt();
           int y = json.get("y").getAsInt();
           boolean isPlayer = json.get("isPlayer").getAsBoolean();
           iOpponentClient.makeMove(x,y, isPlayer);
        }
        else if(keyInJson(json, "isWinner"))
        {
            boolean isWinner = json.get("isWinner").getAsBoolean();
            iOpponentClient.showEndingMessage(isWinner);
        }


    }
    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        System.out.println("[Closed]: " + reason);
    }

    @OnError
    public void onWebSocketError(Throwable cause) {
        cause.printStackTrace(System.err);
    }

    private boolean keyInJson(JsonObject json, String key) {
        try {
            return json.has(key);

        } catch (NullPointerException ex) {
            return false;
        }
    }
}
