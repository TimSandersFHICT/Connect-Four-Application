package ConnectFourREST;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestEndPoint implements IsRestEndPoint {

    private final String REST_API_URL = "http://localhost:9998/api/";

    @Override
    public int loginPlayer(String username, String password) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(REST_API_URL);

        String response = target.path("loginPlayer/").path(username).path(password).request().get(String.class);
        JsonObject result =  new JsonParser().parse(response).getAsJsonObject();

        int id = result.get("id").getAsInt();

        //register player




        return id;
    }

    @Override
    public boolean registerPlayer(String username, String password) {
        Form form = new Form();
        form.param("username", username);
        form.param("password", password);

        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(REST_API_URL);

        Response response = target.path("register").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);

        return response.getStatus() < 400;
    }
}
