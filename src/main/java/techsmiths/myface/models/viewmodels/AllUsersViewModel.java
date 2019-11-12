package techsmiths.myface.models.viewmodels;

import techsmiths.myface.models.dbmodels.User;

import java.util.List;
import java.util.stream.Collectors;

public class AllUsersViewModel {
    private final List<UserViewModel> users;

    public AllUsersViewModel(List<User> users) {
        this.users = users.stream()
                .map(UserViewModel::new)
                .collect(Collectors.toList());
    }

    public List<UserViewModel> getUsers() {
        return users;
    }
}
