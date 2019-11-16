package techsmiths.myface.models.apiModels.comments;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UpdateCommentModel {
    private Long postId;
    private Long senderId;
    private String message;
    private Date sentAt;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSentAt() {
        return sentAt;
    }

    @DateTimeFormat(pattern = CommentModel.DATE_FORMAT)
    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }
}
