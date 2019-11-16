package techsmiths.myface.services;

import org.springframework.stereotype.Service;
import techsmiths.myface.helpers.Pagination;
import techsmiths.myface.helpers.PostWithUsersMapper;
import techsmiths.myface.models.apiModels.CreatePostModel;
import techsmiths.myface.models.apiModels.UpdatePostModel;
import techsmiths.myface.models.dbmodels.Post;
import techsmiths.myface.models.dbmodels.PostWithUsers;

import java.util.List;

@Service
public class PostService extends DatabaseService {

    public List<PostWithUsers> getAllPosts(Pagination pagination) {
        return jdbi.withHandle(handle ->
                handle
                        .createQuery(
                            "SELECT * " +
                            "FROM posts as post " +
                            "JOIN users as sender on post.sender_user_id = sender.id " +
                            "JOIN users as receiver on post.receiver_user_id = receiver.id " +
                            "ORDER BY post.posted_at DESC " +
                            "LIMIT :limit " +
                            "OFFSET :offset")
                        .bind("limit", pagination.getLimit())
                        .bind("offset", pagination.getOffset())
                        .map(new PostWithUsersMapper())
                        .list()
        );
    }

    public PostWithUsers getPostDetails(Long id) {
        return jdbi.withHandle(handle ->
                handle
                        .createQuery(
                            "SELECT * " +
                            "FROM posts as post " +
                            "JOIN users as sender on post.sender_user_id = sender.id " +
                            "JOIN users as receiver on post.receiver_user_id = receiver.id " +
                            "WHERE post.id = :id")
                        .bind("id", id)
                        .map(new PostWithUsersMapper())
                        .one()
        );
    }

    public Long createPost(CreatePostModel post) {
        jdbi.withHandle(handle ->
                handle.createUpdate(
                        "INSERT INTO posts " +
                                "(sender_user_id, receiver_user_id, message, image, posted_at) " +
                                "VALUES " +
                                "(:senderUserId, :receiverUserId, :message, :image NOW())")
                        .bind("senderUserId", post.getSenderId())
                        .bind("receiverUserId", post.getReceiverId())
                        .bind("message", post.getMessage())
                        .bind("image", post.getImage())
                        .execute()
        );
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT LAST_INSERT_ID()")
                        .mapTo(Long.class)
                        .one()
        );
    }

    public int countAllPosts() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM posts")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public PostWithUsers updatePost(Long id, UpdatePostModel updatePostModel) {
        jdbi.withHandle(handle ->
                handle.createUpdate(
                        "UPDATE posts SET " +
                        "( " +
                            "senderId = :senderId " +
                            "receiverId = :receiverId " +
                            "message = :message " +
                            "image = :image " +
                            "posted_at = :postedAt " +
                        ") " +
                        "WHERE id = :id")
                        .bind("id", id)
                        .bind("senderId", updatePostModel.getSenderId())
                        .bind("receiverId", updatePostModel.getReceiverId())
                        .bind("message", updatePostModel.getMessage())
                        .bind("image", updatePostModel.getImage())
                        .bind("postedAt", updatePostModel.getPostedAt())
                        .execute()
        );
        return getPostDetails(id);
    }
}
