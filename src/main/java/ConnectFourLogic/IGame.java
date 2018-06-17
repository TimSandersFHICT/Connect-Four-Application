package ConnectFourLogic;

public interface IGame {
    boolean RegisterNewPlayer(IPlayer player);
    boolean checkStartGame();
    void tryMakeMove(int row);
    void SetupGame();
}
