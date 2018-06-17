package ConnectFourWebSocketClient;

import ConnectFourClientApp.Controllers.IConnectFourController;
import ConnectFourREST.IsRestEndPoint;
import ConnectFourREST.RestEndPoint;
import com.google.gson.JsonObject;

import javax.websocket.Session;
import java.io.IOException;

public class Client implements IOpponentClient, IPlayerClient{


    private IConnectFourController connectFourController;
    private IClientWebSocket socket;
    private int id;

    public boolean isOnTurn() {
        return onTurn;
    }

    private boolean onTurn;

    public Client()
    {
        onTurn = false;
        //webSocket = new ClientWebSocket(this);
    }

    public void makePlayerMove(int row)
    {
        socket.makeMove(row);
    }

    public void makeOpponentMove(int row)
    {

    }

    @Override
    public void opponentReady() {

    }

    public void setConnectFourController(IConnectFourController connectFourController)
    {
        this.connectFourController = connectFourController;
    }

    public void setSocket(IClientWebSocket socket)
    {
        this.socket = socket;
    }

    public void playerReady()
    {
        socket.playerReady(true);
    }

    public void opponentReady(boolean isReady)
    {
        connectFourController.showReadyMessage(isReady);
    }

    public boolean isPlayerRegistered(String username, String password)
    {
        try{
            RestEndPoint restEndPoint = new RestEndPoint();
            if(restEndPoint.loginPlayer(username, password) >= 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.out.println(id);
        }
        return false;
    }

    public void isPlayerReady(boolean isReady)
    {
        connectFourController.showReadyMessage(isReady);
    }

    public void onTurn()
    {
        onTurn = true;
        System.out.println("player is on turn bby");
    }

    public void InvalidMoveMessage()
    {
        connectFourController.ShowInvalidMoveMessage();
    }

    public void makeMove(int x, int y, boolean isPlayer)
    {
        onTurn = false;
        System.out.println("you cannot do shit");
        connectFourController.MakeMove(x,y, isPlayer);
    }

    public void showEndingMessage(boolean isWinner)
    {
        connectFourController.showEndingMessage(isWinner);
    }







}
