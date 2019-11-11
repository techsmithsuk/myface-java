package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/posts")
public class PostController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getAllPostsPage(@RequestParam(value = "page", required = false) Integer page) {
        return new ModelAndView("posts/allPosts");
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getCreatePostPage() {
        return new ModelAndView("posts/createPost");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RedirectView createPost() {
        return new RedirectView("/posts");
    }
}
