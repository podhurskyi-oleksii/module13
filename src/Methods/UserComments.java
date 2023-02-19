package Methods;

import Comments.Comment;
import Comments.Post;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static UtilityMethods.Utils.*;

public class UserComments {
    public void getUserComments(int userID) throws IOException, InterruptedException {
        Post post = POSTS.getLastPost(userID);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_POSTS + "/" + post.getId() + "/comments"))
                .GET()
                .build();
        HttpResponse<String> response =
                CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        List<Comment> comments = GSON.fromJson(response.body(), COMMENT_TYPE);

        String fileName = String.format("docs/user-%d-post-%d-comments.json", post.getUserId(), post.getId());
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(GSON_PRETTY.toJson(comments));
        fileWriter.flush();
        fileWriter.close();

        System.out.println("All comments on the last post(ID) " + POSTS.getLastPost(userID).getId() + " of User-" + userID);
        System.out.println(GSON_PRETTY.toJson(comments));
    }
}
