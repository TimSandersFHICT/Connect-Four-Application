package stubs;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import java.io.IOException;

public class ClientSocketStub implements IClientSocketStub {

    private ServerSocketStub server;
    private IClientGUIStub application;

    public ClientSocketStub(IClientGUIStub application){
        server = new ServerSocketStub(this);
        this.application = application;
    }

    @OnOpen
    public void onWebSocketConnect() {

    }

    @Override
    public void onText(String message) {
        JsonObject json = new JsonParser().parse(message).getAsJsonObject();

        if (keyInJsonObject(json, "cellFilled")) {
            int x = json.get("x").getAsInt();
            int y = json.get("y").getAsInt();
            application.makeMove(x, y);
        }
        else if(keyInJsonObject(json, "ready"))
        {
            application.isPlayerReady(json.get("ready").getAsBoolean());
        }
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason){

    }

    @OnError
    public void onWebSocketError(Throwable cause){

    }

    public boolean keyInJsonObject(JsonObject json, String key) {
        try {
            return json.has(key);
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public void makeMove(int row)
    {
        JsonObject json = new JsonObject();
        json.addProperty("MakeMove", row);
        server.OnText(json.toString());
    }


    public void playerReady(boolean isReady)
    {
        JsonObject json = new JsonObject();
        json.addProperty("ready", isReady);
        server.OnText(json.toString());
    }

}
