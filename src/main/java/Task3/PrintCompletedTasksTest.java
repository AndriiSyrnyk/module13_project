package Task3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class PrintCompletedTasksTest {
    public static void main(String[] args) throws IOException {
        printAllCompletedTasks(2);
    }

    private static void printAllCompletedTasks(int userId) throws IOException {
        String baseInputUri = "https://jsonplaceholder.typicode.com";

        String response = Jsoup.connect(baseInputUri + "/users/" + userId + "/todos")
                .ignoreContentType(true)
                .get()
                .body()
                .text();

        Type type = TypeToken
                .getParameterized(List.class, Task.class)
                .getType();

        List<Task> allUserTasks = new Gson().fromJson(response, type);

        List<Task> completedTasks = allUserTasks.stream()
                .filter(Task::isCompleted)
                .collect(Collectors.toList());

        System.out.println(completedTasks);
    }
}
