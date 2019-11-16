package techsmiths.myface.models.apiModels;

import techsmiths.myface.helpers.Pagination;
import techsmiths.myface.models.dbmodels.Post;
import techsmiths.myface.models.dbmodels.PostWithUsers;
import techsmiths.myface.models.viewmodels.PostViewModel;

import java.util.List;
import java.util.stream.Collectors;

public class PostListResponseModel {
    private List<PostViewModel> posts;
    private Pagination pagination;

    public PostListResponseModel(List<PostWithUsers> posts, Pagination pagination) {
        this.posts = posts.stream().map(PostViewModel::new).collect(Collectors.toList());
        this.pagination = pagination;
    }

    public List<PostViewModel> getPosts() {
        return posts;
    }

    public String getNextPage() {
        if (!pagination.hasNextPage()) {
            return null;
        }
        return String.format("/api/posts?page=%d", pagination.getPageNumber() + 1);
    }

    public String getPreviousPage() {
        if (!pagination.hasPreviousPage()) {
            return null;
        }

        return String.format("/api/posts?page=%d", pagination.getPageNumber() - 1);
    }
}
