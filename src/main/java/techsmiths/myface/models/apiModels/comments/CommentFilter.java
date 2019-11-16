package techsmiths.myface.models.apiModels.comments;

import org.springframework.web.util.UriBuilder;
import techsmiths.myface.models.apiModels.Filter;

public class CommentFilter extends Filter {
    private Long postId;
    private Long senderId;

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
