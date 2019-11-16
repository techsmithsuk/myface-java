package techsmiths.myface.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import techsmiths.myface.helpers.Pagination;
import techsmiths.myface.models.apiModels.PostListResponseModel;
import techsmiths.myface.models.apiModels.PostModel;
import techsmiths.myface.models.apiModels.UpdatePostModel;
import techsmiths.myface.models.dbmodels.PostWithUsers;
import techsmiths.myface.services.PostService;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/posts")
public class ApiPostController {
    private final PostService postService;

    @Autowired
    public ApiPostController(PostService postService) {
        this.postService = postService;
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public PostListResponseModel getPosts(@RequestParam(value = "page", required = false) Integer page,
                                          @RequestParam(value = "page_size", required = false) Integer pageSize) {
        int numberOfPosts = postService.countAllPosts();
        Pagination pagination = new Pagination(page, pageSize, numberOfPosts);
        List<PostWithUsers> posts = postService.getAllPosts(pagination);

        return new PostListResponseModel(posts, pagination);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PostModel getPostDetails(@PathVariable("id") Long id) {
        PostWithUsers post = postService.getPostDetails(id);
        return new PostModel(post);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createPost(@ModelAttribute UpdatePostModel post) {
        Long id = postService.createPost(post);
        URI location = URI.create(String.format("/api/posts/%d", id));
        return ResponseEntity.created(location).build();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public PostModel updatePosts(@PathVariable("id") Long id, @ModelAttribute UpdatePostModel updatePostModel) {
        PostWithUsers post = postService.updatePost(id, updatePostModel);
        return new PostModel(post);
    }
}
