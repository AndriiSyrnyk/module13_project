package Task1;

import java.io.IOException;

public class JsonplaceholderAPPTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(JsonplaceholderUtil.addNewUser());
        System.out.println(JsonplaceholderUtil.updateUser(3));
        System.out.println(JsonplaceholderUtil.deleteUser(9));
        System.out.println(JsonplaceholderUtil.getAllUsers()); // get all users
        System.out.println(JsonplaceholderUtil.getUserById(3)); // get user by id
        System.out.println(JsonplaceholderUtil.getUserByUsername("Karianne")); // get user by name
    }
}
