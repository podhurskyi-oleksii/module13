package UtilityMethods;

import Methods.UserComments;
import Methods.UserPosts;
import Methods.UserServices;
import Methods.UserToDos;
import ToDos.Todo;
import User.User;
import Comments.Post;
import Comments.Comment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.util.List;

public class Utils {
    public static final HttpClient CLIENT = HttpClient.newHttpClient();
    public static final Type USER_TYPE = TypeToken
            .getParameterized(List.class, User.class)
            .getType();

    public static final Type POST_TYPE = TypeToken
            .getParameterized(List.class, Post.class)
            .getType();

    public static final Type COMMENT_TYPE = TypeToken
            .getParameterized(List.class, Comment.class)
            .getType();

    public static final Type TODO_TYPE = TypeToken
            .getParameterized(List.class, Todo.class)
            .getType();
    public static final String URL_USERS = "https://jsonplaceholder.typicode.com/users";
    public static final String URL_POSTS = "https://jsonplaceholder.typicode.com/posts";
    public static final Gson GSON = new Gson();
    public static final Gson GSON_PRETTY = new GsonBuilder().setPrettyPrinting().create();
    public static final UserServices SERVICES = new UserServices();
    public static final UserPosts POSTS = new UserPosts();
    public static final UserComments COMMENTS = new UserComments();
    public static final UserToDos TODOS = new UserToDos();

}
