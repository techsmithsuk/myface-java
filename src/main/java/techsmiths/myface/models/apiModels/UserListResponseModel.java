package techsmiths.myface.models.apiModels;

import techsmiths.myface.models.dbmodels.User;
import techsmiths.myface.models.viewmodels.PostViewModel;
import techsmiths.myface.models.viewmodels.UserViewModel;

import java.util.List;
import java.util.stream.Collectors;

public class UserListResponseModel {
    private List<UserViewModel> users;
    private Boolean hasNextPage;
    private Integer currentPage;

    public UserListResponseModel(List<User> users, Integer currentPage, Boolean hasNextPage) {
        this.users = users.stream().map(UserViewModel::new).collect(Collectors.toList());
        this.hasNextPage = hasNextPage;
        this.currentPage = currentPage;
    }

    public List<UserViewModel> getUsers() {
        return users;
    }

    public String getNextPage() {
        if (!hasNextPage) {
            return null;
        }
        return String.format("/api/users?page=%d", currentPage + 1);
    }

    public String getPreviousPage() {
        if (currentPage <= 1) {
            return null;
        }

        return String.format("/api/users?page=%d", currentPage - 1);
    }
}
