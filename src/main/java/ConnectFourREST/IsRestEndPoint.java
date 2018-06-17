package ConnectFourREST;

public interface IsRestEndPoint {

    int loginPlayer(String username, String password);

    boolean registerPlayer(String username, String password);
}
