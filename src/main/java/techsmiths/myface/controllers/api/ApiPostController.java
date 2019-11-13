package techsmiths.myface.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import techsmiths.myface.models.apiModels.PostListResponseModel;
import techsmiths.myface.models.dbmodels.Post;
import techsmiths.myface.services.PostService;

import java.util.List;

@Controller
@RequestMapping("/api/posts")
public class ApiPostController {
    private static final int PAGE_SIZE = 10;

    private final PostService postService;

    @Autowired
    public ApiPostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("")
    public PostListResponseModel getPosts(@RequestParam(value = "page") int page,
                                          @RequestParam(value = "page_size", required = false) Integer requestedPageSize) {
        int pageSize = requestedPageSize == null ? PAGE_SIZE : requestedPageSize;
        int offset = (page - 1) * PAGE_SIZE;
        List<Post> posts = postService.getAllPosts(PAGE_SIZE, offset);
        int numberOfPosts = postService.countAllPosts();
        boolean hasNextPage = pageSize * page <= numberOfPosts;

        return new PostListResponseModel(posts, page, hasNextPage);
    }
}
