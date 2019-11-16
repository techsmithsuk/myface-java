package techsmiths.myface.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import techsmiths.myface.helpers.Pagination;
import techsmiths.myface.models.apiModels.UserListResponseModel;
import techsmiths.myface.models.apiModels.UserModel;
import techsmiths.myface.models.dbmodels.User;
import techsmiths.myface.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class ApiUserController {
    private final UserService userService;

    @Autowired
    public ApiUserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping("")
    public UserListResponseModel getUsers(@RequestParam(value = "page", required = false) Integer page,
                                          @RequestParam(value = "page_size", required = false) Integer pageSize) {
        int numberOfPosts = userService.countAllPosts();
        Pagination pagination = new Pagination(page, pageSize, numberOfPosts);
        List<User> users = userService.getAllUsers(pagination);

        return new UserListResponseModel(users, pagination);
    }

    @ResponseBody
    @RequestMapping("/{id}")
    public UserModel getUserDetails(@PathVariable("id") Long id) {
        User user = userService.getUserDetails(id);
        return new UserModel(user);
    }
}
