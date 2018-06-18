package stubs;

import ConnectFourLogic.Game;
import ConnectFourLogic.IGame;
import ConnectFourLogic.IPlayer;
import ConnectFourLogic.Player;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.websocket.Session;

public class ServerSocketStub {

    IClientSocketStub client;
    private Session session;
    private IGame game;
    private ClientGUIStub GUI = new ClientGUIStub();

    public ServerSocketStub(ClientSocketStub clientSocket){
        this.client = clientSocket;
    }

    public void OnText(String message) {
        JsonObject json = new JsonParser().parse(message).getAsJsonObject();

        if (keyInJsonObject(json, "MakeMove")) {
            int row = json.get("MakeMove").getAsInt();
            //TODO ADD GAME
            GUI.tryMakeMove(row);
        }
        else if(keyInJsonObject(json,"ready"))
        {
            game = new Game();
            IPlayer player = new Player(session);
            boolean canPlayerRegister = game.RegisterNewPlayer(player);
            JsonObject jsonReady = new JsonObject();
            jsonReady.addProperty("ready", canPlayerRegister);
            client.onText(jsonReady.toString());
        }
    }

    private boolean keyInJsonObject(JsonObject json, String key) {
        try {
            return json.has(key);
        } catch (NullPointerException ex) {
            return false;
        }
    }


}
