package techsmiths.myface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import techsmiths.myface.helpers.Pagination;
import techsmiths.myface.models.apiModels.CreatePostModel;
import techsmiths.myface.models.dbmodels.Post;
import techsmiths.myface.models.viewmodels.AllPostsViewModel;
import techsmiths.myface.services.PostService;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getAllPostsPage(@RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        int numberOfPosts = postService.countAllPosts();
        Pagination pagination = new Pagination(page, pageSize, numberOfPosts);
        List<Post> posts = postService.getAllPosts(pagination);
        AllPostsViewModel postsViewModel = new AllPostsViewModel(posts);

        return new ModelAndView("posts/allPosts", "model", postsViewModel);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getCreatePostPage() {
        return new ModelAndView("posts/createPost");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RedirectView createPost(@ModelAttribute CreatePostModel post) {
        postService.createPost(post);
        return new RedirectView("/posts");
    }
}
