package stubs;

import com.google.gson.JsonObject;

public interface IClientSocketStub {
    void onText(String message);
    boolean keyInJsonObject(JsonObject json, String key);
    void makeMove(int row);
    void playerReady(boolean isReady);
}
