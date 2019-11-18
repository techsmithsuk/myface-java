package techsmiths.myface.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
@CrossOrigin(origins = "http://localhost:3000")
public class ApiCommentsController {
    private final CommentService commentService;

    @Autowired
    public ApiCommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public CommentListResponseModel searchComments(CommentFilter filter) {
        int numberOfPosts = commentService.countComments(filter);
        List<Comment> posts = commentService.searchComments(filter);

        return new CommentListResponseModel(posts, filter, numberOfPosts);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommentModel getComment(@PathVariable("id") Long id) {
        Comment comment = commentService.getComment(id);
        return new CommentModel(comment);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public CommentModel updateComment(@PathVariable("id") Long id, UpdateCommentModel updateCommentModel) {
        Comment comment = commentService.updateComment(id, updateCommentModel);
        return new CommentModel(comment);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createComment(UpdateCommentModel updateCommentModel) {
        Long id = commentService.createComment(updateCommentModel);
        URI location = URI.create("/api/comments/" + id);
        return ResponseEntity.created(location).build();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
    }
}
