package techsmiths.myface.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import techsmiths.myface.models.apiModels.users.UpdateUserModel;
import techsmiths.myface.models.apiModels.users.UserListResponseModel;
import techsmiths.myface.models.apiModels.users.UserModel;
import techsmiths.myface.models.apiModels.users.UsersFilter;
import techsmiths.myface.models.dbmodels.User;
import techsmiths.myface.services.UserService;

import java.net.URI;
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
    @RequestMapping(value = "", method = RequestMethod.GET)
    public UserListResponseModel getUsers(UsersFilter filter) {
        int numberOfPosts = userService.countUsers(filter);
        List<User> users = userService.searchUsers(filter);

        return new UserListResponseModel(users, filter, numberOfPosts);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserModel getUserDetails(@PathVariable("id") Long id) {
        User user = userService.getUserDetails(id);
        return new UserModel(user);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createUser(@ModelAttribute UpdateUserModel updateUserModel) {
        Long id = userService.createUser(updateUserModel);
        URI location = URI.create(String.format("/api/users/%d", id));
        return ResponseEntity.created(location).build();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public UserModel updateUser(@PathVariable("id") Long id, UpdateUserModel updateUserModel) {
        User user = userService.updateUser(id, updateUserModel);
        return new UserModel(user);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
