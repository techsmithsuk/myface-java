package techsmiths.myface.models.viewmodels;

import techsmiths.myface.models.dbmodels.Post;

import java.util.List;
import java.util.stream.Collectors;

public class AllPostsViewModel {
    private final List<PostViewModel> posts;

    public AllPostsViewModel(List<Post> posts) {
        this.posts = posts.stream()
                .map(PostViewModel::new)
                .collect(Collectors.toList());
    }

    public List<PostViewModel> getPosts() {
        return posts;
    }
}
