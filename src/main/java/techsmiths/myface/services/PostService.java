package techsmiths.myface.services;

import org.springframework.stereotype.Service;
import techsmiths.myface.models.dbmodels.Post;

import java.util.List;

@Service
public class PostService extends DatabaseService {

    public List<Post> getAllPosts(int limit, int offset) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users LIMIT :limit OFFSET :offset")
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .mapToBean(Post.class)
                        .list()
        );
    }
}
