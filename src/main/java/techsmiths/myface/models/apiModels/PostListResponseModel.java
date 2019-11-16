package techsmiths.myface.models.apiModels;

import techsmiths.myface.helpers.Pagination;
import techsmiths.myface.models.dbmodels.PostWithUsers;

import java.util.List;
import java.util.stream.Collectors;

public class PostListResponseModel {
    private List<PostModel> posts;
    private Pagination pagination;

    public PostListResponseModel(List<PostWithUsers> posts, Pagination pagination) {
        this.posts = posts.stream().map(PostModel::new).collect(Collectors.toList());
        this.pagination = pagination;
    }

    public List<PostModel> getPosts() {
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
