package techsmiths.myface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import techsmiths.myface.helpers.Pagination;
import techsmiths.myface.models.dbmodels.User;
import techsmiths.myface.models.viewmodels.AllUsersViewModel;
import techsmiths.myface.models.viewmodels.UserViewModel;
import techsmiths.myface.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final int PAGE_SIZE = 10;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getAllUsersPage(@RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "page_size", required = false) Integer pageSize) {
        int numberOfPosts = userService.countAllPosts();
        Pagination pagination = new Pagination(page, pageSize, numberOfPosts);
        List<User> users = userService.getAllUsers(pagination);
        AllUsersViewModel allUsersViewModel = new AllUsersViewModel(users);

        return new ModelAndView("users/allUsers", "model", allUsersViewModel);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getSingleUserPage(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        UserViewModel userViewModel = new UserViewModel(user);

        return new ModelAndView("users/user", "model", userViewModel);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public RedirectView updateUser(@PathVariable("id") Long id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        return new RedirectView(String.format("/users/%s", id));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getCreateUserPage() {
        return new ModelAndView("users/createUser");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RedirectView createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return new RedirectView("/users");
    }
}
