package techsmiths.myface.services;

import techsmiths.myface.models.dbmodels.Comment;

public class CommentService extends DatabaseService {

    public Comment getComment(Long id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM comments WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Comment.class)
                        .one()
        );
    }
}
