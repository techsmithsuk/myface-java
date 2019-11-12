package techsmiths.myface.models.viewmodels;

import techsmiths.myface.models.dbmodels.Post;

public class PostViewModel {
    private final Post post;

    public PostViewModel(Post post) {
        this.post = post;
    }

    public int getId() {
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
}
