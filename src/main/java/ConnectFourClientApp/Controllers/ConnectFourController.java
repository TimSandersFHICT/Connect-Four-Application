package ConnectFourClientApp.Controllers;

import ConnectFourClientApp.ConnectFour;
import ConnectFourLogic.Player;
import ConnectFourREST.RestEndPoint;
import ConnectFourWebSocketClient.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ConnectFourController implements IConnectFourController{


    public TextField usernameRegisterField;
    public TextField passwordRegisterField;
    public TextField usernameLoginField;
    public TextField passwordLoginField;
    public Button btnLogin;
    public Button btnRegister;
    public Button btnReadyToPlay;
    public String loggedInUser;
    private IPlayerClient playerClient;
    private int x;
    private int y;


    public ConnectFourController(IPlayerClient client)
    {
        this.playerClient = client;
    }


    @FXML
    public boolean Login()
    {
        this.playerClient.setConnectFourController(this);
        String username = usernameLoginField.getText();
        String password = passwordLoginField.getText();
        if(username == "" || password == "")
        {
            showMessage("Username or Password is not filled in");
            return false;
        }
        else
        {
            if (playerClient.isPlayerRegistered(username, password)){

                loggedInUser = username;
                usernameRegisterField.setDisable(true);
                passwordRegisterField.setDisable(true);
                btnRegister.setDisable(true);
                usernameLoginField.setDisable(true);
                passwordLoginField.clear();
                passwordLoginField.setDisable(true);
                btnLogin.setDisable(true);
                btnReadyToPlay.setDisable(false);
                showMessage("You are now logged in");
                return true;
            }
        }
        return false;
    }

    @FXML
    public void Register()
    {
        String username = usernameRegisterField.getText();
        String password = passwordRegisterField.getText();

        if(username == "" || password == "")
        {
            showMessage("Username or password is not filled in");
        }
        else
        {
            RestEndPoint restEndPoint = new RestEndPoint();
            restEndPoint.registerPlayer(username, password);
            usernameRegisterField.setDisable(true);
            passwordRegisterField.clear();
            passwordRegisterField.setDisable(true);
            btnRegister.setDisable(true);
            showMessage("Welcome " + username + " you are now registered");
        }
    }

    @FXML
    public void setPlayerReady()
    {
        btnReadyToPlay.setDisable(true);
        playerClient.setSocket(new ClientWebSocket(((Client) playerClient)));
        playerClient.playerReady();
    }

    /**
     * Show an alert message.
     * The message will disappear when the user presses ok.
     */
    private void showMessage(final String message) {
        // Use Platform.runLater() to ensure that code concerning
        // the Alert message is executed by the JavaFX Application Thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Connect Four");
                alert.setHeaderText("Message for " + loggedInUser);
                alert.setContentText(message);
                alert.showAndWait();
            }
        });
    }

    public void showReadyMessage(boolean isReady)
    {
        if(isReady)
        {
            showMessage("You are now ready");
        }
        else
        {
            showMessage("Someone is already playing, you have to wait until they are done.");
            btnReadyToPlay.setDisable(false);
        }
    }


    public void circlePlayAreaMousePressed(MouseEvent event, int x, int y) {
        if (playerClient.isOnTurn())
        {
            System.out.println("Goed zo je klikt op een knop met positie: X = "  + x +  " Y = " + y );
            playerClient.makePlayerMove(x);
        }
        else
        {
            System.out.println("Je bent nog niet klaar om te spelen");
        }

    }

    public void ShowInvalidMoveMessage()
    {
        showMessage("You can not make a move on this cell");
    }

    public void MakeMove(int x, int y, boolean isPlayer)
    {
        if(isPlayer)
        {
            ConnectFour.circlesPlayArea[x][y].setFill(Color.RED);
        }
        else
        {
            ConnectFour.circlesPlayArea[x][y].setFill(Color.BLUE);
        }
    }

    public void showEndingMessage(boolean isWinner)
    {
        if(isWinner)
        {
            showMessage("Je hebt gewonnen!");
        }
        else
        {
            showMessage("Je hebt verloren");
        }
        for(int i = 0; i < ConnectFour.circlesPlayArea.length; i++)
        {
            for(int j = 0; j < ConnectFour.circlesPlayArea[i].length; j++)
            {
                ConnectFour.circlesPlayArea[i][j].setFill(Color.WHITE);
            }
        }

        btnReadyToPlay.setDisable(false);
    }






}
