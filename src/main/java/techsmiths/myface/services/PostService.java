package techsmiths.myface.services;

import org.springframework.stereotype.Service;
import techsmiths.myface.models.dbmodels.Post;

import java.util.List;

@Service
public class PostService extends DatabaseService {

    public List<Post> getAllPosts(int limit, int offset) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM posts LIMIT :limit OFFSET :offset")
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .mapToBean(Post.class)
                        .list()
        );
    }

    public void createPost(Post post) {
        jdbi.withHandle(handle ->
                handle.createUpdate(
                        "INSERT INTO posts " +
                                "(sender_user_id, receiver_user_id, message, image) " +
                                "VALUES " +
                                "(:senderUserId, :receiverUserId, :message, :image)")
                        .bind("senderUserId", post.getSender().getId())
                        .bind("receiverUserId", post.getReceiver().getId())
                        .bind("message", post.getMessage())
                        .bind("image", post.getImage())
                        .execute()
        );
    }
}
