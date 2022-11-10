package Task1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class JsonplaceholderUtil {
    public static String baseUri = "https://jsonplaceholder.typicode.com/users";
    public static String updateUri = "https://jsonplaceholder.typicode.com/posts";
    public static HttpClient CLIENT = HttpClient.newHttpClient();
    public static String addNewUser() throws IOException, InterruptedException {
        User newUser = createDefaultUser();
        Gson gsonUser = new Gson();
        String requestBody = gsonUser.toJson(newUser);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUri))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        return response.toString();
    }

    public static String updateUser(int userId) throws IOException, InterruptedException {
        User newUser = createDefaultUser();
        Gson gsonUser = new Gson();
        String user = gsonUser.toJson(newUser);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(updateUri + "/" + userId))
                .PUT(HttpRequest.BodyPublishers.ofString(user))
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        return response.toString();
    }

    public static String deleteUser(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUri + "/" + userId))
                .DELETE()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        return response.toString();
    }

    public static List<User> getAllUsers() throws IOException {
        String response = Jsoup.connect(baseUri)
                .ignoreContentType(true)
                .get()
                .body()
                .text();

        Type type = TypeToken
                .getParameterized(List.class, User.class)
                .getType();

        List<User> users = new Gson().fromJson(response, type);
        return users;
    }
    public static User getUserById(int id) throws IOException {
        String response = Jsoup.connect(baseUri + "/" + id)
                .ignoreContentType(true)
                .get()
                .body()
                .text();
        User user = new Gson().fromJson(response, User.class);
        return user;
    }

    public static User getUserByUsername(String userName) throws IOException {
        String response = Jsoup.connect(baseUri + "?username=" + userName)
                .ignoreContentType(true)
                .get()
                .body()
                .text();

        Type type = TypeToken
                .getParameterized(List.class, User.class)
                .getType();

        List<User> users = new Gson().fromJson(response, type);
        return users.get(0);
    }

    private static User createDefaultUser() {
        return new User(11, "andrii syrnyk",
                "androso", "androso@ukr.net",
                new Address("15 Kvitnya str.", "","Ternopil","46016", new Geo(49.56,25.65 )),
                "0984634171", "", new Company("","","") );
    }
}
