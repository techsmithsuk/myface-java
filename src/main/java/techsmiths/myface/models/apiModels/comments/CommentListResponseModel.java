package techsmiths.myface.models.apiModels.comments;

import techsmiths.myface.models.apiModels.ListResponseModel;
import techsmiths.myface.models.dbmodels.Comment;

import java.util.List;
import java.util.stream.Collectors;

public class CommentListResponseModel extends ListResponseModel<CommentModel, CommentFilter> {

    public CommentListResponseModel(List<Comment> comments, CommentFilter filter, int totalNumberOfItems) {
        super(comments.stream().map(CommentModel::new).collect(Collectors.toList()), filter, totalNumberOfItems);
    }

    @Override
    protected String getRootUrl() {
        return "/api/comments";
    }
}
