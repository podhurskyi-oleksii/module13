package Methods;

import ToDos.Todo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

import static UtilityMethods.Utils.*;

public class UserToDos {
    public void getAllToDos(int userID) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_USERS + "/" + userID + "/todos"))
                .GET()
                .build();
        HttpResponse<String> response =
                CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        List<Todo> allUserTodos = GSON.fromJson(response.body(), TODO_TYPE);

        List<Todo> todos = allUserTodos.stream()
                .filter(t -> !t.isCompleted())
                .collect(Collectors.toList());

        System.out.println("All \"open\" Tasks of User-" + userID);
        System.out.println(GSON_PRETTY.toJson(todos));
    }
}
