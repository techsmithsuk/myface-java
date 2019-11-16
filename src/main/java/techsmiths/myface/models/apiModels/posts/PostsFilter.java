package techsmiths.myface.models.apiModels.posts;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.util.UriBuilder;
import techsmiths.myface.models.apiModels.Filter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostsFilter extends Filter {
    private Long senderId;
    private Long receiverId;
    private Date sentBefore;
    private Date sentAfter;

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Date getSentBefore() {
        return sentBefore;
    }

    @DateTimeFormat(pattern = PostModel.DATE_PATTERN)
    public void setSentBefore(Date sentBefore) {
        this.sentBefore = sentBefore;
    }

    public Date getSentAfter() {
        return sentAfter;
    }

    @DateTimeFormat(pattern = PostModel.DATE_PATTERN)
    public void setSentAfter(Date sentAfter) {
        this.sentAfter = sentAfter;
    }

    @Override
    public void appendQueryParams(UriBuilder builder) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (senderId != null) {
            builder.queryParam("senderId", senderId);
        }

        if (receiverId != null) {
            builder.queryParam("receiverId", receiverId);
        }

        if (sentAfter != null) {
            builder.queryParam("sentAfter", formatter.format(sentAfter));
        }

        if (sentBefore != null) {
            builder.queryParam("sentBefore", formatter.format(sentBefore));
        }
    }
}
