package techsmiths.myface.models.viewmodels;

import techsmiths.myface.models.dbmodels.PostWithUsers;

import java.util.Date;

public class PostViewModel {
    private final PostWithUsers post;

    public PostViewModel(PostWithUsers post) {
        this.post = post;
    }

    public Long getId() {
        return post.getId();
    }

    public UserViewModel getSender() {
        return new UserViewModel(post.getSender());
    }

    public UserViewModel getReceiver() {
        return new UserViewModel(post.getReceiver());
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
}
