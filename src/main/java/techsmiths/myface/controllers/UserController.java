package techsmiths.myface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import techsmiths.myface.models.dbmodels.User;
import techsmiths.myface.models.viewmodels.AllUsersViewModel;
import techsmiths.myface.models.viewmodels.UserViewModel;
import techsmiths.myface.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final int PAGE_SIZE = 10;

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getAllUsersPage(@RequestParam(value = "page", required = false) Integer page) {
        int page_index = page == null ? 0 : page - 1;
        int offset = page_index * PAGE_SIZE;

        List<User> users = userService.getAllUsers(PAGE_SIZE, offset);
        List<UserViewModel> userViewModels = users.stream()
                .map(UserViewModel::new)
                .collect(Collectors.toList());
        AllUsersViewModel allUsersViewModel = new AllUsersViewModel(userViewModels);

        return new ModelAndView("users/allUsers", "model", allUsersViewModel);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getSingleUserPage(@PathVariable("id") int id) {
        return new ModelAndView("users/user");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public RedirectView updateUser(@PathVariable("id") int id) {
        return new RedirectView(String.format("/users/%s", id));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getCreateUserPage() {
        return new ModelAndView("users/createUser");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RedirectView createUser() {
        return new RedirectView("/users");
    }
}
