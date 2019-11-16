package techsmiths.myface.services;

import techsmiths.myface.models.apiModels.comments.CommentFilter;
import techsmiths.myface.models.apiModels.comments.UpdateCommentModel;
import techsmiths.myface.models.dbmodels.Comment;

import java.util.Date;
import java.util.List;

public class CommentService extends DatabaseService {

    public List<Comment> searchComments(CommentFilter filter) {
        return jdbi.withHandle(handle ->
                handle.createQuery(
                        "SELECT * FROM comments " +
                            "WHERE (:postId IS NULL OR post_id LIKE :postId) " +
                            "AND (:senderId IS NULL OR sender_id LIKE :senderId) " +
                            "LIMIT :limit OFFSET :offset"
                        )
                        .bind("postId", filter.getPostId())
                        .bind("senderId", filter.getSenderId())
                        .bind("limit", filter.getPageSize())
                        .bind("offset", filter.getOffset())
                        .mapToBean(Comment.class)
                        .list()
        );
    }

    public int countComments(CommentFilter filter) {
        return jdbi.withHandle(handle ->
                handle.createQuery(
                        "SELECT COUNT(*) FROM comments " +
                            "WHERE (:postId IS NULL OR post_id LIKE :postId) " +
                            "AND (:senderId IS NULL OR sender_id LIKE :senderId)"
                        )
                        .bind("postId", filter.getPostId())
                        .bind("senderId", filter.getSenderId())
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public Comment getComment(Long id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM comments WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Comment.class)
                        .one()
        );
    }

    public Comment updateComment(Long id, UpdateCommentModel updateCommentModel) {
        jdbi.withHandle(handle ->
                handle.createUpdate(
                        "UPDATE comments " +
                            "SET (post_id = :postId, sender_id = :senderId, message = :message, sent_at = :sentAt) " +
                            "WHERE id = :id")
                    .bind("postId", updateCommentModel.getPostId())
                    .bind("senderId", updateCommentModel.getSenderId())
                    .bind("message", updateCommentModel.getMessage())
                    .bind("sentAt", updateCommentModel.getSentAt())
                    .execute()
        );
        return getComment(id);
    }

    public Long createComment(UpdateCommentModel comment) {
        jdbi.withHandle(handle ->
                handle.createUpdate(
                        "INSERT INTO comments " +
                            "(sender_id, post_id, message, sent_at) " +
                            "VALUES " +
                            "(:senderId, :postId, :message, :sentAt)"
                        )
                        .bind("senderId", comment.getSenderId())
                        .bind("postId", comment.getPostId())
                        .bind("message", comment.getMessage())
                        .bind("sentAt", comment.getSentAt() == null ? new Date() : comment.getSentAt())
                        .execute()
        );
        return getLastAddedId();
    }
}
