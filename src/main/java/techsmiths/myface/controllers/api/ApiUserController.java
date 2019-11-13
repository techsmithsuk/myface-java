package techsmiths.myface.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import techsmiths.myface.models.apiModels.PostListResponseModel;
import techsmiths.myface.models.apiModels.UserListResponseModel;
import techsmiths.myface.models.dbmodels.Post;
import techsmiths.myface.models.dbmodels.User;
import techsmiths.myface.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class ApiUserController {
    private static final int PAGE_SIZE = 10;

    private final UserService userService;

    @Autowired
    public ApiUserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping("")
    public UserListResponseModel getUsers(@RequestParam(value = "page") int page,
                                          @RequestParam(value = "page_size", required = false) Integer requestedPageSize) {
        int pageSize = requestedPageSize == null ? PAGE_SIZE : requestedPageSize;
        int offset = (page - 1) * PAGE_SIZE;
        List<User> posts = userService.getAllUsers(PAGE_SIZE, offset);
        int numberOfPosts = userService.countAllPosts();
        boolean hasNextPage = pageSize * page <= numberOfPosts;

        return new UserListResponseModel(posts, page, hasNextPage);
    }
}
