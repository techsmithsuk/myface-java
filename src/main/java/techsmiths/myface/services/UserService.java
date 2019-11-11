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
}
