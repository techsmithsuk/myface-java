package techsmiths.myface.models.apiModels.comments;

import com.fasterxml.jackson.annotation.JsonFormat;
import techsmiths.myface.models.dbmodels.Comment;

import java.util.Date;

public class CommentModel {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

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

    @JsonFormat(pattern = DATE_FORMAT)
    public Date getSentAt() {
        return comment.getSentAt();
    }
}
