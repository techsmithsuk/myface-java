package techsmiths.myface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/users")
public class UserController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getAllUsersPage() {
        return new ModelAndView("users/allUsers");
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
