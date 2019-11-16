package techsmiths.myface.models.apiModels;

import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import techsmiths.myface.models.dbmodels.PostWithUsers;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class PostListResponseModel {
    private List<PostModel> posts;
    private PostsFilter filter;
    private int totalNumberOfPosts;

    public PostListResponseModel(List<PostWithUsers> posts, PostsFilter filter, int totalNumberOfPosts) {
        this.posts = posts.stream().map(PostModel::new).collect(Collectors.toList());
        this.filter = filter;
        this.totalNumberOfPosts = totalNumberOfPosts;
    }

    public List<PostModel> getPosts() {
        return posts;
    }

    public Integer getTotalNumberOfResults() {
        return totalNumberOfPosts;
    }

    public URI getNextPage() {
        if (filter.getPage() * filter.getPageSize() >= totalNumberOfPosts) {
            return null;
        }

        UriBuilder builder = UriComponentsBuilder.fromPath("/api/posts")
                .queryParam("page", filter.getPage() + 1)
                .queryParam("pageSize", filter.getPageSize());

        addFilterParams(builder);
        return builder.build();
    }

    public URI getPreviousPage() {
        if (filter.getPage() <= 1) {
            return null;
        }

        UriBuilder builder = UriComponentsBuilder.fromPath("/api/posts")
                .queryParam("page", filter.getPage() - 1)
                .queryParam("pageSize", filter.getPageSize());

        addFilterParams(builder);
        return builder.build();
    }

    private void addFilterParams(UriBuilder builder) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (filter.getSenderId() != null) {
            builder.queryParam("senderId", filter.getSenderId());
        }

        if (filter.getReceiverId() != null) {
            builder.queryParam("receiverId", filter.getReceiverId());
        }

        if (filter.getSentAfter() != null) {
            builder.queryParam("sentAfter", formatter.format(filter.getSentAfter()));
        }

        if (filter.getSentBefore() != null) {
            builder.queryParam("sentBefore", formatter.format(filter.getSentBefore()));
        }
    }
}
