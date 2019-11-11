package techsmiths.myface.models.viewmodels;

import java.util.List;

public class AllUsersViewModel {
    private final List<UserViewModel> users;

    public AllUsersViewModel(List<UserViewModel> users) {
        this.users = users;
    }

    public List<UserViewModel> getUsers() {
        return users;
    }
}
