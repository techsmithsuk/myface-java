package techsmiths.myface.controllers.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import techsmiths.myface.models.apiModels.comments.CommentFilter;
import techsmiths.myface.models.apiModels.comments.CommentListResponseModel;
import techsmiths.myface.models.apiModels.comments.CommentModel;
import techsmiths.myface.models.apiModels.comments.UpdateCommentModel;
import techsmiths.myface.models.dbmodels.Comment;
import techsmiths.myface.services.CommentService;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/comments")
public class ApiCommentsController {
    private final CommentService commentService;

    public ApiCommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ResponseBody
    @RequestMapping("")
    public CommentListResponseModel searchComments(CommentFilter filter) {
        int numberOfPosts = commentService.countComments(filter);
        List<Comment> posts = commentService.searchComments(filter);

        return new CommentListResponseModel(posts, filter, numberOfPosts);
    }

    @ResponseBody
    @RequestMapping("/{id}")
    public CommentModel getComment(@PathVariable("id") Long id) {
        Comment comment = commentService.getComment(id);
        return new CommentModel(comment);
    }

    @ResponseBody
    @RequestMapping("/{id}")
    public CommentModel updateComment(@PathVariable("id") Long id, UpdateCommentModel updateCommentModel) {
        Comment comment = commentService.updateComment(id, updateCommentModel);
        return new CommentModel(comment);
    }

    @RequestMapping("/create")
    public ResponseEntity createComment(UpdateCommentModel updateCommentModel) {
        Long id = commentService.createComment(updateCommentModel);
        URI location = URI.create("/api/comments/" + id);
        return ResponseEntity.created(location).build();
    }

    @ResponseBody
    @RequestMapping("/{id}")
    public void deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
    }
}
