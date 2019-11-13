package techsmiths.myface.models.apiModels;

import techsmiths.myface.models.dbmodels.Post;
import techsmiths.myface.models.viewmodels.PostViewModel;

import java.util.List;
import java.util.stream.Collectors;

public class PostListResponseModel {
    private List<PostViewModel> posts;
    private Boolean hasNextPage;
    private Integer currentPage;

    public PostListResponseModel(List<Post> posts, Integer currentPage, Boolean hasNextPage) {
        this.posts = posts.stream().map(PostViewModel::new).collect(Collectors.toList());
        this.hasNextPage = hasNextPage;
        this.currentPage = currentPage;
    }

    public List<PostViewModel> getPosts() {
        return posts;
    }

    public String getNextPage() {
        if (!hasNextPage) {
            return null;
        }
        return String.format("/api/posts?page=%d", currentPage + 1);
    }

    public String getPreviousPage() {
        if (currentPage <= 1) {
            return null;
        }

        return String.format("/api/posts?page=%d", currentPage - 1);
    }
}
