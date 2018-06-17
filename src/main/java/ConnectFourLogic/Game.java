package ConnectFourLogic;

import ConnectFourClientApp.ConnectFour;
import ConnectFourClientApp.Controllers.ConnectFourController;
import ConnectFourLogic.Enums.CellState;
import com.sun.org.apache.bcel.internal.generic.FADD;

import java.util.ArrayList;
import java.util.Random;


public class Game implements IGame{

    public IPlayer[] players;
    public Grid grid;
    private boolean playerOnTurn;

    public Game() {
       SetupGame();
    }

    public boolean RegisterNewPlayer(IPlayer player)
    {
        if(players[0] == null)
        {
            System.out.println("New Player1 Registered");
            players[0] = player;
            return true;
        }
        else if(players[1] == null)
        {
            System.out.println("New Player2 Registered");
            players[1] = player;
            return true;
        }
        System.out.println("The game is full");
        return false;
    }

    public boolean checkStartGame()
    {
        if(players[1] != null)
        {
            IPlayer player = getPlayerTurn();
            System.out.println("sending player turn");
            player.sendReadyMessage();
        }
        else
        {
            return false;
        }
        return false;
    }

    public IPlayer getPlayerTurn()
    {
        return playerOnTurn ? players[0] : players[1];
    }

    public void tryMakeMove(int row) {
        IPlayer player = getPlayerTurn();
        Cell cell = grid.checkRow(row);
        if(cell == null)
        {

            player.sendInvalidMoveMessage();
        }
        else
        {
            cell.setCellState(CellState.FILLED);
            cell.setOwner(player);
            IPlayer opponent = getOpponentTurn();
            player.sendCellFilled(cell.getX(), cell.getY(), true);
            opponent.sendCellFilled(cell.getX(), cell.getY(), false);
            IPlayer winner = grid.checkWin();
            if(winner != null)
            {
                player.setEndingMessage(true);
                opponent.setEndingMessage(false);
                SetupGame();
            }
            else
            {
                playerOnTurn = !playerOnTurn;
                opponent.sendReadyMessage();
            }

        }
    }

    public IPlayer getOpponentTurn()
    {
        return playerOnTurn ? players[1] : players[0];
    }

    public void SetupGame()
    {
        players = new IPlayer[2];
        //select a random turn
        grid = new Grid(7,6);
        playerOnTurn = new Random().nextBoolean();
    }













}
