package Methods;

import Comments.Post;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static UtilityMethods.Utils.*;

public class UserPosts {
    public Post getLastPost(int userID) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_USERS + "/" + userID + "/posts"))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        List<Post> userPosts = GSON.fromJson(response.body(), POST_TYPE);
        if (userPosts.size() >= 1)
            return userPosts.get(userPosts.size() - 1);
        return null;

    }
}
