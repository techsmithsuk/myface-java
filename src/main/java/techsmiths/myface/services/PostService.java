package techsmiths.myface.services;

import org.springframework.stereotype.Service;
import techsmiths.myface.helpers.PostWithUsersMapper;
import techsmiths.myface.models.apiModels.posts.PostsFilter;
import techsmiths.myface.models.apiModels.posts.UpdatePostModel;
import techsmiths.myface.models.dbmodels.PostWithUsers;

import java.util.Date;
import java.util.List;

@Service
public class PostService extends DatabaseService {

    public List<PostWithUsers> searchPosts(PostsFilter filter) {
        return jdbi.withHandle(handle ->
                handle
                        .createQuery(
                            "SELECT * " +
                            "FROM posts as post " +
                            "JOIN users as sender on post.sender_user_id = sender.id " +
                            "JOIN users as receiver on post.receiver_user_id = receiver.id " +
                            "WHERE (:senderId is NULL OR post.sender_user_id = :senderId) " +
                            "AND (:receiverId is NULL OR post.receiver_user_id = :receiverId) " +
                            "AND (:sentBefore is NULL OR post.posted_at < :sentBefore) " +
                            "AND (:sentAfter is NULL OR post.posted_at > :sentAfter) " +
                            "ORDER BY post.posted_at DESC " +
                            "LIMIT :limit " +
                            "OFFSET :offset")
                        .bind("senderId", filter.getSenderId())
                        .bind("receiverId", filter.getReceiverId())
                        .bind("sentBefore", filter.getSentBefore())
                        .bind("sentAfter", filter.getSentAfter())
                        .bind("limit", filter.getPageSize())
                        .bind("offset", filter.getOffset())
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

    public Long createPost(UpdatePostModel post) {
        return jdbi.withHandle(handle -> {
                    handle.createUpdate(
                            "INSERT INTO posts " +
                                    "(sender_user_id, receiver_user_id, message, image, posted_at) " +
                                    "VALUES " +
                                    "(:senderUserId, :receiverUserId, :message, :image, :postedAt)")
                            .bind("senderUserId", post.getSenderId())
                            .bind("receiverUserId", post.getReceiverId())
                            .bind("message", post.getMessage())
                            .bind("image", post.getImage())
                            .bind("postedAt", post.getPostedAt() == null ? new Date() : post.getPostedAt())
                            .execute();

                    return handle.createQuery("SELECT LAST_INSERT_ID()")
                            .mapTo(Long.class)
                            .one();
                }
        );
    }

    public int countPosts(PostsFilter filter) {
        return jdbi.withHandle(handle ->
                handle.createQuery(
                        "SELECT COUNT(*) FROM posts " +
                            "WHERE (:senderId is NULL OR sender_user_id = :senderId) " +
                            "AND (:receiverId is NULL OR receiver_user_id = :receiverId) " +
                            "AND (:sentBefore is NULL OR posted_at < :sentBefore) " +
                            "AND (:sentAfter is NULL OR posted_at > :sentAfter)")
                        .bind("senderId", filter.getSenderId())
                        .bind("receiverId", filter.getReceiverId())
                        .bind("sentBefore", filter.getSentBefore())
                        .bind("sentAfter", filter.getSentAfter())
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public PostWithUsers updatePost(Long id, UpdatePostModel updatePostModel) {
        jdbi.withHandle(handle ->
                handle.createUpdate(
                        "UPDATE posts SET " +
                        "sender_user_id = :senderId, " +
                        "receiver_user_id = :receiverId, " +
                        "message = :message, " +
                        "image = :image, " +
                        "posted_at = :postedAt " +
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

    public void deletePost(Long id) {
        jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM posts WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );
    }
}
