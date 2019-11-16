package techsmiths.myface.controllers.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import techsmiths.myface.models.apiModels.comments.CommentModel;
import techsmiths.myface.models.dbmodels.Comment;
import techsmiths.myface.services.CommentService;

@Controller
@RequestMapping("/api/comments")
public class ApiCommentsController {
    private final CommentService commentService;

    public ApiCommentsController(CommentService commentService) {
        this.commentService = commentService;
    }
    
    @ResponseBody
    @RequestMapping("/{id}")
    public CommentModel getComment(@PathVariable("id") Long id) {
        Comment comment = commentService.getComment(id);
        return new CommentModel(comment);
    }
}
