package ConnectFourWebSocketServer;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.websocket.server.ServerContainer;


public class ServerWebSocket {
    public static void main(String[] args) {


        int port = 8096;

        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        try{
            ServerContainer container = WebSocketServerContainerInitializer.configureContext(context);
            container.addEndpoint(WebSocketEndPoint.class);
            server.start();
            server.join();
        }
        catch(Throwable t){
            t.printStackTrace(System.err);
        }
    }
}
