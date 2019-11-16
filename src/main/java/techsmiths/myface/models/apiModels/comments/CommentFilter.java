package techsmiths.myface.models.apiModels.comments;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.util.UriBuilder;
import techsmiths.myface.models.apiModels.Filter;

import java.util.Date;

public class CommentFilter extends Filter {
    private Long postId;
    private Long senderId;
    private Date sentBefore;
    private Date sentAfter;

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

    public Date getSentBefore() {
        return sentBefore;
    }

    @DateTimeFormat(pattern = CommentModel.DATE_FORMAT)
    public void setSentBefore(Date sentBefore) {
        this.sentBefore = sentBefore;
    }

    public Date getSentAfter() {
        return sentAfter;
    }

    @DateTimeFormat(pattern = CommentModel.DATE_FORMAT)
    public void setSentAfter(Date sentAfter) {
        this.sentAfter = sentAfter;
    }

    @Override
    public void appendQueryParams(UriBuilder builder) {
        if (postId != null) {
            builder.queryParam("postId", postId);
        }

        if (senderId != null) {
            builder.queryParam("senderId", senderId);
        }
    }
}
