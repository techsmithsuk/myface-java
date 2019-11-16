package techsmiths.myface.models.apiModels;

import com.fasterxml.jackson.annotation.JsonFormat;
import techsmiths.myface.models.dbmodels.PostWithUsers;

import java.util.Date;

public class PostModel {
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private final PostWithUsers post;

    public PostModel(PostWithUsers post) {
        this.post = post;
    }

    public Long getId() {
        return post.getId();
    }

    public Long getSenderId() {
        return post.getSenderId();
    }

    public Long getReceiverId() {
        return post.getReceiverId();
    }

    public String getMessage() {
        return post.getMessage();
    }

    public String getImage() {
        return post.getImage();
    }

    @JsonFormat(pattern = DATE_PATTERN)
    public Date getPostedAt() {
        return post.getPostedAt();
    }

    public UserModel getSender() {
        return new UserModel(post.getSender());
    }

    public UserModel getReceiver() {
        return new UserModel(post.getReceiver());
    }
}
