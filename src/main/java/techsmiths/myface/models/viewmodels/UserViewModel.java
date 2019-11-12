package techsmiths.myface.models.viewmodels;

import techsmiths.myface.models.dbmodels.User;

public class UserViewModel {
    private final User user;

    public UserViewModel(User user) {
        this.user = user;
    }

    public Integer getId() {
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

    public String getDisplayName() {
        return String.format("%s %s", user.getFirstName(), user.getLastName());
    }

    public String getProfileImage() {
        return user.getProfileImage();
    }

    public String getBannerImage() {
        return user.getBannerImage();
    }
}
