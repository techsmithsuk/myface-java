package techsmiths.myface.models.apiModels;

import techsmiths.myface.models.dbmodels.User;

public class UserModel {
    private final User user;

    public UserModel(User user) {
        this.user = user;
    }

    public Long getId() {
        return user.getId();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    public String getProfileImage() {
        return user.getProfileImage();
    }

    public String getBannerImage() {
        return user.getBannerImage();
    }
}
