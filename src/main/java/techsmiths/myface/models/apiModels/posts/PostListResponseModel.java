package techsmiths.myface.models.apiModels.posts;

import techsmiths.myface.models.apiModels.ListResponseModel;
import techsmiths.myface.models.dbmodels.PostWithUsers;

import java.util.List;
import java.util.stream.Collectors;

public class PostListResponseModel extends ListResponseModel<PostModel, PostsFilter> {
    public PostListResponseModel(List<PostWithUsers> posts, PostsFilter filter, int totalNumberOfPosts) {
        super(posts.stream().map(PostModel::new).collect(Collectors.toList()), filter, totalNumberOfPosts);
    }

    @Override
    protected String getRootUrl() {
        return "/api/posts";
    }
}
