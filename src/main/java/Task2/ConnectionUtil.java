package Task2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;

public class ConnectionUtil {
    private static String baseInputUri = "https://jsonplaceholder.typicode.com";

    public static void writeAllPostsCommentsToFile(int userId) throws IOException {
        int lastPostIdByUserId = getLastPostIdByUserId(userId);
        String allCommentsByPostId = getAllCommentsByPostId(lastPostIdByUserId);
        String outputFileName = "C:\\Users\\38098\\IdeaProjects\\Module13Project\\src\\main\\resources\\" +
                "user-" + userId + "-post-" + lastPostIdByUserId +  "-comments.json";

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileName))) {
            bufferedWriter.write(allCommentsByPostId);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    private static int getLastPostIdByUserId(int userId) throws IOException {
        String response = Jsoup.connect(baseInputUri + "/users/" + userId + "/posts")
                .ignoreContentType(true)
                .get()
                .body()
                .text();

        Type type = TypeToken
                .getParameterized(List.class, Post.class)
                .getType();

        List<Post> allPosts = new Gson().fromJson(response, type);

        int lastPostId = allPosts.stream()
                .max(Comparator.comparing(Post::getId))
                .get()
                .getId();

        return lastPostId;
    }

    private static String getAllCommentsByPostId(int postId) throws IOException {
        String response = Jsoup.connect(baseInputUri + "/posts/" + postId + "/comments")
                .ignoreContentType(true)
                .get()
                .body()
                .text();

        return response;
    }
}
