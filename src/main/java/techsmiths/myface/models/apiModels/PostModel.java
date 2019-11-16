package techsmiths.myface.models.apiModels;

import techsmiths.myface.models.dbmodels.Post;
import techsmiths.myface.models.dbmodels.PostWithUsers;

import java.util.Date;

public class PostModel {
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
