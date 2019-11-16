package techsmiths.myface.models.apiModels.users;

import techsmiths.myface.models.apiModels.ListResponseModel;
import techsmiths.myface.models.dbmodels.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserListResponseModel extends ListResponseModel<UserModel, UsersFilter> {

    public UserListResponseModel(List<User> users, UsersFilter filter, int totalNumberOfResults) {
        super(users.stream().map(UserModel::new).collect(Collectors.toList()), filter, totalNumberOfResults);
    }

    @Override
    protected String getRootUrl() {
        return "/api/users";
    }
}
