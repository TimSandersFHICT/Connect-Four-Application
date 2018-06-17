package ConnectFourClientApp;

import ConnectFourClientApp.Controllers.ConnectFourController;
import ConnectFourWebSocketClient.*;
import ConnectFourWebSocketServer.ServerWebSocket;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;

public class ConnectFour extends Application   {

    private ConnectFourController connectFourController;
    // Play area, a 7 x 6 grid where the player's chips are placed
    private Circle playArea;
    private Circle circle;
    // Squares for the play area
    public static Circle[][] circlesPlayArea;

    // Constants to define number of circles horizontal and vertical
    private final int NRCIRCLESHORIZONTAL = 7;
    private final int NRCIRCLESVERTICAL = 6;
    private final int BORDERSIZE = 10; // Size of borders in pixels
    private final int OFFSET = 10000;
    private final int CENTERX = 50;
    private final int CENTERY = 50;
    private final int RADIUS = 25;


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/ConnectFourGUI.fxml"));
        IPlayerClient client = new Client();
        /*
                USE REFLECTION FOR DEPENDENCY INJECTION:
                INJECT EVERY CONTROLLER INSTANCE, WHICH IS CREATED DYNAMICALLY WHILE LOADING THE FXML,
                WITH THE SAME GAME CLIENT INSTANCE
         */
        loader.setControllerFactory((Class<?> type) -> {
            try {
                // look for constructor taking MyService as a parameter
                for (Constructor<?> c : type.getConstructors()) {
                    if (c.getParameterCount() == 1 && c.getParameterTypes()[0]== IPlayerClient.class) {
                        return c.newInstance(client);
                    }
                }
                // didn't find appropriate constructor, just use default constructor:
                return type.newInstance();
            } catch (Exception exc) {
                System.out.println(exc);
                return null;
            }
        });

        Parent root = loader.load();
        connectFourController = loader.getController();
        // Create the scene and add the grid pane
        Group group = new Group();

        // Play area, a 7 x 6 grid where the opponent's chips are placed
        playArea = new Circle(CENTERX,CENTERY,RADIUS, Color.WHITE);
        playArea.setStroke(Color.BLACK);
        playArea.setStrokeWidth(3);

        //Add FXML File to the group
        group.getChildren().add(root);

        // Create 10 x 10 squares for the target area
        circlesPlayArea = new Circle[NRCIRCLESHORIZONTAL][NRCIRCLESVERTICAL];
        for (int i = NRCIRCLESHORIZONTAL - 1; i >= 0; i--) {
            for (int j = NRCIRCLESVERTICAL - 1; j >= 0 ; j--) {
                //Offset of X
                double x = playArea.getCenterX() + i * 55;
                //Offset of Y
                double y = playArea.getCenterY() + ((NRCIRCLESVERTICAL-1)*55) - j * 55;

                circle = new Circle(x,y,RADIUS, Color.WHITE);
                circle.setStroke(Color.BLACK);
                circle.setStrokeWidth(3);



                final int xPos = i;
                final int yPos = j;

                circle.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                connectFourController.circlePlayAreaMousePressed(event, xPos, yPos); }
                        });

                circlesPlayArea[i][j] = circle;
                group.getChildren().add(circle);
            }
        }





        //Add the playArea to the group
        //group.getChildren().add(playArea);

        primaryStage.setTitle("Connect Four");

        //Make a new scene with the group
        primaryStage.setScene(new Scene(group, 595, 369));
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
