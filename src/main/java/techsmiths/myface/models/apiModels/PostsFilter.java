package techsmiths.myface.models.apiModels;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PostsFilter {
    private Integer page = 1;
    private Integer pageSize = 10;
    private Long senderId;
    private Long receiverId;
    private Date sentBefore;
    private Date sentAfter;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

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

    public Integer getOffset() {
        return (page - 1) * pageSize;
    }
}
