package Methods;

import User.User;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static UtilityMethods.Utils.*;

public class UserServices {
    public void addNewUser(User user) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_USERS))
                .header("Content-type", "application/json; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString(GSON.toJson(user)))
                .build();

        HttpResponse<String> response =
                CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
        System.out.println("New User Has been added");

    }

    public void updateUser(User user) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_USERS + "/" + user.getId()))
                .header("Content-type", "application/json; charset=UTF-8")
                .PUT(HttpRequest.BodyPublishers.ofString(new Gson().toJson(user)))
                .build();

        HttpResponse<String> response =
                CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
        System.out.println("User has been Updated");

    }

    public void deleteUser(User user) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_USERS + "/" + user.getId()))
                .DELETE()
                .build();

        HttpResponse<String> response =
                CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            System.out.println("User with ID-" + user.getId() + " has been deleted");
        }
    }

    public List<User> getAllUser() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_USERS))
                .GET()
                .build();

        HttpResponse<String> response =
                CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(response.body(), USER_TYPE);
    }

    public User getUserById(int userID) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_USERS + "/" + userID))
                .GET()
                .build();

        HttpResponse<String> response =
                CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(response.body(), User.class);
    }

    public User getUserByUsername(String userName) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_USERS + "?username=" + userName))
                .GET()
                .build();

        HttpResponse<String> response =
                CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        List<User> users = GSON.fromJson(response.body(), USER_TYPE);
        if (users.size() >= 1) {
            return users.get(0);
        }
        return null;
    }
}
