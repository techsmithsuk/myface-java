package techsmiths.myface.models.apiModels;

import techsmiths.myface.helpers.Pagination;
import techsmiths.myface.models.dbmodels.User;
import techsmiths.myface.models.viewmodels.UserViewModel;

import java.util.List;
import java.util.stream.Collectors;

public class UserListResponseModel {
    private List<UserViewModel> users;
    private Pagination pagination;

    public UserListResponseModel(List<User> users, Pagination pagination) {
        this.users = users.stream().map(UserViewModel::new).collect(Collectors.toList());
        this.pagination = pagination;
    }

    public List<UserViewModel> getUsers() {
        return users;
    }

    public String getNextPage() {
        if (!pagination.hasNextPage()) {
            return null;
        }
        return String.format("/api/users?page=%d", pagination.getPageNumber() + 1);
    }

    public String getPreviousPage() {
        if (!pagination.hasPreviousPage()) {
            return null;
        }

        return String.format("/api/users?page=%d", pagination.getPageNumber() - 1);
    }
}
