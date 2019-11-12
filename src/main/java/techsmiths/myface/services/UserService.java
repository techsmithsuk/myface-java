package techsmiths.myface.services;

import org.springframework.stereotype.Service;
import techsmiths.myface.models.dbmodels.User;

import java.util.List;

@Service
public class UserService extends DatabaseService {

    public List<User> getAllUsers(int limit, int offset) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users LIMIT :limit OFFSET :offset")
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .mapToBean(User.class)
                        .list()
        );
    }

    public User getUser(String username) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE username = :username")
                        .bind("username", username)
                        .mapToBean(User.class)
                        .one()
        );
    }

    public void updateUser(String username, User user) {
        jdbi.withHandle(handle ->
                handle.createUpdate(
                            "UPDATE users SET " +
                                    "username = :newUsername" +
                                    "email = :email" +
                                    "first_name = :firstName" +
                                    "last_name = :lastName" +
                                    "profile_image = :profileImage" +
                                    "banner_image = :bannerImage" +
                            "WHERE username = :oldUsername")
                        .bind("newUsername", user.getUsername())
                        .bind("email", user.getEmail())
                        .bind("firstName", user.getFirstName())
                        .bind("lastName", user.getLastName())
                        .bind("profileImage", user.getProfileImage())
                        .bind("bannerImage", user.getBannerImage())
                        .bind("oldUsername", username)
                        .execute()
        );
    }

    public void createUser(User user) {
        jdbi.withHandle(handle ->
                handle.createUpdate(
                        "INSERT INTO users " +
                                "(username, email, first_name, last_name, profile_image, banner_image) " +
                                "VALUES " +
                                "(:username, :email, :firstName, :lastName, :profileImage, :bannerImage)")
                        .bind("username", user.getUsername())
                        .bind("email", user.getEmail())
                        .bind("firstName", user.getFirstName())
                        .bind("lastName", user.getLastName())
                        .bind("profileImage", user.getProfileImage())
                        .bind("bannerImage", user.getBannerImage())
                        .execute()
        );
    }
}
