package techsmiths.myface.services;

import org.springframework.stereotype.Service;
import techsmiths.myface.models.apiModels.users.UpdateUserModel;
import techsmiths.myface.models.apiModels.users.UsersFilter;
import techsmiths.myface.models.dbmodels.User;

import java.util.List;

@Service
public class UserService extends DatabaseService {

    public List<User> searchUsers(UsersFilter filter) {
        return jdbi.withHandle(handle ->
                handle.createQuery(
                        "SELECT * FROM users " +
                            "WHERE (:username IS NULL OR username LIKE :username) " +
                            "AND (:email IS NULL OR email LIKE :email) " +
                            "AND (:firstName IS NULL OR first_name LIKE :firstName) " +
                            "AND (:lastName IS NULL OR last_name LIKE :lastName) " +
                            "LIMIT :limit OFFSET :offset"
                        )
                        .bind("username", filter.getUsername())
                        .bind("email", filter.getEmail())
                        .bind("firstName", filter.getFirstName())
                        .bind("lastName", filter.getLastName())
                        .bind("limit", filter.getPageSize())
                        .bind("offset", filter.getOffset())
                        .mapToBean(User.class)
                        .list()
        );
    }

    public int countUsers(UsersFilter filter) {
        return jdbi.withHandle(handle ->
                handle.createQuery(
                        "SELECT COUNT(*) FROM users " +
                            "WHERE (:username IS NULL OR username LIKE :username) " +
                            "AND (:email IS NULL OR email LIKE :email) " +
                            "AND (:firstName IS NULL OR first_name LIKE :firstName) " +
                            "AND (:lastName IS NULL OR last_name LIKE :lastName) "
                        )
                        .bind("username", filter.getUsername())
                        .bind("email", filter.getEmail())
                        .bind("firstName", filter.getFirstName())
                        .bind("lastName", filter.getLastName())
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public User getUserDetails(Long id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(User.class)
                        .one()
        );
    }

    public User updateUser(Long id, UpdateUserModel user) {
        jdbi.withHandle(handle ->
                handle.createUpdate(
                            "UPDATE users SET " +
                                    "username = :username " +
                                    "email = :email" +
                                    "first_name = :firstName" +
                                    "last_name = :lastName" +
                                    "profile_image = :profileImage" +
                                    "banner_image = :bannerImage" +
                            "WHERE id = :id")
                        .bind("username", user.getUsername())
                        .bind("email", user.getEmail())
                        .bind("firstName", user.getFirstName())
                        .bind("lastName", user.getLastName())
                        .bind("profileImage", user.getProfileImage())
                        .bind("bannerImage", user.getBannerImage())
                        .bind("id", id)
                        .execute()
        );
        return getUserDetails(id);
    }

    public Long createUser(UpdateUserModel user) {
        return jdbi.withHandle(handle -> {
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
                        .execute();

                return handle.createQuery("SELECT LAST_INSERT_ID()")
                        .mapTo(Long.class)
                        .one();
            }
        );
    }

    public void deleteUser(Long id) {
        jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM users WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );
    }
}
