package techsmiths.myface.models.apiModels.comments;

import techsmiths.myface.models.dbmodels.Comment;

import java.util.Date;

public class CommentModel {
    private final Comment comment;

    public CommentModel(Comment comment) {
        this.comment = comment;
    }

    public Long getId() {
        return comment.getId();
    }

    public Long getSenderId() {
        return comment.getSenderId();
    }

    public Long getPostId() {
        return comment.getPostId();
    }

    public String getMessage() {
        return comment.getMessage();
    }

    public Date getSentAt() {
        return comment.getSentAt();
    }
}
